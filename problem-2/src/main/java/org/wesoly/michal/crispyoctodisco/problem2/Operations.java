package org.wesoly.michal.crispyoctodisco.problem2;

import com.google.common.collect.MoreCollectors;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class Operations {

    private final List<Operation> operations = List.of(new Push(), new Addition(), new Subtraction(), new Multiplication(), new Division());

    public Optional<Operation> find(String item) {
        return operations.stream()
                .filter(operation -> operation.isApplicable(item))
                .collect(MoreCollectors.toOptional());
    }

    private static class Push implements Operation {

        private static final String REGEX = "[+-]?([0-9]*[.])?[0-9]+";

        @Override
        public boolean isApplicable(String item) {
            return item.matches(REGEX);
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            Float value = toRoundedFloat(item);
            values.push(value);
        }

        private Float toRoundedFloat(String item) {
            BigDecimal number = new BigDecimal(item);
            number = number.setScale(2, RoundingMode.HALF_EVEN);
            return number.floatValue();
        }
    }

    private static class Addition implements Operation {

        private static final String OPERATOR = "+";

        @Override
        public boolean isApplicable(String item) {
            return item.equals(OPERATOR);
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            values.push(values.pop() + values.pop());
        }
    }

    private static class Subtraction implements Operation {

        private static final String OPERATOR = "-";

        @Override
        public boolean isApplicable(String item) {
            return item.equals(OPERATOR);
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            Float secondValue = values.pop();
            values.push(values.pop() - secondValue);
        }
    }

    private static class Multiplication implements Operation {

        private static final String OPERATOR = "*";

        @Override
        public boolean isApplicable(String item) {
            return item.equals(OPERATOR);
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            values.push(values.pop() * values.pop());
        }
    }

    private static class Division implements Operation {

        private static final String OPERATOR = "/";

        @Override
        public boolean isApplicable(String item) {
            return item.equals(OPERATOR);
        }

        @Override
        public void apply(Stack<Float> values, String item) {
            Float secondValue = values.pop();
            values.push(values.pop() / secondValue);
        }
    }
}
