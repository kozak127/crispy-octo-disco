package org.wesoly.michal.crispyoctodisco;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class ProblemProcessorTest {

    private final ProblemProcessor processor = new ProblemProcessor();

    @Test
    public void shouldProcessGivenExamplesCorrectly() {

        // GIVEN
        List<Integer> numbers = Arrays.asList(2, 1, 4, 5, 3);
        Integer sumValue = 6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(2, 4), Pair.of(1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWhenNumbersAreHigher() {

        // GIVEN
        List<Integer> numbers = Arrays.asList(2, 1, 4, 5, 3, 7, 10, 22);
        Integer sumValue = 6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(2, 4), Pair.of(1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWithNegativeNumbers() {

        // GIVEN
        List<Integer> numbers = Arrays.asList(2, 1, 4, 5, 3, -1, -2, 7);
        Integer sumValue = 6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(2, 4), Pair.of(1, 5), Pair.of(-1, 7));
    }

    @Test
    public void shouldProcessCorrectlyWithNegativeSumValue() {

        // GIVEN
        List<Integer> numbers = Arrays.asList(2, 1, -4, -5, 3, -1, -2, 7, 0, -7, 8, -6);
        Integer sumValue = -6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(-4, -2), Pair.of(-5, -1), Pair.of(-6, 0), Pair.of(-7, 1));
    }

    @Test
    public void shouldProcessCorrectlyWhenNumbersAreLower() {

        // GIVEN
        List<Integer> numbers = Arrays.asList(2, 1, 4, 5, 3, 7, 10, 22);
        Integer sumValue = 77;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).isEmpty();
    }

    @Test
    public void shouldProcessCorrectlyWithGreatRangeOfNumbersAndLowSumValue() {

        // GIVEN
        List<Integer> numbers = IntStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList());
        Integer sumValue = 6;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(Pair.of(2, 4), Pair.of(1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWithGreatRangeOfNumbersAndHighSumValue() {

        // GIVEN
        List<Integer> numbers = IntStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList());
        Integer sumValue = 5000000;

        // WHEN
        List<Pair<Integer, Integer>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).hasSize(2499999);
    }
}