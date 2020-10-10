package org.wesoly.michal.crispyoctodisco;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        InputReader inputReader = new InputReader();

        List<String> expressions = inputReader.read();

        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        List<String> evaluatedExpressions = evaluator.evaluate(expressions);
        System.out.println(evaluatedExpressions);
    }
}