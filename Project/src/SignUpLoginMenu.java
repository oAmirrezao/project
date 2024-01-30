import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class SignUpLoginMenu extends JFrame implements ActionListener {
    private static final SignUpLoginMenu SIGN_UP_LOGIN_MENU = new SignUpLoginMenu();

    private final Container c;
    private final JComboBox<String> cb;
    private final JLabel title;
    private final JLabel username;
    private final JTextField tUsername;
    String[] choices = {"فروشنده", "خریدار"};
    private final JLabel email;
    private final JTextField temail;
    private final JLabel password;
    private final JPasswordField tpass;
    private final JLabel confirm;
    private final JPasswordField tconfirm;
    private final JLabel phone;
    private final JFormattedTextField tphone;
    private final JLabel role;
    private final JButton signup;
    private final JButton reset;
    private final JLabel status;
    MaskFormatter phoneMask;

    private SignUpLoginMenu() {
        setTitle("Signup Menu");
        setBounds(300, 90, 800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        title = new JLabel("Signup Menu");
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
        tUsername.setSize(200, 20);
        tUsername.setLocation(150, 100);
        c.add(tUsername);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 15));
        email.setSize(100, 20);
        email.setLocation(50, 80);
        c.add(email);

        temail = new JTextField();
        temail.setFont(new Font("Arial", Font.PLAIN, 15));
        temail.setSize(200, 20);
        temail.setLocation(150, 80);
        c.add(temail);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 15));
        password.setSize(100, 20);
        password.setLocation(50, 120);
        c.add(password);

        tpass = new JPasswordField();
        tpass.setFont(new Font("Arial", Font.PLAIN, 15));
        tpass.setSize(200, 20);
        tpass.setLocation(150, 120);
        c.add(tpass);

        confirm = new JLabel("Confirm Password");
        confirm.setFont(new Font("Arial", Font.PLAIN, 15));
        confirm.setSize(150, 20);
        confirm.setLocation(50, 160);
        c.add(confirm);

        tconfirm = new JPasswordField();
        tconfirm.setFont(new Font("Arial", Font.PLAIN, 15));
        tconfirm.setSize(200, 20);
        tconfirm.setLocation(150, 160);
        c.add(tconfirm);

        role = new JLabel("select your role");
        role.setFont(new Font("Arial", Font.PLAIN, 15));
        role.setSize(150, 20);
        role.setLocation(50, 65);
        c.add(role);

        cb = new JComboBox<String>(choices);
        cb.setFont(new Font("Arial", Font.PLAIN, 15));
        cb.setSize(100, 20);
        cb.setLocation(150, 65);
        c.add(cb);


        phone = new JLabel("Phone");
        phone.setFont(new Font("Arial", Font.PLAIN, 15));
        phone.setSize(100, 20);
        phone.setLocation(50, 180);
        c.add(phone);

        // Create a mask formatter for phone numbers
        phoneMask = null;
        try {
            phoneMask = new MaskFormatter("(###) ###-####");
            phoneMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Create a formatted text field with the mask formatter
        tphone = new JFormattedTextField(phoneMask);
        tphone.setFont(new Font("Arial", Font.PLAIN, 15));
        tphone.setSize(150, 20);
        tphone.setLocation(150, 180);
        c.add(tphone);


        signup = new JButton("Signup");
        signup.setFont(new Font("Arial", Font.PLAIN, 15));
        signup.setSize(100, 20);
        signup.setLocation(50, 200);
        signup.addActionListener(this);
        c.add(signup);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(250, 200);
        reset.addActionListener(this);
        c.add(reset);

        status = new JLabel("");
        status.setFont(new Font("Arial", Font.PLAIN, 15));
        status.setSize(300, 20);
        status.setLocation(50, 240);
        c.add(status);

        try {
            Image image = ImageIO.read(new File("C:\\Users\\ASUS\\OneDrive\\Documents\\Semester3\\Project\\src\\book3.jpg"));
            setIconImage(image);
        } catch (IOException e) {
            System.out.println("Erorr " + e);
        }
        setResizable(true);
        setVisible(true);
    }

    public static SignUpLoginMenu getSignUpLoginMenu() {
        return SIGN_UP_LOGIN_MENU;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signup) {
            String role = choices[cb.getSelectedIndex()];
            String email = temail.getText();
            String username = tUsername.getText();
            String password = new String(tpass.getPassword());
            String phoneNumber = tphone.getText();
            String confirm = new String(tconfirm.getPassword());
            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty() || phoneNumber.contains("_")) {
                status.setText("Please fill all the fields");
            } else {
                if (!password.equals(confirm)) {
                    status.setText("Passwords do not match");
                } else if (!validateEmail(email)) {
                    status.setText("invalid email address");
                } else if (!isStrongPassword(password)) {
                    System.out.println(User.userList);
                    status.setText("weak password");
                } else if (!User.isUniqueUsername(username)) {
                    System.out.println(1);
                    status.setText("This username already exists");
                } else {
                    System.out.println(role);
                    status.setText("signup successful");
                    if (role.equalsIgnoreCase("فروشنده")) {
                        new Seller(username, password, email, phoneNumber);
                    } else {
                        new Customer(username, password, email, phoneNumber);
                    }
                    temail.setText("");
                    tUsername.setText("");
                    tpass.setText("");
                    tconfirm.setText("");
                    tphone.setText("");
                    status.setText("");
                    setVisible(false);
                    BookStoreHomePage.getBookStoreHomePage().setVisible(true);
                }
            }
        } else if (e.getSource() == reset) {
            temail.setText("");
            tUsername.setText("");
            tpass.setText("");
            tconfirm.setText("");
            tphone.setText("");
            status.setText("");
        }
    }

    boolean isStrongPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    boolean validateEmail(String email) {
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }


}

//    public boolean addUser(String username, String password, String email, long phoneNumber, String role) {
//        User user = new User(username, password, email, phoneNumber, role);
//        System.out.println(user.role);
//        if (user.username != null) {
//            System.out.println("you successfully logged in or signed up to our website!!!!");
//            return true;
//        }
//        System.out.println("emmm!!! some thing went wrong");
//        return false;
//    }
