package com.codurance.bankkata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleShould{

    private final Console console = new Console();
    private ByteArrayOutputStream consoleOutput;

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void setUp() throws Exception {
        consoleOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(consoleOutput);
        System.setOut(printStream);
    }

    @After
    public void setOut(){
        System.setOut(System.out);
    }

    @Test
    public void print_line(){
        String line = "10/04/2014 | 500.00 | 1400.00";

        console.printLine(line);

        assertThat(consoleOutput.toString()).isEqualTo(line+LINE_SEPARATOR);
    }

}