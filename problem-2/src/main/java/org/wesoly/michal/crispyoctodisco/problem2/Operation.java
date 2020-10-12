package org.wesoly.michal.crispyoctodisco.problem2;

import java.util.Stack;

public interface Operation {

    boolean isApplicable(String item);

    void apply(Stack<Float> values, String item);
}
