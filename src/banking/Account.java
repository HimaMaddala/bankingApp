package banking;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    private String accountNumber;
    private String holderName;
    private String type;
    private double balance;
    private ArrayList<Transaction> transactions;
    private static final double INTEREST_RATE = 0.03;

    public Account(String accountNumber, String holderName, String type, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.type = type;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount));
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    public void generateStatement() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public double checkBalance() {
        return balance;
    }

    public void applyInterest() {
        if ("savings".equals(type)) {
            double interest = balance * INTEREST_RATE;
            balance += interest;
            transactions.add(new Transaction("interest", interest));
        }
    }
    public String getAccountNumber() {
        return accountNumber;
    }

}
