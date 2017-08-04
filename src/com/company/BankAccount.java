package com.company;



/**
 * Created by ryanfox on 8/1/17.
 */
public class BankAccount {

    private Person owner;
    private AccountType accountType;
    private double avalBalance;
    private double balance;

    public BankAccount(Person owner,AccountType accountType){

        this.owner = owner;
        this.accountType = accountType;
        this.balance = 0.0;
        this.avalBalance = 0.0;

    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public double getAvalBalance() {
        return avalBalance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "owner=" + owner.getFname() + owner.getLname() +
                ", accountType=" + accountType +
                ", avalBalance=" + avalBalance +
                ", balance=" + balance +
                '}';
    }
}
