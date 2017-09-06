package com.codurance.bankkata;

import java.util.List;

public interface TransactionRepository {
    void add(Transaction transaction);

    List<Transaction> all();
}
