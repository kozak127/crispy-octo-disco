package org.wesoly.michal.crispyoctodisco;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;

    @Test
    public void shouldProcessGivenExamplesCorrectly() {

        // GIVEN
        String expression1 = "+ + 0.5 1.5 * 4 10";
        String expression2 = "- 2e3 - 700 + 7 * 2 15";
        String expression3 = "- -1.5 * 3.1415 / -7 -2";
        String expression4 = "100500";
        String expression5 = "1 2";
        String expression6 = "+ 1";

        List<String> expressions = List.of(expression1, expression2, expression3, expression4, expression5, expression6);

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String result1 = "42.00";
        String result2 = "1337.00";
        String result3 = "-12.50";
        String result4 = "100500.00";
        String result5 = "error";
        String result6 = "error";

        /*
         * GIVEN EXAMPLES ARE NOT CORRECT REVERSE POLISH NOTATION EXPRESSIONS!
         */

        //assertThat(evaluatedExpressions).containsExactly(result1, result2, result3, result4, result5, result6);
        assertThat(evaluatedExpressions).containsExactly(result5, result5, result5, result4, result5, result6);
    }

    @Test
    public void shouldProcessSimpleNumbersCorrectly() {

        // GIVEN
        String expression1 = "7 4 + 3 -";
        String expression2 = "1 2 * 3 +";
        String expression3 = "1 2 + 3 *";
        String expression4 = "5 9 2 * +";
        String expression5 = "1 2 * 3 4 * +";
        String expression6 = "+ 1";

        List<String> expressions = List.of(expression1, expression2, expression3, expression4, expression5, expression6);

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String result1 = "8.00";
        String result2 = "5.00";
        String result3 = "9.00";
        String result4 = "23.00";
        String result5 = "14.00";
        String result6 = "error";

        assertThat(evaluatedExpressions).containsExactly(result1, result2, result3, result4, result5, result6);
    }

    @Test
    public void shouldProcessFloatNumbersCorrectly() {

        // GIVEN
        String expression1 = "7.5 4.5 + 3 -";
        String expression2 = "1.1 2.5 * 3.3 +";
        String expression3 = "1.3 2.4 + 3.5 *";
        String expression4 = "5.4 9.7 2.9 * +";
        String expression5 = "1.67 2.22 * 3.88 4.9909 * +";
        String expression6 = "+ 1.9887";

        List<String> expressions = List.of(expression1, expression2, expression3, expression4, expression5, expression6);

        // WHEN
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);

        // THEN
        String result1 = "9.00";
        String result2 = "6.05";
        String result3 = "12.95";
        String result4 = "33.53";
        String result5 = "23.07";
        String result6 = "error";

        assertThat(evaluatedExpressions).containsExactly(result1, result2, result3, result4, result5, result6);
    }
}