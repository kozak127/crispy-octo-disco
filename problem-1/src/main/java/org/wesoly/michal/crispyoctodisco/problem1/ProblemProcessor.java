package org.wesoly.michal.crispyoctodisco.problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class ProblemProcessor {

    List<Pair<IndexedNumber, IndexedNumber>> process(Integer sumValue, IndexedNumber[] numbers) {

        Arrays.sort(numbers, IndexedNumber::compareTo);

        List<Pair<IndexedNumber, IndexedNumber>> result = new ArrayList<>();

        int sortedIndex = 0;
        int reverseSortedIndex = numbers.length - 1;

        while (sortedIndex < reverseSortedIndex) {
            while (isSumLowerThanSumValue(sumValue, numbers, sortedIndex, reverseSortedIndex)) {
                IndexedNumber left = numbers[sortedIndex];
                IndexedNumber right = numbers[reverseSortedIndex];
                if (isSumEqualSumValue(left, right, sumValue) && isLeftIndexLowerThanRightIndex(left, right)) {
                    result.add(Pair.of(left, right));
                }
                sortedIndex++;
            }
            while (isSumHigherThanSumValue(sumValue, numbers, sortedIndex, reverseSortedIndex)) {
                IndexedNumber left = numbers[sortedIndex];
                IndexedNumber right = numbers[reverseSortedIndex];
                if (isSumEqualSumValue(left, right, sumValue) && isLeftIndexLowerThanRightIndex(left, right)) {
                    result.add(Pair.of(left, right));
                }
                reverseSortedIndex--;
            }
        }
        return result;
    }

    private boolean isSumEqualSumValue(IndexedNumber left, IndexedNumber right, Integer sumValue) {
        return left.getValue() + right.getValue() == sumValue;
    }

    private boolean isLeftIndexLowerThanRightIndex(IndexedNumber left, IndexedNumber right) {
        return left.getIndex() < right.getIndex();
    }

    private boolean isSumHigherThanSumValue(Integer sumValue, IndexedNumber[] numbers, int index, int reverseIndex) {
        return numbers[index].getValue() + numbers[reverseIndex].getValue() >= sumValue && index < reverseIndex;
    }

    private boolean isSumLowerThanSumValue(Integer sumValue, IndexedNumber[] numbers, int index, int reverseIndex) {
        return numbers[index].getValue() + numbers[reverseIndex].getValue() <= sumValue && index < reverseIndex;
    }
}