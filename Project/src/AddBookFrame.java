import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;

public class AddBookFrame extends JFrame implements ActionListener{
    private static final AddBookFrame addBookFrame = new AddBookFrame();
    private final Container c;
    private final JLabel title;
    private final JLabel bookTitle;
    private final JTextField tBookTitle;
    private final JLabel author;
    private final JTextField tAuthor;
    private final JLabel publisher;
    private final JTextField tPublisher;
    private final JLabel imageName;
    private final JTextField tImageName;
    private  final JLabel price;
    private final JTextField tPrice;
    private final JLabel status;
    private final JButton add;
    private final JButton exit;
    private AddBookFrame() {
        setTitle("Add Book");
        setBounds(300, 90, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Add Book");
        title.setFont(new Font("Arial", Font.PLAIN, 15));
        title.setSize(100, 20);
        title.setLocation(100, 30);
        c.add(title);

        bookTitle = new JLabel("BookTitle");
        bookTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        bookTitle.setSize(100, 20);
        bookTitle.setLocation(50, 100);
        c.add(bookTitle);

        tBookTitle = new JTextField();
        tBookTitle.setFont(new Font("Arial", Font.PLAIN, 15));
        tBookTitle.setSize(200, 20);
        tBookTitle.setLocation(150, 100);
        c.add(tBookTitle);

        author = new JLabel("Author");
        author.setFont(new Font("Arial", Font.PLAIN, 15));
        author.setSize(100, 20);
        author.setLocation(50, 80);
        c.add(author);

        tAuthor = new JTextField();
        tAuthor.setFont(new Font("Arial", Font.PLAIN, 15));
        tAuthor.setSize(100, 20);
        tAuthor.setLocation(150, 80);
        c.add(tAuthor);

        publisher = new JLabel("Publisher");
        publisher.setFont(new Font("Arial", Font.PLAIN, 15));
        publisher.setSize(100, 20);
        publisher.setLocation(50, 120);
        c.add(publisher);

        tPublisher = new JTextField();
        tPublisher.setFont(new Font("Arial", Font.PLAIN, 15));
        tPublisher.setSize(100, 20);
        tPublisher.setLocation(150, 120);
        c.add(tPublisher);

        imageName= new JLabel("imageFolder");
        imageName.setFont(new Font("Arial", Font.PLAIN, 15));
        imageName.setSize(100, 20);
        imageName.setLocation(50, 160);
        c.add(imageName);

        tImageName = new JTextField();
        tImageName.setFont(new Font("Arial", Font.PLAIN, 15));
        tImageName.setSize(100, 20);
        tImageName.setLocation(150, 160);
        c.add(tImageName);

        price = new JLabel("price");
        price.setFont(new Font("Arial", Font.PLAIN, 15));
        price.setSize(100, 20);
        price.setLocation(50, 200);
        c.add(price);

        tPrice = new JTextField();
        tPrice.setFont(new Font("Arial", Font.PLAIN, 15));
        tPrice.setSize(100, 20);
        tPrice.setLocation(150, 200);
        tPrice.setDocument(new NumberDocument());
        c.add(tPrice);


        add = new JButton("Add");
        add.setFont(new Font("Arial", Font.PLAIN, 15));
        add.setSize(100, 20);
        add.setLocation(50, 240);
        add.addActionListener(this);
        c.add(add);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 15));
        exit.setSize(100, 20);
        exit.setLocation(250, 240);
        exit.addActionListener(this);
        c.add(exit);

        status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        status.setSize(300, 20);
        status.setLocation(50, 280);
        c.add(status);
        setVisible(true);
    }

    public static AddBookFrame getAddBookFrame() {
        return addBookFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String bookTitle = tBookTitle.getText();
            String author = tAuthor.getText();
            String publisher = tPublisher.getText();
            String imageName = tImageName.getText();
            if (bookTitle.isEmpty() || author.isEmpty() || publisher.isEmpty() || imageName.isEmpty() || tPrice.getText().isEmpty()) {
                status.setText("Please fill all the fields");
            } else {
                // TODO add your logic to this part
                long price = Long.parseLong(tPrice.getText());
                if (Book.validateImageAddress(imageName)) {
                    Book book = new Book(User.currentUser, bookTitle, author, imageName, publisher, price);
                    status.setText("Your book successfully added");
                    tBookTitle.setText("");
                    tAuthor.setText("");
                    tPublisher.setText("");
                    tImageName.setText("");
                    tPrice.setText("");
                } else {
                    status.setText("Please enter a valid image address");
                }
            }
        } else if(e.getSource() == exit) {
            tBookTitle.setText("");
            tAuthor.setText("");
            tPublisher.setText("");
            tImageName.setText("");
            tPrice.setText("");
            setVisible(false);
            BookStoreHomePage.getBookStoreHomePage().setVisible(true);
        }
    }
}
// A custom document class that accepts only numbers
class NumberDocument extends PlainDocument {

    // A method to insert a string into the document
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        // Check if the string is null or empty
        if (str == null || str.isEmpty()) {
            return;
        }

        // Loop through the characters in the string
        for (char c : str.toCharArray()) {
            // Check if the character is a digit
            if (Character.isDigit(c)) {
                // Insert the character into the document
                super.insertString(offset, String.valueOf(c), attr);
                // Increment the offset
                offset++;
            }
        }
    }
}

