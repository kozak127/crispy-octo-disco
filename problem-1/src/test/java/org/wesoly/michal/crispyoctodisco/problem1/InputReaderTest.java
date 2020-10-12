package org.wesoly.michal.crispyoctodisco.problem1;

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
        String example = "6\n2\n1\n4\n5\n3\nA";

        ByteArrayInputStream in = new ByteArrayInputStream(example.getBytes());
        System.setIn(in);

        // WHEN
        inputReader.read();
        Integer sumValue = inputReader.getSumValue();
        List<Integer> numbers = inputReader.getNumbers();

        // THEN
        assertThat(sumValue).isEqualTo(6);
        assertThat(numbers).containsExactly(2, 1, 4, 5, 3);
    }

    @Test
    public void shouldProcessGivenExamplesCorrectlyWithEmptyLines() throws IOException {

        // GIVEN
        String example = "6\n\n2\n1\n4\n\n5\n3\nA";

        ByteArrayInputStream in = new ByteArrayInputStream(example.getBytes());
        System.setIn(in);

        // WHEN
        inputReader.read();
        Integer sumValue = inputReader.getSumValue();
        List<Integer> numbers = inputReader.getNumbers();

        // THEN
        assertThat(sumValue).isEqualTo(6);
        assertThat(numbers).containsExactly(2, 1, 4, 5, 3);
    }
}
