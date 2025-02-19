package Backend;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    private List<Book> books;

    public Bookstore() {
        this.books = new ArrayList<>();
        loadDefaultBooks(); 
    }


    private void loadDefaultBooks() {
       
    }

   
    public void addBook(String title, String author, double price) {
        books.add(new Book(title, author, price));
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
