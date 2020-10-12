package org.wesoly.michal.crispyoctodisco.expression;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wesoly.michal.crispyoctodisco.expression.operation.Operation;

@Service
@RequiredArgsConstructor
public class ExpressionEvaluator {

    private static final String ERROR = "error";

    private final ExpressionOperations operations;

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
        } catch (EmptyStackException | IllegalArgumentException ex) {
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

    private void evaluateItems(Stack<Float> values, List<String> items) throws EmptyStackException, IllegalArgumentException {
        for (String item : items) {
            Operation operation = operations.find(item).orElseThrow(IllegalArgumentException::new);
            operation.apply(values, item);
        }
    }
}
