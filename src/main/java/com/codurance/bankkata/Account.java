package com.codurance.bankkata;

import java.util.List;

public class Account implements InterfaceAccount{

    private TransactionRepository transactionRepository;
    private Clock clock;
    private StatementPrinter statementPrinter;
    

    public Account(TransactionRepository transactionRepository, Clock clock, StatementPrinter statementPrinter) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    @Override
    public void deposit(int amount) {
        ValidAmount.validateAmount(amount);
        transactionRepository.add(transaction(amount));
    }

    @Override
    public void withdraw(int amount) {
        ValidAmount.validateAmount(amount);
        transactionRepository.add(transaction(-amount));
    }

    public void printStatement() {
        List<Transaction> transactions = transactionRepository.all();
        statementPrinter.print(transactions);
    }

    private Transaction transaction(int amount) {
        return new Transaction(clock.today(), amount);
    }
}
