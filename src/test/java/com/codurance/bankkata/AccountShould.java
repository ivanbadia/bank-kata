package com.codurance.bankkata;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    private static final int POSITIVE_AMOUNT = 100;
    private static final int NEGATIVE_AMOUNT = -POSITIVE_AMOUNT;
    private static final LocalDate TODAY = LocalDate.of(2017, 9, 6);
    private static final List<Transaction> TRANSACTIONS = Arrays.asList(new Transaction(LocalDate.of(2017, 1, 1), 10), new Transaction(LocalDate.of(2017, 5, 6), 34));

    @Mock
    private Clock clock;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private StatementPrinter statementPrinter;

    private Account account;

    @Before
    public void setUp()  {
        account = new Account(transactionRepository, clock, statementPrinter);
        given(clock.today()).willReturn(TODAY);
    }

    @Test
    public void validate_that_the_deposit_amount_is_positive(){

        Throwable throwable = catchThrowable(() -> account.deposit(NEGATIVE_AMOUNT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void deposit_amount_into_the_account(){

        account.deposit(POSITIVE_AMOUNT);

        verify(transactionRepository).add(new Transaction(TODAY, POSITIVE_AMOUNT));
    }

    @Test
    public void validate_that_the_withdraw_amount_is_positive(){

        Throwable throwable = catchThrowable(() -> account.withdraw(NEGATIVE_AMOUNT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void withdraw_amount_from_the_account(){

        account.withdraw(POSITIVE_AMOUNT);

        verify(transactionRepository).add(new Transaction(TODAY, NEGATIVE_AMOUNT));
    }

    @Test
    public void print_statement(){

        given(transactionRepository.all()).willReturn(TRANSACTIONS);

        account.printStatement();

        verify(statementPrinter).print(TRANSACTIONS);
    }
}
