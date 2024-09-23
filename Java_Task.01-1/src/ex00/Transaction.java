import java.util.UUID;

public class Transaction {
    private String transactionId;
    private User sender;
    private User recipient;
    private String category;
    private int amount;


    public Transaction(User sender, User recipient, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        }
        this.transactionId = UUID.randomUUID().toString();
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.category = "OUTCOME";


        sender.updateBalance(-amount);
        recipient.updateBalance(amount);
    }


    public String getTransactionId() {
        return transactionId;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getSenderTransactionInfo() {
        return sender.getUsername() + " -> " + recipient.getUsername() + ", " + (-amount) + ", " + category + ", Transaction ID: " + transactionId;
    }

    public String getRecipientTransactionInfo() {
        return recipient.getUsername() + " -> " + sender.getUsername() + ", +" + amount + ", INCOME, Transaction ID: " + transactionId;
    }
}