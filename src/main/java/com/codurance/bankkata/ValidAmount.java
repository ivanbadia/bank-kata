package com.codurance.bankkata;

public class ValidAmount {
    public static void validateAmount(int amount){
        if(amount<=0) {
            throw new IllegalArgumentException("The amount must be greater than 0");
        }
    }
}