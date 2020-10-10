package org.wesoly.michal.crispyoctodisco;

import com.google.common.collect.MoreCollectors;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wesoly.michal.crispyoctodisco.operation.Operation;

@Service
@RequiredArgsConstructor
public class Operations {

    private final List<Operation> operations;

    public Optional<Operation> find(String item) {
        return operations.stream()
                .filter(operation -> operation.isApplicable(item))
                .collect(MoreCollectors.toOptional());
    }

}
