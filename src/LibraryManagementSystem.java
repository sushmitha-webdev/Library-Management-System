import java.util.*;

// Book class (same as before)
class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println("SUCCESS: '" + title + "' has been issued.");
        } else {
            System.out.println("NOTE: '" + title + "' is already issued.");
        }
    }

    public void returnBook() {
        if (isIssued) {
            isIssued = false;
            System.out.println("SUCCESS: '" + title + "' has been returned.");
        } else {
            System.out.println("NOTE: '" + title + "' was not issued, cannot return.");
        }
    }

    @Override
    public String toString() {
        return "Book: '" + title + "' by " + author + (isIssued ? " [Issued]" : " [Available]");
    }
}

// User class (same as before)
class User {
    private String name;
    private int userId;
    private List<Book> borrowedBooks;

    public User(String name, int userId) {
        this.name = name;
        this.userId = userId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + " | Name: " + name;
    }
}

// Library class - CORRECTED
class Library {
    private List<Book> books;
    private Map<Integer, User> users; // To manage users by their ID

    public Library() {
        books = new ArrayList<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("LIBRARY: Added '" + book.getTitle() + "' to the library.");
    }

    public void addUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("LIBRARY: Registered user " + user.getName() + " (ID: " + user.getUserId() + ").");
    }

    public void showAllBooks() {
        System.out.println("\n--- Current Library Collection ---");
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
        System.out.println("----------------------------------");
    }

    public void showUserBorrowedBooks(User user) {
        System.out.println("\n--- Books Borrowed by " + user.getName() + " ---");
        if (user.getBorrowedBooks().isEmpty()) {
            System.out.println(user.getName() + " has not borrowed any books.");
            return;
        }
        for (Book b : user.getBorrowedBooks()) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
        System.out.println("----------------------------------");
    }

    public void issueBook(User user, String title) {
        Book foundBook = null;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                foundBook = b;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("ERROR: Book '" + title + "' not found in the library.");
            return;
        }

        // Check if the book is already issued
        if (foundBook.isIssued()) {
            System.out.println("ERROR: Book '" + title + "' is already issued.");
        } else {
            // Issue the book (marks it as issued in the Book object)
            foundBook.issueBook();
            // Add the book to the user's borrowed list
            user.borrowBook(foundBook);
            System.out.println("ACTION: '" + title + "' issued to " + user.getName() + ".");
        }
    }

    public void returnBook(User user, String title) {
        Book foundBookInUserList = null;
        for (Book b : user.getBorrowedBooks()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                foundBookInUserList = b;
                break;
            }
        }

        if (foundBookInUserList == null) {
            System.out.println("ERROR: '" + title + "' was not found in " + user.getName() + "'s borrowed list, or title is incorrect.");
            return;
        }

        // Only return if the book is actually marked as issued.
        // This handles cases where a book might somehow be in user's list but not marked issued.
        if (foundBookInUserList.isIssued()) {
            foundBookInUserList.returnBook(); // Mark book as not issued
            user.returnBook(foundBookInUserList); // Remove from user's borrowed list
            System.out.println("ACTION: '" + title + "' returned by " + user.getName() + ".");
        } else {
            System.out.println("ERROR: Book '" + title + "' found in " + user.getName() + "'s list but was not marked as issued.");
            // Optionally, you might still want to remove it from the user's list in this edge case:
            // user.returnBook(foundBookInUserList);
        }
    }
}

// Main class 
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        User currentUser = new User("Alice Smith", 101);
        library.addUser(currentUser);

        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt, David Thomas"));
        library.addBook(new Book("Introduction to Algorithms", "Thomas H. Cormen et al."));
        library.addBook(new Book("Designing Data-Intensive Applications", "Martin Kleppmann"));
        library.addBook(new Book("Cracking the Coding Interview", "Gayle Laakmann McDowell"));
        library.addBook(new Book("Structure and Interpretation of Computer Programs", "Harold Abelson, Gerald Jay Sussman"));

        int choice;
        do {
            System.out.println("\n===== Library Menu for " + currentUser.getName() + " =====");
            System.out.println("1. Show all books in library");
            System.out.println("2. Show books " + currentUser.getName() + " has borrowed");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                System.out.print("Enter your choice: ");
            }
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1 -> library.showAllBooks();
                case 2 -> library.showUserBorrowedBooks(currentUser);
                case 3 -> {
                    System.out.print("Enter the exact title of the book to issue: ");
                    String titleToIssue = sc.nextLine();
                    library.issueBook(currentUser, titleToIssue);
                }
                case 4 -> {
                    System.out.print("Enter the exact title of the book to return: ");
                    String titleToReturn = sc.nextLine();
                    library.returnBook(currentUser, titleToReturn);
                }
                case 5 -> System.out.println("Thank you for using the Library System!" + currentUser.getName() + "!");
                default -> System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        } while (choice != 5);

        sc.close();
    }
}