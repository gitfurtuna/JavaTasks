public class User {
    private int id;
    private String username;
    private int balance;

    public User(String username, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.id = UserIdsGenerator.getInstance().generateId();
        this.username = username;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        if (this.balance + amount < 0) {
            throw new IllegalArgumentException("Insufficient funds for this transaction");
        }
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", name: " + username + ", balance: " + balance;
    }

}
