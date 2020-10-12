package org.wesoly.michal.crispyoctodisco.problem1;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

public class Application {

    public static void main(String[] args) throws IOException {

        InputReader inputReader = new InputReader();

        inputReader.read();
        Integer sumValue = inputReader.getSumValue();
        List<Integer> numbers = inputReader.getNumbers();

        ProblemProcessor processor = new ProblemProcessor();

        List<Pair<Integer, Integer>> result = processor.process(sumValue, numbers);
        System.out.println("Found pairs: " + result.size());
    }
}