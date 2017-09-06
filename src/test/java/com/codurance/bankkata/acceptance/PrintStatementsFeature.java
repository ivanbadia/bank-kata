package com.codurance.bankkata.acceptance;

import com.codurance.bankkata.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementsFeature {

    @Mock
    private Clock clock;

    private ByteArrayOutputStream consoleOutput;

    private Account account;

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void setUp() throws Exception {
        account = new Account(new InMemoryTransactionRepository(), clock, new StatementPrinter(new Console()));
        consoleOutput = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(consoleOutput);
        System.setOut(printStream);
    }

    @After
    public void setOut(){
        System.setOut(System.out);
    }

    @Test
    public void print_statement_contains_all_transactions_in_descending_order(){
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 1));
        account.deposit(1000);
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 2));
        account.withdraw(100);
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 10));
        account.deposit(500);

        account.printStatement();

        String expectedStatement =  "DATE | AMOUNT | BALANCE" + LINE_SEPARATOR +
                                    "10/04/2014 | 500.00 | 1400.00" + LINE_SEPARATOR +
                                    "02/04/2014 | -100.00 | 900.00" + LINE_SEPARATOR +
                                    "01/04/2014 | 1000.00 | 1000.00" + LINE_SEPARATOR;
        assertThat(consoleOutput.toString()).isEqualTo(expectedStatement);
    }
}
