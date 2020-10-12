package org.wesoly.michal.crispyoctodisco.problem2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class InputReader {

    private static final String GREETING = "Please input the expressions in Polish notation. Expressions can contain double-precision floating point numbers and the following operations: addition, subtraction, division and multiplication. To finish, type DONE. Empty lines are ignored";
    private static final String DONE = "DONE";

    public List<String> read() throws IOException {
        print(GREETING);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> expressions = new ArrayList<>();

        boolean done = false;
        while (!done) {
            String input = readLine(reader);
            if (DONE.equalsIgnoreCase(input)) {
                done = true;
            } else {
                expressions.add(input);
            }
        }
        return expressions;
    }

    private String readLine(BufferedReader reader) throws IOException {
        String input = "";
        while (StringUtils.isBlank(input)) {
            input = reader.readLine();
        }
        return input;
    }

    private void print(String toPrint) {
        System.out.println(toPrint); // Sorry, I cannot bear the sight of System.out.println...
    }
}
