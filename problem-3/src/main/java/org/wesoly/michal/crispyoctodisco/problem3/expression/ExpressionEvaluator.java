package org.wesoly.michal.crispyoctodisco.problem3.expression;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wesoly.michal.crispyoctodisco.problem3.expression.operation.Operation;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpressionEvaluator {

    private static final String ERROR = "error";
    private static final String FORMAT = "%.2f";

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
        } catch (EmptyStackException ex) {
            log.warn("Problem evaluating expression: {}", expression);
            return ERROR;
        } catch (IllegalArgumentException ex) {
            log.warn("Couldn't find operations to evaluate expression: {}", expression);
            return ERROR;
        }

        if (values.size() > 1 || values.empty()) {
            log.warn("Problem evaluating expression: {}", expression);
            return ERROR;
        }

        String result = String.format(FORMAT, values.peek());
        log.debug("Successfully evaluated expression: {} with result: {}", expression, result);
        return result;
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
