package org.wesoly.michal.crispyoctodisco.expression;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionEvaluationResponseDto {

    private List<String> evaluatedExpressions = new ArrayList<>();
}