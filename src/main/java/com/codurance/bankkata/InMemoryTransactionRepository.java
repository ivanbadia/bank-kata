package com.codurance.bankkata;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class InMemoryTransactionRepository implements TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> all() {
        return unmodifiableList(transactions);
    }
}
