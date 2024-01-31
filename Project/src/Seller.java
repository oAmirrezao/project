// this class inherits from User class
// so every object of this class
// is an instance of User
public class Seller extends User{
    public Seller(String username, String password, String email, String phoneNumber) {
        // super class constructor must be first part in the constructor
        // because thisi class inherits from User class
        super(username, password, email, phoneNumber);
        this.role = "seller";
        currentUser = this;
    }

    // function which when we add a book to user books will execute
    public void addBook(String owner, String title, String author, String imageName, String publisher, long price) {
        this.books.add(new Book(this, title, author, imageName, publisher, price));
    }


}
