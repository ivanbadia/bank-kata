package com.codurance.bankkata.acceptance;

import com.codurance.bankkata.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PrintStatementsFeature {

    @Mock
    private Clock clock;

    @Mock
    private Console console;

    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account(new InMemoryTransactionRepository(), clock, new StatementPrinter(console));
    }

    @Test
    public void print_statement_contains_all_transactions_in_descending_order(){
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 1));
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 2));
        given(clock.today()).willReturn(LocalDate.of(2014, 4, 10));
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        verify(console).printLine("DATE | AMOUNT | BALANCE");
        verify(console).printLine("10/04/2014 | 500.00  | 1400.00");
        verify(console).printLine("02/04/2014 | -100.00 | 900.00");
        verify(console).printLine("01/04/2014 | 1000.00 | 1000.00");
    }
}
