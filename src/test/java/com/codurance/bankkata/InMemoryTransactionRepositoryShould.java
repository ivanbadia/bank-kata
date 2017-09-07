package com.codurance.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTransactionRepositoryShould {

    private final TransactionRepository transactionRepository = new InMemoryTransactionRepository();

    @Test
    public void store_transaction(){
        Transaction firstTransaction = new Transaction(LocalDate.of(2016, 9, 6), 1000);
        transactionRepository.add(firstTransaction);
        Transaction secondTransaction = new Transaction(LocalDate.of(2016, 9, 7), -1000);
        transactionRepository.add(secondTransaction);

        assertThat(transactionRepository.all())
                .isEqualTo(asList(firstTransaction, secondTransaction));
    }

}