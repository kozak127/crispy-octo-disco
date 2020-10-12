package org.wesoly.michal.crispyoctodisco.expression;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expression")
@RequiredArgsConstructor
@Slf4j
public class ExpressionController {

    private final ExpressionEvaluator evaluator;

    @PostMapping
    public ResponseEntity<ExpressionEvaluationResponseDto> evaluateExpression(@RequestBody ExpressionEvaluationRequestDto requestDto) {
        List<String> expressions = requestDto.getExpressions();
        List<String> evaluatedExpressions = evaluator.evaluate(expressions);
        ExpressionEvaluationResponseDto responseDto = new ExpressionEvaluationResponseDto(evaluatedExpressions);
        return createSuccessfulResponse(responseDto);
    }

    private ResponseEntity<ExpressionEvaluationResponseDto> createSuccessfulResponse(ExpressionEvaluationResponseDto body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(body);
    }
}
