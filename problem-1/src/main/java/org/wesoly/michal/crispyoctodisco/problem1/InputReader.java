package org.wesoly.michal.crispyoctodisco.problem1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class InputReader {

    private static final String GREETING = "Please input the K-number and at least 2 A-values. To finish, type any other char than number. Empty lines are ignored";
    private static final String ERROR_MESSAGE = "Given values are invalid. Please input K-Number and at least 2 A-values";
    private static final int MAX_NUMBER = 10000000;

    private Integer sumValue = null;
    private Integer maxIndex = 0;
    private IndexedNumber[] numbers = new IndexedNumber[MAX_NUMBER];

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
        maxIndex = 0;
        numbers = new IndexedNumber[MAX_NUMBER];
    }

    private void readValues(BufferedReader reader) throws IOException {
        try {
            sumValue = readLine(reader);
            while (true) { // NOSONAR
                Integer value = readLine(reader);
                IndexedNumber indexedNumber = new IndexedNumber(maxIndex, value);
                numbers[maxIndex] = indexedNumber;
                maxIndex++;
            }
        } catch (NumberFormatException ex) {
            // DO NOTHING
        } finally {
            maxIndex--; // negate the last incrementation in incomplete loop
        }
    }

    private Integer readLine(BufferedReader reader) throws IOException, NumberFormatException {
        String input = "";
        while (StringUtils.isBlank(input)) {
            input = reader.readLine();
        }

        return Integer.parseInt(input);
    }

    private boolean verify() {
        return Objects.nonNull(sumValue) && maxIndex >= 2;
    }

    private void print(String toPrint) {
        System.out.println(toPrint); // Sorry, I cannot bear the sight of System.out.println...
    }
}
