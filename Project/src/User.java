import java.util.*;
public abstract class User {
    public static User currentUser;
    static public ArrayList<User> userList;
    static private final ArrayList<User> usersList = new ArrayList<>();
    protected ArrayList<Book> books;
    private long inventory;
    public String username;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private Cart[] history;
    public String role;

    public User(String username, String password, String email, String phoneNumber) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
            usersList.add(this);
    }

    public static boolean isUniqueUsername(String username) {
        for(User user: usersList) {
            if (user.username.equals(username))
                return false;
        }
        return true;
    }
    boolean isStrongPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }

    public void changeInventory(long increaseValue) {
        inventory += increaseValue;
    }
    public void deleteBook(Book book) {
        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getTitle().equalsIgnoreCase(book.getTitle())) {
                    books.remove(i);
                    break;
                }
            }
        }
    }

    public static User isUserExist(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).username.equals(username) && usersList.get(i).password.equals(password))
                return userList.get(i);
        }
        return null;
    }

}
