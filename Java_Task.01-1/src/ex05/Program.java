public class Program {
    public static void main(String[] args) {
        boolean isDev = false;
        for (String arg : args) {
            if ("--profile=dev".equals(arg)) {
                isDev = true;
                break;
            }
        }
        UsersList users = new UsersArrayList();
        TransactionsService transactionsService = new TransactionsService(users);
        Menu menu = new Menu(transactionsService, isDev);
        menu.show();
    }
}
