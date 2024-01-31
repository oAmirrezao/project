import java.util.*;
public abstract class User {
    // features of a user
    public static User currentUser;
    static private final ArrayList<User> usersList = new ArrayList<>();
    protected ArrayList<Book> books;
    private long inventory;
    public String username;
    private final String password;
    private final String email;
    private final String phoneNumber;
    public String role;
    // constructor of the class
    // conditions to validate a user is implemented in signup class
    public User(String username, String password, String email, String phoneNumber) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.phoneNumber = phoneNumber;
            usersList.add(this);
    }


    // checks if we had such a user before or not
    public static boolean isUniqueUsername(String username) {
        for(User user: usersList) {
            if (user.username.equals(username))
                return false;
        }
        return true;
    }

    // checks if a password is strong enough or not
    boolean isStrongPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    // checks if the email address is valid
    boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }
    // function to change the inventory of a user
    public void changeInventory(long increaseValue) {
        inventory += increaseValue;
    }
    // when we want to delete a book this function will be execute
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
