import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService transactionsService;
    private boolean isDev;

    public Menu(TransactionsService transactionsService, boolean isDev) {
        this.transactionsService = transactionsService;
        this.isDev = isDev;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayMenu();
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        System.out.println("Enter a user name and a balance");
                        System.out.print("-> ");
                        String userInput = scanner.nextLine();
                        String[] userParts = userInput.split(" ");
                        String name = userParts[0];
                        int balance = Integer.parseInt(userParts[1]);
                        User first = new User(name, balance);
                        transactionsService.addUser(first);
                        System.out.println("User with id = " + first.getId() + " is added");
                        break;
                    case 2:
                        System.out.println("Enter a user ID");
                        System.out.print("-> ");
                        String idInput = scanner.nextLine();
                        int id = Integer.parseInt(idInput);
                        System.out.println(transactionsService.getUsernameById(id) + " - " + transactionsService.getUserBalance(id));
                        break;
                    case 3:
                        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                        System.out.print("-> ");
                        String transferInput = scanner.nextLine();
                        String[] transferParts = transferInput.split(" ");
                        int senderId = Integer.parseInt(transferParts[0]);
                        int recipientId = Integer.parseInt(transferParts[1]);
                        int amount = Integer.parseInt(transferParts[2]);
                        transactionsService.transferFunds(senderId, recipientId, amount);
                        System.out.println("The transfer is completed");
                        break;
                    case 4:
                        System.out.println("Enter a user ID");
                        System.out.print("-> ");
                        int userIdForTransactions = Integer.parseInt(scanner.nextLine());
                        Transaction[] userTransactions = transactionsService.getUserTransfers(userIdForTransactions);
                        for (Transaction transaction : userTransactions) {
                            System.out.println(transaction.getSenderTransactionInfo());
                        }
                        break;
                    case 5:
                        if (isDev) {
                            System.out.println("Enter a user ID and a transfer ID");
                            System.out.print("-> ");
                            String removeInput = scanner.nextLine();
                            String[] removeParts = removeInput.split(" ");
                            int removeUserId = Integer.parseInt(removeParts[0]);
                            String transferId = removeParts[1];
                            transactionsService.removeTransactionForUser(removeUserId,transferId);
                        } else {
                            System.out.println("This option is not available in production mode.");
                        }
                        break;
                    case 6:
                        if (isDev) {
                            System.out.println("Check results:");
                            List<Transaction> UnmatchedTransactions =  transactionsService.checkUnmatchedTransactions();
                            if (UnmatchedTransactions.isEmpty()) {
                                System.out.println("All transactions are paired");
                            } else {
                                for (Transaction t : UnmatchedTransactions) {
                                    System.out.println(t.getRecipient().getUsername() + "(id = " + t.getRecipient().getId() + ") has an unacknowledged transfer id = " + t.getTransactionId() + " from " + t.getSender().getUsername() + "(id = " + t.getSender().getId() + ") for " + t.getAmount());
                                }
                            }
                        } else {
                            System.out.println("This option is not available in production mode.");
                        }
                        break;
                    case 7:
                        System.out.println("Finishing execution.");
                        return;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Invalid input, please enter valid data.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void displayMenu() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (isDev) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
        }
        System.out.println("7. Finish execution");
        System.out.print("-> ");
    }
}

