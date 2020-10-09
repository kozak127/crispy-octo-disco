package org.wesoly.michal.crispyoctodisco;

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

    private Integer sumValue = null;
    private List<Integer> numbers = new ArrayList<>();

    public void read() {
        clear();
        print("Please input the K-number and at least 2 A-values. To finish, type any other char than number. Empty lines are ignored");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean valid = false;
        while (!valid) {
            readValues(reader);
            valid = verify();
            if (!valid) {
                print("Given values are invalid. Please input K-Number and at least 2 A-values");
                clear();
            }
        }

        print("Thank you");
    }

    private void clear() {
        sumValue = null;
        numbers = new ArrayList<>();
    }

    private void readValues(BufferedReader reader) {
        try {
            sumValue = readLine(reader);
            while (true) { // NOSONAR
                numbers.add(readLine(reader));
            }
        } catch (NumberFormatException | IOException ex) {
            // DO NOTHING
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
        return Objects.nonNull(sumValue) && numbers.size() >= 2;
    }

    private void print(String toPrint) {
        System.out.println(toPrint); // Sorry, I cannot bear the sight of System.out.println...
    }
}
