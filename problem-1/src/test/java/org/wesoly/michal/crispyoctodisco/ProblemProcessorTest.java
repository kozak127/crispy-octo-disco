package org.wesoly.michal.crispyoctodisco;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ProblemProcessorTest {

    private final ProblemProcessor processor = new ProblemProcessor();

    //@Test
    public void shouldProcessGivenExamplesCorrectly() {

        // GIVEN
        List<Integer> numbers = List.of(2, 1, 4, 5, 3);
        Integer sumValue = 6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(2, 4), Pair.of(1, 5));
    }
}