import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("Bob", 1000));
        users.add(new User("Kob",500));
        users.add(new User("Mob",700));



        for (User user : users) {
            System.out.println("ID: " + user.getId() + ", name: " + user.getUsername() + ", balance: " + user.getBalance());
        }
    }
}

