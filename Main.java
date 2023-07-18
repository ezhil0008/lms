import java.util.ArrayList;
import java.util.List;

// Book class representing a book entity
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// User class representing a library user
class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

// Library class managing books and users
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void borrowBook(User user, Book book) {
        if (book.isAvailable()) {
            user.borrowBook(book);
            System.out.println(user.getName() + " has borrowed " + book.getTitle());
        } else {
            System.out.println("Sorry, " + book.getTitle() + " is not available");
        }
    }

    public void returnBook(User user, Book book) {
        if (user.getBorrowedBooks().contains(book)) {
            user.returnBook(book);
            System.out.println(user.getName() + " has returned " + book.getTitle());
        } else {
            System.out.println("Sorry, " + user.getName() + " has not borrowed " + book.getTitle());
        }
    }
}

// Example usage of the library management system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Create books
        Book book1 = new Book("Book 1", "Author 1");
        Book book2 = new Book("Book 2", "Author 2");

        // Create users
        User user1 = new User("User 1");
        User user2 = new User("User 2");

        // Add books and users to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addUser(user1);
        library.addUser(user2);

        // User 1 borrows a book
        library.borrowBook(user1, book1);

        // User 2 tries to borrow a book that is already borrowed
        library.borrowBook(user2, book1);

        // User 1 returns the book
        library.returnBook(user1, book1);

        // User 2 borrows the book after it is returned
        library.borrowBook(user2, book1);
    }
}