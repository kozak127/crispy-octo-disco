package org.wesoly.michal.crispyoctodisco;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ExpressionEvaluator {

    private static final String ERROR = "error";

    private final Operations operations = new Operations();

    public List<String> evaluate(List<String> expressions) {
        return expressions.stream()
                .map(this::evaluate)
                .collect(Collectors.toList());
    }

    private String evaluate(String expression) {
        Stack<Float> values = new Stack<>();
        List<String> items = splitExpression(expression);

        try {
            evaluateItems(values, items);
        } catch (EmptyStackException ex) {
            return ERROR;
        }

        if (values.size() > 1 || values.empty()) {
            return ERROR;
        }

        return String.format("%.2f", values.peek());
    }

    private List<String> splitExpression(String expression) {
        List<String> items = List.of(expression.split(" "));
        items = items.stream()
                .map(item -> item.replaceAll("\\s+", ""))
                .collect(Collectors.toList());
        return items;
    }

    private void evaluateItems(Stack<Float> values, List<String> items) throws EmptyStackException {
        for (String item : items) {
            Operation operation = operations.find(item);
            operation.apply(values, item);
        }
    }


}
