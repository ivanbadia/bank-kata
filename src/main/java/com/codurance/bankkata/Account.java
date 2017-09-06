package com.codurance.bankkata;

public class Account {

    private TransactionRepository transactionRepository;
    private Clock clock;

    public Account(TransactionRepository transactionRepository, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(int amount) {
        validateAmount(amount);

        transactionRepository.add(new Transaction(clock.today(), amount));
    }

    private void validateAmount(int amount) {
        if(amount<=0) {
            throw new IllegalArgumentException("The amount must not be greater than 0");
        }
    }

    public void withdraw(int amount) {
        throw new UnsupportedOperationException();
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
