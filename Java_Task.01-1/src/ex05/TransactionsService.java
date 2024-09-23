import java.util.ArrayList;
import java.util.List;

public class TransactionsService  {

        private UsersList users;

        public TransactionsService(UsersList users) {
            this.users = users;
            }

        public void addUser(User user) {
            users.addUser(user);
            }

        public int getUserBalance(int userId) {
            User user = users.getUserById(userId);
            return user.getBalance();
             }

             public String getUsernameById(int userId) {
                 User user = users.getUserById(userId);
                 return user.getUsername();
             }


             public Transaction transferFunds(int senderId, int recipientId, int amount) {
            User sender = users.getUserById(senderId);
            User recipient = users.getUserById(recipientId);

            Transaction transaction = new Transaction(sender, recipient, amount);
            sender.getTransactions().addTransaction(transaction);
            recipient.getTransactions().addTransaction(transaction);
             return transaction;
            }

            public Transaction[] getUserTransfers(int userId) {
            User user = users.getUserById(userId);
            return user.getTransactions().toArray();
             }

            public void removeTransactionForUser(int userId, String transactionId) {
                User user = users.getUserById(userId);
                Transaction[] transactions = user.getTransactions().toArray();
                for (Transaction transaction : transactions) {
                    if (transaction.getSender().equals(user) && transaction.getTransactionId().equals(transactionId)) {
                        user.getTransactions().removeTransaction(transactionId);
                        System.out.println("Transfer To " + transaction.getRecipient().getUsername() + "(id = " + transaction.getRecipient().getId() + ") " + transaction.getAmount() + " removed");
                    }
                }
            }

             public List<Transaction> checkUnmatchedTransactions() {
             List<Transaction> unmatchedTransactions = new ArrayList<>();
                 for (int i = 0; i < users.getUserCount(); i++) {
                     int counter = 0;
                     User user = users.getUserByIndex(i);
                     Transaction[] transactions = user.getTransactions().toArray();
                     for (Transaction transaction : transactions) {
                         if (transaction.getSender().equals(user)) {
                             User user1 = transaction.getRecipient();
                             String transactionID = transaction.getTransactionId();
                             Transaction[] transactions1 = user1.getTransactions().toArray();
                             for (Transaction transaction1 : transactions1) {
                                 if (transaction1.getTransactionId().equals(transactionID)) {
                                     counter++;
                                 }
                             }
                             if (counter == 0) {
                                 unmatchedTransactions.add(transaction);
                             }
                         }
                     }
                 }

                 return unmatchedTransactions;
             }

    }



