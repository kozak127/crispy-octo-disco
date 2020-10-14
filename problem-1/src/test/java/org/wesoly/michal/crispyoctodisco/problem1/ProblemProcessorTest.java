package org.wesoly.michal.crispyoctodisco.problem1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
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
        List<IndexedNumber> numbers = createIndexedNumbers(List.of(2, 1, 4, 5, 3));
        Integer sumValue = 6;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(resultPair(numbers, 2, 4), resultPair(numbers, 1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWhenNumbersAreHigher() {

        // GIVEN
        List<IndexedNumber> numbers = createIndexedNumbers(List.of(2, 1, 4, 5, 3, 7, 10, 22));
        Integer sumValue = 6;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(resultPair(numbers, 2, 4), resultPair(numbers, 1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWithNegativeNumbers() {

        // GIVEN
        List<IndexedNumber> numbers = createIndexedNumbers(List.of(2, 1, 4, 5, 3, -1, -2, 7));
        Integer sumValue = 6;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(resultPair(numbers, 2, 4), resultPair(numbers, 1, 5), resultPair(numbers, -1, 7));
    }

    @Test
    public void shouldProcessCorrectlyWithNegativeSumValue() {

        // GIVEN
        List<IndexedNumber> numbers = createIndexedNumbers(List.of(2, 1, -4, -5, 3, -1, -2, 7, 0, -7, 8, -6));
        Integer sumValue = -6;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(resultPair(numbers, -4, -2), resultPair(numbers, -5, -1));
    }

    @Test
    public void shouldProcessCorrectlyWhenNumbersAreLower() {

        // GIVEN
        List<IndexedNumber> numbers = createIndexedNumbers(List.of(2, 1, 4, 5, 3, 7, 10, 22));
        Integer sumValue = 77;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).isEmpty();
    }

    @Test
    public void shouldProcessCorrectlyWithGreatRangeOfNumbersAndLowSumValue() {

        // GIVEN
        //List<IndexedNumber> numbers = createIndexedNumbers(IntStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList()));
        List<IndexedNumber> numbers = createIndexedNumbers(IntStream.rangeClosed(1, 5000000).boxed().collect(Collectors.toList()));

        Integer sumValue = 6;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).containsExactlyInAnyOrder(resultPair(numbers, 2, 4), resultPair(numbers, 1, 5));
    }

    @Test
    public void shouldProcessCorrectlyWithGreatRangeOfNumbersAndHighSumValue() {

        // GIVEN
        //List<IndexedNumber> numbers = createIndexedNumbers(IntStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList()));

        List<IndexedNumber> numbers = createIndexedNumbers(IntStream.rangeClosed(1, 5000000).boxed().collect(Collectors.toList()));
        Integer sumValue = 2500000;

        // WHEN
        List<Pair<IndexedNumber, IndexedNumber>> pairs = processor.process(sumValue, numbers);

        // THEN
        assertThat(pairs).hasSize(1249999);
    }

    private Pair<IndexedNumber, IndexedNumber> resultPair(List<IndexedNumber> numbers, int leftValue, int rightValue) {
        IndexedNumber left = numbers.stream()
                .filter(number -> number.getValue() == leftValue)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        IndexedNumber right = numbers.stream()
                .filter(number -> number.getValue() == rightValue)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return Pair.of(left, right);
    }

    private List<IndexedNumber> createIndexedNumbers(List<Integer> numbers) {
        List<IndexedNumber> toReturn = new ArrayList<>();
        for (int index = 0; index < numbers.size(); index++) {
            Integer number = numbers.get(index);
            toReturn.add(new IndexedNumber(index, number));
        }
        return toReturn;
    }
}