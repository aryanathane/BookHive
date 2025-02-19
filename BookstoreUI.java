package Frontend;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BookstoreUI {
    private JFrame frame;
    private JPanel loginPanel;
    private JPanel addBooksPanel;
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton addBooksButton;
    private JButton viewBooksButton;
    private Color primaryColor = new Color(63, 81, 170); 
    private Color secondaryColor = new Color(158, 158, 158); 
    private Color textColor = Color.WHITE;
    
    public BookstoreUI() {
        frame = new JFrame("Bookstore Management System");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.setBackground(primaryColor);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(textColor);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        userField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(textColor);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(secondaryColor);
//        loginButton.setForeground(textColor);
        registerButton = new JButton("Register");
        registerButton.setBackground(secondaryColor);
//        registerButton.setForeground(textColor);
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                char[] password = passwordField.getPassword();
                if (User.authenticateUser(username, password)) {
                    enableOptionsUI();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                char[] password = passwordField.getPassword();
                User.registerUser(username, password);
                JOptionPane.showMessageDialog(frame, "User registered successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        loginPanel.add(userLabel);
        loginPanel.add(userField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(registerButton);
        loginPanel.add(loginButton);

        frame.add(loginPanel);
        frame.setVisible(true);

        new Bookstore();
    }

    private void enableOptionsUI() {
        frame.getContentPane().removeAll();

        addBooksButton = new JButton("Add Books");
        addBooksButton.setFont(new Font("Arial", Font.BOLD, 16));
        addBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableAddBooksUI();
            }
        });

        viewBooksButton = new JButton("View Books");
        viewBooksButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewBooksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableBookDisplayUI();
            }
        });

        JPanel optionsPanel = new JPanel(new GridLayout(2, 1));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        optionsPanel.add(addBooksButton);
        optionsPanel.add(viewBooksButton);
        addBackAndHomeButtons(optionsPanel);

        frame.add(optionsPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void enableAddBooksUI() {
        frame.getContentPane().removeAll();

        addBooksPanel = new JPanel(new GridLayout(6, 2));
        addBooksPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField titleField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField authorField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField priceField = new JTextField();

        JButton addBookButton = new JButton("Add Book");
        addBookButton.setFont(new Font("Arial", Font.BOLD, 16));
        addBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add book logic
            }
        });

        addBooksPanel.add(titleLabel);
        addBooksPanel.add(titleField);
        addBooksPanel.add(authorLabel);
        addBooksPanel.add(authorField);
        addBooksPanel.add(priceLabel);
        addBooksPanel.add(priceField);
        addBooksPanel.add(addBookButton);
        addBackAndHomeButtons(addBooksPanel);

        frame.add(addBooksPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void enableBookDisplayUI() {
        frame.getContentPane().removeAll();

        // Read books from the CSV file
        List<Book> books = SaveBooks.readBooksFromCSV("Book.csv");
        if (books.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No books found", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            BookListUI bookListUI = new BookListUI(books);
            JPanel bookListPanel = bookListUI.getPanel();
            addBackAndHomeButtons(bookListPanel);

            BookListUI.Bookframe.add(bookListPanel);
        }

        frame.revalidate();
        frame.repaint();
    }

    private void addBackAndHomeButtons(JPanel panel) {
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enableOptionsUI();
            }
        });

        JButton homeButton = new JButton("Home");
        homeButton.setFont(new Font("Arial", Font.BOLD, 16));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(loginPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        panel.add(backButton);
        panel.add(homeButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new BookstoreUI();
            }
        });
    }
}
