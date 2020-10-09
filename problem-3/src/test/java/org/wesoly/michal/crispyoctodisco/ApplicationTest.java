package org.wesoly.michal.crispyoctodisco;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ApplicationTest {

    @Test
    public void testAppHasAGreeting() {
        Application testing = new Application();
        assertThat(testing.getProject()).isNotBlank();
    }
}