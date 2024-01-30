import java.util.*;
public class Customer extends User{
    ArrayList<Category> favouriteCategories = new ArrayList<Category>();
    String discountCode;

    public Customer(String username, String password, String e_mail, String phoneNumber) {
        super(username, password, e_mail, phoneNumber);
        this.role = "customer";
        currentUser = this;
    }

    // TODO  add some features to approve that this customer has enough money in his/her bank account
    private void buyBook(String bookTitle) {
        for (Book book : Book.books) {
            // TODO instance of must be checked if works correct
            if (book.getOwner() instanceof Seller firstOwner && book.getTitle().equalsIgnoreCase(bookTitle)) {
                // TODO an important part of program must be completed
                this.changeInventory(-book.getPrice());
                book.setOwner(this);
                firstOwner.books.remove(book);
                firstOwner.changeInventory(book.getPrice());
            }
        }
    }


    private void addFavouriteCategory(Category category) {
        this.favouriteCategories.add(category);
    }
}
