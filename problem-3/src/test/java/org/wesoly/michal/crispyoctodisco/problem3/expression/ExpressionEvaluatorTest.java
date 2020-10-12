package org.wesoly.michal.crispyoctodisco.problem3.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import org.junit.Test;
import org.wesoly.michal.crispyoctodisco.problem3.expression.operation.Operation;

public class ExpressionEvaluatorTest {

    private static final String DELIMITER = " ";
    private static final String NUMBER = "5";
    private static final String OPERATOR = "+";
    private static final String ERROR = "error";

    private final ExpressionOperations operations = mock(ExpressionOperations.class);

    private ExpressionEvaluator evaluator = new ExpressionEvaluator(operations);

    @Test
    public void shouldProcessSimpleExpressionCorrectly() {

        // GIVEN
        String expression = NUMBER + DELIMITER + NUMBER + DELIMITER + OPERATOR;
        List<String> expressions = List.of(expression);

        Operation operation = new MockOperation(List.of(10f));
        given(operations.find(eq(NUMBER))).willReturn(Optional.of(operation));
        given(operations.find(eq(OPERATOR))).willReturn(Optional.of(operation));

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String expectedResult = "10.00";
        assertThat(evaluatedExpressions).containsExactly(expectedResult);
        verify(operations, times(2)).find(eq(NUMBER));
        verify(operations, times(1)).find(eq(OPERATOR));
    }

    @Test
    public void shouldProcessIncorrectExpressionCorrectly() {

        // GIVEN
        String expression = NUMBER + DELIMITER + OPERATOR;
        List<String> expressions = List.of(expression);

        Operation operation = new MockOperation(List.of(10f, 20f));
        given(operations.find(eq(NUMBER))).willReturn(Optional.of(operation));
        given(operations.find(eq(OPERATOR))).willReturn(Optional.of(operation));

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String expectedResult = ERROR;
        assertThat(evaluatedExpressions).containsExactly(expectedResult);
        verify(operations, times(1)).find(eq(NUMBER));
        verify(operations, times(1)).find(eq(OPERATOR));
    }

    @Test
    public void shouldHandleEmptyStackException() {

        // GIVEN
        String expression = NUMBER + DELIMITER + OPERATOR;
        List<String> expressions = List.of(expression);

        Operation operation = mock(Operation.class);
        given(operations.find(eq(NUMBER))).willReturn(Optional.of(operation));
        given(operations.find(eq(OPERATOR))).willReturn(Optional.of(operation));
        doThrow(new EmptyStackException()).when(operation).apply(any(Stack.class), eq(NUMBER));

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String expectedResult = ERROR;
        assertThat(evaluatedExpressions).containsExactly(expectedResult);
        verify(operations, times(1)).find(eq(NUMBER));
    }

    @Test
    public void shouldHandleIllegalArgumentException() {

        // GIVEN
        String expression = NUMBER + DELIMITER + OPERATOR;
        List<String> expressions = List.of(expression);

        Operation operation = mock(Operation.class);
        given(operations.find(eq(NUMBER))).willReturn(Optional.of(operation));
        given(operations.find(eq(OPERATOR))).willReturn(Optional.of(operation));
        doThrow(new IllegalArgumentException()).when(operation).apply(any(Stack.class), eq(NUMBER));

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String expectedResult = ERROR;
        assertThat(evaluatedExpressions).containsExactly(expectedResult);
        verify(operations, times(1)).find(eq(NUMBER));
    }

    private static class MockOperation implements Operation {

        private final List<Float> testData;

        private MockOperation(List<Float> testData) {
            this.testData = testData;
        }

        @Override
        public boolean isApplicable(String item) {
            return true;
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            values.clear();
            values.addAll(testData);
        }
    }
}