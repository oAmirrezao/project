import java.util.*;

// this class extends the User class
// means each object of this class is a User
public class Customer extends User{
    ArrayList<Category> favouriteCategories = new ArrayList<Category>();
    String discountCode;

    public Customer(String username, String password, String e_mail, String phoneNumber) {
        // super class constructor must be call first in the constructor
        // otherwise we will get a compilation error
        super(username, password, e_mail, phoneNumber);
        this.role = "customer";
        currentUser = this;
    }

    // TODO  add some features to approve that this customer has enough money in his/her bank account
    private void buyBook(String bookTitle) {
        for (Book book : Book.books) {
            // TODO instance of must be checked if works correct
            if (book.getOwner() instanceof Seller firstOwner && book.getTitle().equalsIgnoreCase(bookTitle)) {
                // when a customer buys a book this must be execute
                this.changeInventory(-book.getPrice());
                book.setOwner(this);
                firstOwner.books.remove(book);
                firstOwner.changeInventory(book.getPrice());
            }
        }
    }


    // this function add categories which this customer likes them
    private void addFavouriteCategory(Category category) {
        this.favouriteCategories.add(category);
    }
}
