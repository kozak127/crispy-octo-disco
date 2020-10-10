package org.wesoly.michal.crispyoctodisco;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputReaderTest {

    private final InputReader inputReader = new InputReader();

    private InputStream stdinBackup;

    @Before
    public void before() {
        stdinBackup = System.in; // backup System.in to restore it later
    }

    @After
    public void after() {
        System.setIn(stdinBackup);
    }

    @Test
    public void shouldProcessGivenExamplesCorrectly() throws IOException {

        // GIVEN
        String expression1 = "+ + 0.5 1.5 * 4 10";
        String expression2 = "- 2e3 - 700 + 7 * 2 15";
        String expression3 = "- -1.5 * 3.1415 / -7 -2";
        String expression4 = "100500";
        String expression5 = "1 2";
        String expression6 = "+ 1";

        String example = expression1 + "\n" +
                expression2 + "\n"
                + expression3 + "\n"
                + expression4 + "\n"
                + expression5 + "\n"
                + expression6 + "\n"
                + "DONE" + "\n";

        ByteArrayInputStream in = new ByteArrayInputStream(example.getBytes());
        System.setIn(in);

        // WHEN
        List<String> expressions = inputReader.read();

        // THEN
        assertThat(expressions).containsExactly(expression1, expression2, expression3, expression4, expression5, expression6);
    }

    @Test
    public void shouldProcessGivenExamplesCorrectlyWithEmptyLines() throws IOException {

        String expression1 = "+ + 0.5 1.5 * 4 10";
        String expression2 = "- 2e3 - 700 + 7 * 2 15";
        String expression3 = "- -1.5 * 3.1415 / -7 -2";
        String expression4 = "100500";
        String expression5 = "1 2";
        String expression6 = "+ 1";

        // GIVEN
        String example = expression1 + "\n"
                + "\n"
                + expression2 + "\n"
                + expression3 + "\n"
                + expression4 + "\n"
                + expression5 + "\n"
                + "\n"
                + expression6 + "\n"
                + "DONE" + "\n";

        ByteArrayInputStream in = new ByteArrayInputStream(example.getBytes());
        System.setIn(in);

        // WHEN
        List<String> expressions = inputReader.read();

        // THEN
        assertThat(expressions).containsExactly(expression1, expression2, expression3, expression4, expression5, expression6);
    }

}