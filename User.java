package Backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private char[] password;
    private static final String USERS_FILE = "users.txt";
    private static List<User> users;

    public User(String username, char[] password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    // Load users from file
    public static List<User> loadUsersFromFile() {
        if (users != null) {
            return users; // Return cached users if already loaded
        }
        users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    users.add(new User(parts[0], parts[1].toCharArray()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
        return users;
    }
    
    public static void saveUsersToFile(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + new String(user.getPassword()) + "\n"); // Convert char[] to String
            }
        } catch (IOException e) {
            System.err.println("Error saving users to file: " + e.getMessage());
        }
    }
    
    public static void registerUser(String username, char[] password) {
        List<User> users = loadUsersFromFile();
        users.add(new User(username, password));
        saveUsersToFile(users);
    }
    
    public static boolean authenticateUser(String username, char[] password) {
        List<User> users = loadUsersFromFile();
        for (User user : users) {
            if (user.getUsername().equals(username) && new String(user.getPassword()).equals(new String(password))) {
                return true;
            }
        }
        return false;
    }
}
