package org.wesoly.michal.crispyoctodisco;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println(new Application().getProject());
    }

    public String getProject() {
        return "problem-3";
    }
}