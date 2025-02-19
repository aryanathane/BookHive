package Backend;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Book> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Book book) {
        items.add(book);
    }

    public void removeItem(Book book) {
        items.remove(book);
    }

    public List<Book> getItems() {
        return items;
    }
}
