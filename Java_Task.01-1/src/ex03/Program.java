import java.sql.SQLOutput;

public class Program {
    public static void main(String[] args) {
        User user = new User("John" , 1000);
        User user1 = new User("Mike", 500);
        Transaction t1 = new Transaction(user, user1, 100);
        Transaction t2 = new Transaction(user, user1, 50);
        Transaction t3 = new Transaction(user1, user, 250);

        user.getTransactions().addTransaction(t1);
        user.getTransactions().addTransaction(t2);
        user.getTransactions().addTransaction(t3);
        user1.getTransactions().addTransaction(t1);
        user1.getTransactions().addTransaction(t2);
        user1.getTransactions().addTransaction(t3);


        Transaction[] transactions = user.getTransactions().toArray();
        for (Transaction transaction : transactions) {
            System.out.println(transaction.getSenderTransactionInfo());
            System.out.println(transaction.getRecipientTransactionInfo());
        }


        try {
            user.getTransactions().removeTransaction(t1.getTransactionId());
            System.out.println("Transaction with ID " + t1.getTransactionId() + " removed.");
            user.getTransactions().removeTransaction(t1.getTransactionId());
        } catch (TransactionNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}