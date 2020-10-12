package org.wesoly.michal.crispyoctodisco.expression;

import com.google.common.collect.MoreCollectors;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wesoly.michal.crispyoctodisco.expression.operation.Operation;

@Service
@RequiredArgsConstructor
public class ExpressionOperations {

    private final List<Operation> operations;

    public Optional<Operation> find(String item) {
        return operations.stream()
                .filter(operation -> operation.isApplicable(item))
                .collect(MoreCollectors.toOptional());
    }

}
