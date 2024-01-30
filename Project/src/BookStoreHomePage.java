import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookStoreHomePage extends JFrame implements ActionListener {
    private static final BookStoreHomePage bookStoreHomePage = new BookStoreHomePage();
    private final Container c;
    private final JLabel title;
    private final JButton store;
    private final JButton addBook;
    private final JButton cart;
    private final JButton login;
    private JButton register;
    private final JButton exit;
    private final JLabel status;

    private BookStoreHomePage() {
        setTitle("Book Store Home Page");
        setBounds(300, 90, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Welcome to our Book Store!");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 50);
        title.setLocation(100, 50);
        c.add(title);

        store = new JButton("Store");
        store.setFont(new Font("Arial", Font.PLAIN, 20));
        store.setSize(200, 50);
        store.setLocation(200, 150);
        store.addActionListener(this);
        c.add(store);

        addBook = new JButton("Add Books");
        addBook.setFont(new Font("Arial", Font.PLAIN, 20));
        addBook.setSize(200, 50);
        addBook.setLocation(200, 220);
        addBook.addActionListener(this);
        c.add(addBook);

        cart = new JButton("View Cart");
        cart.setFont(new Font("Arial", Font.PLAIN, 20));
        cart.setSize(150, 30);
        cart.setLocation(50, 300);
        cart.addActionListener(this);
        c.add(cart);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 20));
        login.setSize(100, 30);
        login.setLocation(250, 300);
        login.addActionListener(this);
        c.add(login);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 20));
        exit.setSize(150, 30);
        exit.setLocation(400, 300);
        exit.addActionListener(this);
        c.add(exit);

        status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        status.setSize(300, 20);
        status.setLocation(50, 340);
        c.add(status);

        setVisible(true);
    }

    public static BookStoreHomePage getBookStoreHomePage() {
        return bookStoreHomePage;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO: add your logic to handle the button clicks
        // For example, you can create new frames for each button
        // and display the corresponding content
        if (e.getSource() == store) {
            setVisible(false);
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new BookListDemo(Book.books).createAndShowGUI();
                }
            });
//            new BookList(Book.books).setVisible(true);
            // Create a new frame for browsing books
            // You can use a JTable to display the book information
            // You can also add buttons for adding books to cart or viewing details
        } else if (e.getSource() == addBook) {
            if (User.currentUser.role.equalsIgnoreCase("customer")) {
                status.setText("Access denied!! you aren't a seller");
            } else {
                setVisible(false);
                AddBookFrame.getAddBookFrame().setVisible(true);
            }
            // Create a new frame for searching books
            // You can use a JTextField to get the user input
            // You can use a JComboBox to let the user choose the search criteria
            // You can use a JTable to display the search results
            // You can also add buttons for adding books to cart or viewing details
        } else if (e.getSource() == cart) {
            // Create a new frame for viewing the cart
            // You can use a JTable to display the cart items
            // You can also add buttons for removing items or checking out
        } else if (e.getSource() == login) {
            // Create a new frame for logging in
            // You can use a JTextField and a JPasswordField to get the user credentials
            // You can also add a button for logging in or a link for registering
        } else if (e.getSource() == exit) {
            setVisible(false);
            SignUpLoginMenu.getSignUpLoginMenu().setVisible(true);
            // Create a new frame for registering
            // You can use a JTextField and a JPasswordField to get the user information
            // You can also add a button for registering or a link for logging in
        }
    }

}
