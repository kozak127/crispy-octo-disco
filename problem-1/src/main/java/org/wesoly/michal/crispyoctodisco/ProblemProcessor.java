package org.wesoly.michal.crispyoctodisco;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ProblemProcessor {

    List<Pair<Integer, Integer>> process(Integer sumValue, List<Integer> numbers) {

        numbers.sort(Integer::compareTo);

        List<Pair<Integer, Integer>> result = new ArrayList<>();

        int index = 0;
        int reverseIndex = numbers.size() - 1;

        while (index < reverseIndex) {
            while (sumLowerThanSumValue(sumValue, numbers, index, reverseIndex)) {
                int firstValue = numbers.get(index);
                int secondValue = numbers.get(reverseIndex);
                if (firstValue + secondValue == sumValue) {
                    result.add(Pair.of(firstValue, secondValue));
                }
                index++;
            }
            while (sumHigherThanSumValue(sumValue, numbers, index, reverseIndex)) {
                int firstValue = numbers.get(index);
                int secondValue = numbers.get(reverseIndex);
                if (firstValue + secondValue == sumValue) {
                    result.add(Pair.of(firstValue, secondValue));
                }
                reverseIndex--;
            }
        }
        return result;
    }

    private boolean sumHigherThanSumValue(Integer sumValue, List<Integer> numbers, int index, int reverseIndex) {
        return numbers.get(index) + numbers.get(reverseIndex) >= sumValue && index < reverseIndex;
    }

    private boolean sumLowerThanSumValue(Integer sumValue, List<Integer> numbers, int index, int reverseIndex) {
        return numbers.get(index) + numbers.get(reverseIndex) <= sumValue && index < reverseIndex;
    }
}