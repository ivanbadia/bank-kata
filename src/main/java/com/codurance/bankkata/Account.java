package com.codurance.bankkata;

public class Account {
    public void deposit(int amount) {
        throw new IllegalArgumentException("The amount must not be greater than 0");
    }

    public void withdraw(int amount) {
        throw new UnsupportedOperationException();
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
