package Frontend;

import Backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BookListUI {
    static JFrame Bookframe;
    private JPanel panel;
    private List<Book> cart;

    public BookListUI(List<Book> books) {
        this.cart = new ArrayList<>();

        Bookframe = new JFrame("Book List");
        Bookframe.setSize(800, 800);
        Bookframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridLayout(books.size() + 1, 4));

        for (Book book : books) {
            JLabel titleLabel = new JLabel(book.getTitle());
            JLabel authorLabel = new JLabel(book.getAuthor());
            JLabel priceLabel = new JLabel(String.valueOf(book.getPrice()));
            JButton addToCartButton = new JButton("Add to Cart");

            addToCartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addToCart(book);
                }
            });

            panel.add(titleLabel);
            panel.add(authorLabel);
            panel.add(priceLabel);
            panel.add(addToCartButton);
        }

        JButton viewCartButton = new JButton("View Cart");
        viewCartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCart();
            }
        });
        panel.add(viewCartButton);

        Bookframe.add(panel);
        Bookframe.setVisible(true);
    }

    private void addToCart(Book book) {
        cart.add(book);
        JOptionPane.showMessageDialog(Bookframe, "Book added to cart: " + book.getTitle(), "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewCart() {
        StringBuilder cartContent = new StringBuilder("Items in Cart:\n");
        for (Book book : cart) {
            cartContent.append(book.getTitle()).append(" by ").append(book.getAuthor()).append(" - $").append(book.getPrice()).append("\n");
        }
        JOptionPane.showMessageDialog(Bookframe, cartContent.toString(), "View Cart", JOptionPane.PLAIN_MESSAGE);
    }

    public JPanel getPanel() {
        return panel;
    }

    public static void main(String[] args) {
        // Assuming books list is populated from somewhere
        List<Book> books = new ArrayList<>();
        books.add(new Book("Book 1", "Author 1", 10.99));
        books.add(new Book("Book 2", "Author 2", 12.99));
        books.add(new Book("Book 3", "Author 3", 9.99));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BookListUI bookListUI = new BookListUI(books);
                // Example of adding the panel to a JFrame
                JFrame testFrame = new JFrame("Test Frame");
                testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                testFrame.setSize(600, 400);
                testFrame.add(bookListUI.getPanel());
                testFrame.setVisible(true);
            }
        });
    }
}
