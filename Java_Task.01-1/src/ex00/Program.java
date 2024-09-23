public class Program {
    public static void main(String[] args) {

        User john = new User(1, "John", 1000);
        User mike = new User(2, "Mike", 500);

        System.out.println("Initial balances:");
        System.out.println(john.getUsername() + ": $" + john.getBalance());
        System.out.println(mike.getUsername() + ": $" + mike.getBalance());

        Transaction transaction1 = new Transaction(john, mike, 500);

        System.out.println("\nAfter transaction:");
        System.out.println(john.getUsername() + ": $" + john.getBalance());
        System.out.println(mike.getUsername() + ": $" + mike.getBalance());
        System.out.println();
        System.out.println(transaction1.getSenderTransactionInfo());
        System.out.println(transaction1.getRecipientTransactionInfo());
    }
}
