import jdk.jshell.spi.ExecutionControl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.desktop.UserSessionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class BookListDemo {

    private JLabel status;
    public ArrayList<Book> books = new ArrayList<>();
    DefaultListModel<Book> model;
    public BookListDemo(ArrayList<Book> books) {
        this.books = books;
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Image List Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a button and add an action listener
        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            frame.setVisible(false);
            BookStoreHomePage.getBookStoreHomePage().setVisible(true);
        });

        // Create a panel and add the button to the top
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(exit, BorderLayout.NORTH);

        // Create a list model and add some image URLs
        model = new DefaultListModel<>();


        // Create a list and set the cell renderer
        JList<Book> list = new JList<Book>(model);
        list.setCellRenderer(new ImageCellRenderer());

        // Add the list to the center of the panel
        panel.add(list, BorderLayout.CENTER);

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // if the mouse is clicked on a book
                if (e.getClickCount() == 1 && list.getSelectedIndex() != -1) {
                    // print the index of the book in the array of books
                    if (User.currentUser instanceof Customer) {
                        if (Book.isAvailable(books.get(list.getSelectedIndex()))) {
                            // TODO
                            books.get(list.getSelectedIndex()).getOwner().deleteBook(books.get(list.getSelectedIndex()));
                            books.get(list.getSelectedIndex()).setOwner(User.currentUser);
                            System.out.println(books.get(list.getSelectedIndex()));
                            User.currentUser.changeInventory(-books.get(list.getSelectedIndex()).getPrice());
                            User.currentUser.books.add(books.get(list.getSelectedIndex()));

                            Book.deleteBook(books.get(list.getSelectedIndex()));

                        } else {
                            frame.setVisible(false);
                            BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                        }

                    } else {
                        frame.setVisible(false);
                        BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                    }
                }
            }
        });
        // Add the panel to the frame
        frame.add(panel);
        frame.pack();
        frame.setSize(600, 500);
        frame.setVisible(true);
        loadBooks();
    }

    // A custom cell renderer that displays an image icon
    class ImageCellRenderer implements ListCellRenderer<Book> {

        private JLabel label;

        public ImageCellRenderer() {
            label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index, boolean isSelected, boolean cellHasFocus) {
            try {
                // Read the image from the URL and scale it
                Image image = ImageIO.read(new File(book.getImageName()));
                image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);

                // Set the icon and the text of the label
                label.setIcon(new ImageIcon(image));
                label.setText(book.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return label;
        }


    }
    public void loadBooks() {
        for (Book book : books) {
            model.addElement(book); // add the book to the model
        }
    }
}
