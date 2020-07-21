package com.codurance.bankkata;

import java.util.List;

public interface TransactionRepository {
    public void add(Transaction transaction);

    public List<Transaction> all();
}
