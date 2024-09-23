import java.util.List;

public class Program {
    public static void main(String[] args) {
        UsersList usersList = new UsersArrayList();
        TransactionsService transactionsService = new TransactionsService(usersList);

        User jonh = new User("Jonh", 500);
        User mike = new User("Mike", 300);

        transactionsService.addUser(jonh);
        transactionsService.addUser(mike);

        System.out.println("Jonh's Balance: " + transactionsService.getUserBalance(jonh.getId()));
        System.out.println("Mike's Balance: " + transactionsService.getUserBalance(mike.getId()));

        Transaction t1 = null;
        try {
            t1 = transactionsService.transferFunds(jonh.getId(), mike.getId(), 300);
            System.out.println("The transfer is completed");
        } catch (IllegalArgumentException | IllegalTransactionException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Jonh's Balance after transfer: " + transactionsService.getUserBalance(jonh.getId()));
        System.out.println("Mike's Balance after transfer: " + transactionsService.getUserBalance(mike.getId()));

        Transaction[] transfers = transactionsService.getUserTransfers(jonh.getId());
        for (Transaction transfer : transfers) {
            System.out.println(transfer.getSenderTransactionInfo());
        }

        if (t1 != null) {
            transactionsService.removeTransactionForUser(jonh.getId(), t1.getTransactionId());
        }

        List<Transaction> UnmatchedTransactions =  transactionsService.checkUnmatchedTransactions();
        for (Transaction t: UnmatchedTransactions) {
            System.out.println(t.getRecipient().getUsername()+ "(id = " + t.getRecipient().getId() + ") has an unacknowledged transfer id = " + t.getTransactionId() + " from " + t.getSender().getUsername()+ "(id = " + t.getSender().getId() + ") for " + t.getAmount());
        }




    }
}
