package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDisplay {

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    books.add(new Book(title, author, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
