public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(String id) throws TransactionNotFoundException;
    Transaction[] toArray();
}
