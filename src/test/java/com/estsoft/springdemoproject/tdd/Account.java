package com.estsoft.springdemoproject.tdd;

public class Account {
    private int balance;

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) {
        if(this.balance >= money) {
            this.balance -= money;
        }
    }
}
