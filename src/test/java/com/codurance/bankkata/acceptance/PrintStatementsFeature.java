package com.codurance.bankkata.acceptance;

import com.codurance.bankkata.Account;
import com.codurance.bankkata.Clock;
import com.codurance.bankkata.Console;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

    @InjectMocks
    private Account account;

    @Test
    public void print_statements(){
        given(clock.localDate()).willReturn(LocalDate.of(2014, 4, 1));
        given(clock.localDate()).willReturn(LocalDate.of(2014, 4, 2));
        given(clock.localDate()).willReturn(LocalDate.of(2014, 4, 10));
        account.deposit(1000);
        account.withdraw(100);
        account.deposit(500);

        account.printStatement();

        verify(console).println("DATE | AMOUNT | BALANCE");
        verify(console).println("10/04/2014 | 500.00  | 1400.00");
        verify(console).println("02/04/2014 | -100.00 | 900.00");
        verify(console).println("01/04/2014 | 1000.00 | 1000.00");
    }
}
