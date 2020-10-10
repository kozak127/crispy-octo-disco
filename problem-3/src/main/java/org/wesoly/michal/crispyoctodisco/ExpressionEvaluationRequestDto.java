package org.wesoly.michal.crispyoctodisco;

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
public class ExpressionEvaluationRequestDto {

    private List<String> expressions = new ArrayList<>();
}
