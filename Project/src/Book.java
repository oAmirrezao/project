import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
public class Book {
    private User owner;
    private String title;
    private String author;
    private String imageName;
    private BufferedImage image;
    private String publisher;
    private Category category;
    private int rate;
    private long price;
    public static ArrayList<Book> books = new ArrayList<>();
    public static ArrayList<Book> booksForBuy = new ArrayList<>();
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
    @Override
    public String toString() {
        return "Title : " + this.title + ", Author : " + this.author + ", Publisher : " + this.publisher + ", Price : " + this.price + ", Owner : " + owner.username;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getImageName() {
        return imageName;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public BufferedImage getImage() {
        return image;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
        try {
            image = ImageIO.read(new File(imageName));
            System.out.println("Image read successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    public static boolean validateImageAddress(String imageName) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imageName));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public int getRate() {
        return rate;
    }
    public long getPrice() {
        return price;
    }
    public User getOwner() {
        return this.owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void showBook() {
        System.out.println("Image: " + image);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publisher: " + publisher);
    }

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
