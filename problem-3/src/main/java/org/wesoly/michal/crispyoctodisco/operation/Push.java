package org.wesoly.michal.crispyoctodisco.operation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import org.springframework.stereotype.Component;

@Component
public class Push implements Operation {

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
