package org.wesoly.michal.crispyoctodisco.expression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@RunWith(SpringJUnit4ClassRunner.class)
public class ExpressionIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldProcessSimpleNumbersCorrectly() throws Exception {

        // GIVEN
        String expression1 = "7 4 + 3 -";
        String expression2 = "1 2 * 3 +";
        String expression3 = "1 2 + 3 *";
        String expression4 = "5 9 2 * +";
        String expression5 = "1 2 * 3 4 * +";
        String expression6 = "+ 1";

        List<String> expressions = List.of(expression1, expression2, expression3, expression4, expression5, expression6);
        ExpressionEvaluationRequestDto requestDto = new ExpressionEvaluationRequestDto(expressions);

        // WHEN
        MvcResult mvcResult = mockMvc.perform(post("/api/expression")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andReturn();

        // THEN
        String result1 = "8.00";
        String result2 = "5.00";
        String result3 = "9.00";
        String result4 = "23.00";
        String result5 = "14.00";
        String result6 = "error";

        ExpressionEvaluationResponseDto responseDto = objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(), ExpressionEvaluationResponseDto.class);

        assertThat(responseDto.getEvaluatedExpressions()).containsExactly(result1, result2, result3, result4, result5, result6);
    }

    @Test
    public void shouldProcessFloatNumbersCorrectly() throws Exception {

        // GIVEN
        String expression1 = "7.5 4.5 + 3 -";
        String expression2 = "1.1 2.5 * 3.3 +";
        String expression3 = "1.3 2.4 + 3.5 *";
        String expression4 = "5.4 9.7 2.9 * +";
        String expression5 = "1.67 2.22 * 3.88 4.9909 * +";
        String expression6 = "+ 1.9887";

        List<String> expressions = List.of(expression1, expression2, expression3, expression4, expression5, expression6);
        ExpressionEvaluationRequestDto requestDto = new ExpressionEvaluationRequestDto(expressions);

        // WHEN
        MvcResult mvcResult = mockMvc.perform(post("/api/expression")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andReturn();

        // THEN
        String result1 = "9.00";
        String result2 = "6.05";
        String result3 = "12.95";
        String result4 = "33.53";
        String result5 = "23.07";
        String result6 = "error";

        ExpressionEvaluationResponseDto responseDto = objectMapper
                .readValue(mvcResult.getResponse().getContentAsString(), ExpressionEvaluationResponseDto.class);

        assertThat(responseDto.getEvaluatedExpressions()).containsExactly(result1, result2, result3, result4, result5, result6);
    }
}