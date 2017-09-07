package com.codurance.bankkata;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

import static java.text.MessageFormat.format;
import static java.util.stream.Collectors.toCollection;

public class StatementPrinter {
    private static final String HEADER = "DATE | AMOUNT | BALANCE";
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String AMOUNT_FORMAT = "#.00";

    private Console console;

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void print(List<Transaction> transactions) {
        printHeader();
        printTransactions(transactions);
    }

    private void printTransactions(List<Transaction> transactions) {
        final AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                .map(transactionToLine(balance))
                .collect(toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(this::printLine);
    }

    private Function<Transaction, String> transactionToLine(AtomicInteger balance) {
        return transaction -> format("{0} | {1} | {2}", formatDate(transaction.date()), formatNumber(transaction.amount()), formatNumber(balance.addAndGet(transaction.amount())));
    }

    private String formatDate(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return dateFormatter.format(date);
    }

    private String formatNumber(int amount){
        DecimalFormat decimalFormat = new DecimalFormat(AMOUNT_FORMAT, DecimalFormatSymbols.getInstance(Locale.UK));
        return decimalFormat.format(amount);
    }

    private void printHeader() {
        printLine(HEADER);
    }

    private void printLine(String line) {
        console.printLine(line);
    }
}
