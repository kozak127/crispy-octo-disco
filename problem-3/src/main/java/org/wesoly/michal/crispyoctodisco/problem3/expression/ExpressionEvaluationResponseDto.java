package org.wesoly.michal.crispyoctodisco.problem3.expression;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpressionEvaluationResponseDto {

    private List<String> evaluatedExpressions = new ArrayList<>();
}
