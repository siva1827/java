package lib;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // Add books
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "123456789"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "987654321"));

        // Register members
        library.registerMember(new Member("Alice", "M001"));
        library.registerMember(new Member("Bob", "M002"));

        // Borrow books
        try {
            library.borrowBook("123456789", "M001");
            library.borrowBook("987654321", "M002");
            library.borrowBook("123456789", "M002"); // This will throw an exception
        } catch (BookNotFoundException | MemberNotFoundException | BookAlreadyBorrowedException e) {
            System.out.println(e.getMessage());
        }

        // Display available books
        System.out.println("Available books: " + library.getAvailableBooks());

        // Return books
        try {
            library.returnBook("123456789", "M001");
            library.returnBook("987654321", "M002");
            library.returnBook("123456789", "M002"); // This will throw an exception
        } catch (BookNotFoundException | MemberNotFoundException | BookNotBorrowedException e) {
            System.out.println(e.getMessage());
        }

        // Display available books
        System.out.println("Available books: " + library.getAvailableBooks());
    }
}
