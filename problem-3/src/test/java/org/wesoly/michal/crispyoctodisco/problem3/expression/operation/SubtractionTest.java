package org.wesoly.michal.crispyoctodisco.problem3.expression.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class SubtractionTest {

    private static final String OPERATION_SIGN = "-";
    private final Operation operation = new Subtraction();

    private static Stream<Arguments> provideIsApplicableParameters() {
        return Stream.of(
                Arguments.of(OPERATION_SIGN, true),
                Arguments.of("+", false),
                Arguments.of(" ", false),
                Arguments.of("t", false),
                Arguments.of("test", false),
                Arguments.of("33", false),
                Arguments.of("33.03", false)
        );
    }

    private static Stream<Arguments> provideApplyParameters() {
        return Stream.of(
                Arguments.of(getStack(List.of(20f, 10f)), getStack(List.of(10f))),
                Arguments.of(getStack(List.of(20.10f, 10.10f)), getStack(List.of(10f))),
                Arguments.of(getStack(List.of(20.10f, 10f)), getStack(List.of(10.10f))),
                Arguments.of(getStack(List.of(10f, 10f, 10f)), getStack(List.of(10f, 0f)))
        );
    }

    private static Stack<Float> getStack(List<Float> initializer) {
        Stack<Float> stack = new Stack<>();
        stack.addAll(initializer);
        return stack;
    }

    @ParameterizedTest
    @MethodSource("provideIsApplicableParameters")
    public void shouldApplyToCorrectSign(String input, boolean expected) {
        assertThat(operation.isApplicable(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideApplyParameters")
    public void shouldApplyOperationCorrectly(Stack<Float> inputStack, Stack<Float> outputStack) {
        operation.apply(inputStack, OPERATION_SIGN);
        assertThat(inputStack).isEqualTo(outputStack);
    }


}