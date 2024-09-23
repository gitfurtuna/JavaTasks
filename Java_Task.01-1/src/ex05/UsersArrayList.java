public class UsersArrayList implements UsersList {
    private User[] users;
    private int size;

    public UsersArrayList() {
        users = new User[10];
        size = 0;
    }

    @Override
    public void addUser(User user) {
        if (size == users.length) {
            resizeArray();
        }
        users[size++] = user;
    }

    private void resizeArray() {
        User[] newArray = new User[users.length * 2];
        System.arraycopy(users, 0, newArray, 0, users.length);
        users = newArray;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < size; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with id " + id + " not found.");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return users[index];
    }

    @Override
    public int getUserCount() {
        return size;
    }
}
