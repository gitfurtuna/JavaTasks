public class TransactionsLinkedList implements TransactionsList {
    private class Node {
        Transaction transaction;
        Node next;

        Node(Transaction transaction) {
            this.transaction = transaction;
            this.next = null;
        }
    }

    private Node head;

    public TransactionsLinkedList() {
        this.head = null;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public void removeTransaction(String id) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.transaction.getTransactionId().equals(id)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                return;
            }
            previous = current;
            current = current.next;
        }

        throw new TransactionNotFoundException("Transaction with ID " + id + " not found.");
    }

    @Override
    public Transaction[] toArray() {
        int size = 0;
        Node current = head;


        while (current != null) {
            size++;
            current = current.next;
        }

        Transaction[] transactions = new Transaction[size];
        current = head;
        int index = 0;


        while (current != null) {
            transactions[index++] = current.transaction;
            current = current.next;
        }

        return transactions;
    }
}
