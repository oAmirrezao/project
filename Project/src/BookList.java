import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BookList extends JFrame {
    ArrayList<Book> books; // a list of books
    JList<Book> jList; // a JList to display the books
    DefaultListModel<Book> model; // a model for the JList
    JScrollPane scrollPane; // a scroll pane for the JList
    BookList(ArrayList<Book> books) {
        this.books = books;
        model = new DefaultListModel<Book>();
        jList = new JList<Book>(model);
        scrollPane = new JScrollPane(jList);

        // set the cell renderer for the JList
        jList.setCellRenderer(new ListCellRenderer<Book>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                // create a panel for each book
                JPanel panel = new JPanel(new BorderLayout());
                try {
                    // read the image from the file
                    BufferedImage image = ImageIO.read(new File(book.getImageName()));
                    // create a label for the image
                    JLabel imageLabel = new JLabel(new ImageIcon(image));
                    // add the label to the panel
                    panel.add(imageLabel, BorderLayout.CENTER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // create a label for the title

                JLabel titleLabel = new JLabel("title : " + book.getTitle());
                titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));
                titleLabel.setSize(100, 20);

                JLabel authorLabel = new JLabel("Author : " + book.getAuthor());
                authorLabel.setFont(new Font("Arial", Font.PLAIN, 40));
                authorLabel.setSize(100, 20);

                JLabel priceLabel = new JLabel("price : " + book.getPrice());
                priceLabel.setFont(new Font("Arial", Font.PLAIN, 40));
                priceLabel.setSize(100, 20);
                // add the label to the panel
                panel.add(titleLabel, BorderLayout.WEST);
                panel.add(authorLabel, BorderLayout.EAST);
                // set the background color of the panel
                panel.setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
                // return the panel as the component
                return panel;
            }
        });

        // add a mouse listener for the JList
        jList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if the mouse is clicked on a book
                if (e.getClickCount() == 1 && jList.getSelectedIndex() != -1) {
                    // print the index of the book in the array of books
                    if (User.currentUser instanceof Customer) {
                        if (Book.isAvailable(books.get(jList.getSelectedIndex()))) {
                            // TODO
                        } else {
                            setVisible(false);
                            BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                        }

                    } else {
                        setVisible(false);
                        BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                    }
                }
            }
        });

        // add the scroll pane to the frame
        this.add(scrollPane);
        // set the title of the frame
        this.setTitle("Book List");
        // set the size of the frame
        this.setSize(400, 300);
        // set the default close operation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the visibility of the frame
        this.setVisible(true);
        // load the books from the array of books
        loadBooks();
    }

    // a method to load the books from the array of books
    public void loadBooks() {
        for (Book book : books) {
            model.addElement(book); // add the book to the model
        }
    }
}
