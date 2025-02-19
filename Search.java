package Backend;

import java.util.ArrayList;
import java.util.List;

public class Search {
    public static List<Book> searchBooks(Bookstore bookstore, String query) {
        List<Book> searchResults = new ArrayList<>();
        List<Book> allBooks = bookstore.getAllBooks();

        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(book);
            }
        }

        return searchResults;
    }
}
