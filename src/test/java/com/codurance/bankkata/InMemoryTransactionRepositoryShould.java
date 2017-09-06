package com.codurance.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryTransactionRepositoryShould {

    private static final Transaction TRANSACTION = new Transaction(LocalDate.of(2016, 9, 6), 1000);
    private final TransactionRepository transactionRepository = new InMemoryTransactionRepository();

    @Test
    public void store_transaction(){
        transactionRepository.add(TRANSACTION);

        assertThat(transactionRepository.all()).contains(TRANSACTION);
    }

}