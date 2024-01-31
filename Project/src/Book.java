import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;

// if we use new File then a photo from current device will be read
// if we use new URL then a photo from web will be load
public class Book {
    // define class features
    private User owner;
    private String title;
    private String author;
    // it is image address
    private String imageName;
    private BufferedImage image;
    private String publisher;
    private Category category;
    private int rate;
    private long price;
    // list of all books
    public static ArrayList<Book> books = new ArrayList<>();
    // list of books which can be sold
    public static ArrayList<Book> booksForBuy = new ArrayList<>();
    // constructor
    public Book(User owner, String title, String author, String imageName, String publisher, long price) {
        this.owner = owner;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.imageName = imageName;
        try {
            // Assign the result of ImageIO.read to the image field
            image = ImageIO.read(new File(imageName));

            // Print a success message
            System.out.println("Image read successfully.");
        } catch (IOException e) {
            // Print an error message
            System.out.println("Error: " + e);
        }
        if (owner.role.equalsIgnoreCase("seller"))
            books.add(this);
    }
    // to string method to define how the class will be show if we print it
    @Override
    public String toString() {
        return "Title : " + this.title + ", Author : " + this.author + ", Publisher : " + this.publisher + ", Price : " + this.price + ", Owner : " + owner.username;
    }
    // getter to get title name
    public String getTitle() {
        return title;
    }
    // setter to set title name
    public void setTitle(String title) {
        this.title = title;
    }
    // getter to get author name
    public String getAuthor() {
        return author;
    }
    // setter to set author name
    public void setAuthor(String author) {
        this.author = author;
    }
    // getter to get image address
    public String getImageName() {
        return imageName;
    }
    // getter to get publisher name
    public String getPublisher() {
        return publisher;
    }
    // setter to set publisher name
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    // getter function to get image
    public BufferedImage getImage() {
        return image;
    }
    // setter function to set image address
    public void setImageName(String imageName) {
        this.imageName = imageName;
        try {
            image = ImageIO.read(new File(imageName));
            System.out.println("Image read successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    // function to check if an image address is there in current device
    public static boolean validateImageAddress(String imageName) {
        BufferedImage image;
        // if image address was invalid it will go to catch and returns false
        try {
            image = ImageIO.read(new File(imageName));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    // getter to get average rate of a book
    public int getRate() {
        return rate;
    }
    // getter to get price of a book
    public long getPrice() {
        return price;
    }
    // getter to get owner of this book
    public User getOwner() {
        return this.owner;
    }
    // setter to set owner of this book
    public void setOwner(User owner) {
        this.owner = owner;
    }

    // it  will be used when we want to show a book features
    public void showBook() {
        System.out.println("Image: " + image);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
    }

    // this will delete a book from book list if the book we want exists
    public static void deleteBook(Book book) {
        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).title.equalsIgnoreCase(book.title)) {
                    books.remove(i);
                    break;
                }
            }
        }
    }
    // this will check if a wanted book is availabe in list of books or not
    public static boolean isAvailable(Book book) {
        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).title.equalsIgnoreCase(book.title)) {
                    return true;
                }
            }
        }
        return false;
    }

}
