package org.wesoly.michal.crispyoctodisco.operation;

import java.util.Stack;
import org.springframework.stereotype.Component;

@Component
public class Addition implements Operation {

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
