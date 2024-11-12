package banking;

import java.util.Date;

public class Transaction {
    private static int idCounter = 0;
    private int transactionID;
    private Date date;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.transactionID = ++idCounter;
        this.date = new Date();
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionID + ", Date: " + date + ", Type: " + type + ", Amount: " + amount;
    }
}
