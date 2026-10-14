import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BankAccountWithHistory {
    private final String accountNumber;
    private double balance;
    // EXPLAIN: private mutable collection containing transaction logs
    private final List<String> transactionHistory = new ArrayList<>();

    public BankAccountWithHistory(String accountNumber, double initialBalance) {
        if (initialBalance < 0) throw new IllegalArgumentException("Initial balance cannot be negative");
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        transactionHistory.add("Account opened with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
        transactionHistory.add("Deposited: " + amount + " | New Balance: " + balance);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > balance) {
            transactionHistory.add("Failed withdrawal attempt of: " + amount);
            return false;
        }
        balance -= amount;
        transactionHistory.add("Withdrew: " + amount + " | New Balance: " + balance);
        return true;
    }

    // EXPLAIN: Avoids the defensive-copy trap by returning an unmodifiable view
    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public static void main(String[] args) {
        BankAccountWithHistory acc = new BankAccountWithHistory("ACC-100", 200.0);
        acc.deposit(150.0);
        acc.withdraw(50.0);

        System.out.println("--- Transaction History ---");
        for (String log : acc.getTransactionHistory()) {
            System.out.println(log);
        }

        // Attempting to modify the unmodifiable view will throw an UnsupportedOperationException
        try {
            acc.getTransactionHistory().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println("\nEncapsulation safe: External code cannot clear transaction history!");
        }
    }
}