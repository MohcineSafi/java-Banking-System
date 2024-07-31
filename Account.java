import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String ownerUsername;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountId, String ownerUsername, double initialBalance) {
        this.accountId = accountId;
        this.ownerUsername = ownerUsername;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(Account toAccount, double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            toAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + toAccount.getAccountId(), amount));
            toAccount.addTransaction(new Transaction("Transfer from " + this.accountId, amount));
            return true;
        } else {
            return false;
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    private void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }
}
