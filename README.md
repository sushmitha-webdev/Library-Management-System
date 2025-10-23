# Library Management System

A simple Java-based console application for managing books and users in a library, developed to demonstrate Object-Oriented Programming (OOP) principles.

## Features

-   **Book Management:** Add new books with titles and authors.
-   **User Management:** Register users with unique IDs.
-   **Issue Books:** Users can borrow available books from the library.
-   **Return Books:** Users can return borrowed books.
-   **Status Tracking:** Books track their availability (issued/available).
-   **User-Specific Borrowing:** Users maintain a list of books they have currently borrowed.

## Technologies Used

-   **Java:** Core programming language.
-   **Eclipse IDE:** (Recommended) For development, compiling, and running.
-   **Terminal:** (Alternative) For compiling and running if not using an IDE.

## How to Run

### Using Eclipse (Recommended)

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/YourUsername/LibraryManagementSystem.git
    ```
2.  **Import as an Existing Java Project:**
    *   Open Eclipse.
    *   Go to `File` > `Import...`.
    *   Select `General` > `Existing Projects into Workspace` and click `Next`.
    *   Click `Browse...` next to "Select root directory" and navigate to the `LibraryManagementSystem` folder you just cloned.
    *   Ensure the project is checked and click `Finish`.
3.  **Run the application:**
    *   In the Package Explorer, navigate to `LibraryManagementSystem.java`.
    *   Right-click on `LibraryManagementSystem.java`.
    *   Select `Run As` > `Java Application`.
    *   The output will appear in the Eclipse Console view.

### Using Command Line (Alternative)

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/YourUsername/LibraryManagementSystem.git
    cd LibraryManagementSystem
    ```
2.  **Compile the Java code:**
    Open your terminal in the `LibraryManagementSystem` directory and compile the `.java` files:
    ```bash
    javac *.java
    ```
3.  **Run the application:**
    ```bash
    java LibraryManagementSystem
    ```

    The application will then present a console-based menu for interaction.

## Project Structure

-   `Book.java`: Defines the `Book` class with properties like title, author, and issue status.
-   `User.java`: Defines the `User` class with user details and a list of borrowed books.
-   `Library.java`: Manages the collection of books and users, handling operations like adding books, issuing, and returning.
-   `LibraryManagementSystem.java`: Contains the `main` method to run the console-based menu interface.

## OOP Principles Demonstrated

-   **Encapsulation:** Data (e.g., book title, user ID) is hidden within classes and accessed via public methods (getters/setters).
-   **Abstraction:** Users interact with simplified interfaces (e.g., `issueBook()`, `returnBook()`) without needing to know the complex underlying logic.
-   **Inheritance:** (Not explicitly used in this basic version, but can be extended, e.g., `PremiumUser` extending `User`).
-   **Polymorphism:** (Not explicitly used in this basic version, but could be introduced with interfaces or method overriding for different book types).

## Future Enhancements (Ideas)

-   Allow multiple users to log in.
-   Search functionality for books.
-   Add due dates and late return penalties.
-   Save/load library data to a file (CSV, JSON).
-   A graphical user interface (GUI).

## Contributing

Feel free to fork this repository, make improvements, and submit pull requests.

## License

This project is open source and available under the [MIT License](LICENSE).
