package org.wesoly.michal.crispyoctodisco.problem3.expression.operation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PushTest {

    private final Operation operation = new Push();

    private static Stream<Arguments> provideIsApplicableParameters() {
        return Stream.of(
                Arguments.of("-", false),
                Arguments.of(" ", false),
                Arguments.of("t", false),
                Arguments.of("test", false),
                Arguments.of("33", true),
                Arguments.of("33.03", true)
        );
    }

    private static Stream<Arguments> provideApplyParameters() {
        return Stream.of(
                Arguments.of(getStack(List.of(10f, 10f)), "30", getStack(List.of(10f, 10f, 30f))),
                Arguments.of(getStack(List.of(10.10f, 10.10f)), "30.00", getStack(List.of(10.10f, 10.10f, 30f))),
                Arguments.of(getStack(List.of(10.10f, 10f)), "30.0", getStack(List.of(10.10f, 10f, 30f))),
                Arguments.of(getStack(List.of(10f, 10f, 10f)), "30", getStack(List.of(10f, 10f, 10f, 30f)))
        );
    }

    private static Stack<Float> getStack(List<Float> initializer) {
        Stack<Float> stack = new Stack<>();
        stack.addAll(initializer);
        return stack;
    }

    @ParameterizedTest
    @MethodSource("provideIsApplicableParameters")
    public void shouldApplyToCorrectItem(String input, boolean expected) {
        assertThat(operation.isApplicable(input)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideApplyParameters")
    public void shouldApplyOperationCorrectly(Stack<Float> inputStack, String item, Stack<Float> outputStack) {
        operation.apply(inputStack, item);
        assertThat(inputStack).isEqualTo(outputStack);
    }


}