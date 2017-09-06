package com.codurance.bankkata;

import java.util.List;

public class StatementPrinter {
    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
    }

    private void printHeader() {
        console.printLine(HEADER);
    }
}
