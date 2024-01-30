public class Seller extends User{
    public Seller(String username, String password, String email, String phoneNumber) {
        super(username, password, email, phoneNumber);
        this.role = "seller";
        currentUser = this;
    }

    public void addBook(String owner, String title, String author, String imageName, String publisher, long price) {
        this.books.add(new Book(this, title, author, imageName, publisher, price));
    }


}
