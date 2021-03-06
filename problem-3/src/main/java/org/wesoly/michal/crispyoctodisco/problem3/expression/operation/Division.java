package org.wesoly.michal.crispyoctodisco.problem3.expression.operation;

import java.util.Stack;
import org.springframework.stereotype.Component;

@Component
public class Division implements Operation {

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
