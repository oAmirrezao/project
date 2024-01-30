import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame implements ActionListener {
    private static final LoginMenu loginMenu = new LoginMenu();
    private final Container c;
    private final JLabel title;
    private final JLabel username;
    private final JTextField tUsername;
    private final JLabel password;
    private final JTextField tpass;
    private final JButton signupMenu;
    private final JButton login;
    private final JLabel status;

    private LoginMenu() {
        setTitle("Login Menu");
        setBounds(300, 90, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Login Menu");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setSize(200, 30);
        title.setLocation(100, 30);
        c.add(title);

        username = new JLabel("username");
        username.setFont(new Font("Arial", Font.PLAIN, 15));
        username.setSize(100, 20);
        username.setLocation(50, 100);
        c.add(username);

        tUsername = new JTextField();
        tUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        tUsername.setSize(100, 20);
        tUsername.setLocation(150, 100);
        c.add(tUsername);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setSize(100, 20);
        password.setLocation(50, 140);
        c.add(password);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(100, 20);
        tpass.setLocation(150, 140);
        c.add(tpass);

        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.PLAIN, 15));
        login.setSize(100, 20);
        login.setLocation(50, 180);
        login.addActionListener(this);
        c.add(login);

        signupMenu = new JButton("Login");
        signupMenu.setFont(new Font("Arial", Font.PLAIN, 15));
        signupMenu.setSize(100, 20);
        signupMenu.setLocation(250, 180);
        signupMenu.addActionListener(this);
        c.add(signupMenu);

        status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        status.setSize(300, 20);
        status.setLocation(50, 140);
        c.add(status);
        setResizable(true);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            String username = tUsername.getText();
            String password = tpass.getText();
            if (username.isEmpty() || password.isEmpty()) {
                status.setText("Please fill all the fields");
            } else {
                // TODO check if there is such username and password
                if (User.isUserExist(username, password) != null) {
                    status.setText("You successfully logged in");
                    tUsername.setText("");
                    tpass.setText("");
                    User user = User.isUserExist(username, password);
                    User.currentUser = user;
                    setVisible(false);
                    BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                }
            }
        } else if (e.getSource() == signupMenu) {
            setVisible(false);
            SignUpLoginMenu.getSignUpLoginMenu().setVisible(true);
        }
    }

    public static LoginMenu getLoginMenu() {
        return loginMenu;
    }
}
