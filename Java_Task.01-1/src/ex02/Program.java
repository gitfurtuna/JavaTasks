public class Program {
    public static void main(String[] args) {
        UsersList usersList = new UsersArrayList();

        usersList.addUser(new User("Jonh", 1000));
        usersList.addUser(new User("Mike",500));


        System.out.println("Total users: " + usersList.getUserCount());
        System.out.println();
        System.out.println("Get user by Index:");
        for (int i = 0; i < usersList.getUserCount(); i++) {
            System.out.println(usersList.getUserByIndex(i));
        }
        System.out.println();
        System.out.println("Get user by Id:");
        try {
            System.out.println(usersList.getUserById(1));
            System.out.println(usersList.getUserById(3));
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
