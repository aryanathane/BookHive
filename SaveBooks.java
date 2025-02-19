package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveBooks {
	private static final String FILENAME = "Book.csv";
	
  
	public static void saveBooksToCSV(String title, String author, double price) {
		// TODO Auto-generated method stub
		 try (FileWriter writer = new FileWriter(FILENAME, true)) {
	            writer.write(String.format("%s,%s,%.2f\n", title, author, price));
	            System.out.println("Book saved to CSV file: " + FILENAME);
	        } catch (IOException e) {
	            System.err.println("Error writing to CSV file: " + e.getMessage());
	        }
	}
	public static List<Book> readBooksFromCSV(String fileName) {
        List<Book> books = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String title = data[0];
                    String author = data[1];
                    double price = Double.parseDouble(data[2]);
                    Book book = new Book(title, author, price);
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }
	
}

