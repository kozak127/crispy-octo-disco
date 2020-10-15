package org.wesoly.michal.crispyoctodisco.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class InputReader {

    private static final String GREETING = "Please input the K-number and at least 2 A-values. To finish, type any other char than number. Empty lines are ignored";
    private static final String ERROR_MESSAGE = "Given values are invalid. Please input K-Number and at least 2 A-values";

    private Integer sumValue = null;
    private IndexedNumber[] numbers = null;

    public void read() throws IOException {
        clear();
        print(GREETING);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean valid = false;
        while (!valid) {
            readValues(reader);
            valid = verify();
            if (!valid) {
                print(ERROR_MESSAGE);
                clear();
            }
        }
    }

    private void clear() {
        sumValue = null;
        numbers = null;
    }

    private void readValues(BufferedReader reader) throws IOException {
        List<Integer> inputNumbers = new ArrayList<>();
        try {
            sumValue = readLine(reader);
            while (true) { // NOSONAR
                Integer value = readLine(reader);
                inputNumbers.add(value);
            }
        } catch (NumberFormatException ex) {
            // DO NOTHING
        } finally {
            numbers = convertToIndexedNumberArray(inputNumbers);
        }
    }

    private Integer readLine(BufferedReader reader) throws IOException, NumberFormatException {
        String input = "";
        while (StringUtils.isBlank(input)) {
            input = reader.readLine();
        }

        return Integer.parseInt(input);
    }

    private IndexedNumber[] convertToIndexedNumberArray(List<Integer> input) {
        IndexedNumber[] numbers = new IndexedNumber[input.size()];
        for (int index = 0; index < input.size(); index++) {
            numbers[index] = new IndexedNumber(index, input.get(index));
        }
        return numbers;
    }

    private boolean verify() {
        return Objects.nonNull(sumValue) && Objects.nonNull(numbers) && numbers.length >= 2;
    }

    private void print(String toPrint) {
        System.out.println(toPrint); // Sorry, I cannot bear the sight of System.out.println...
    }
}
