package lib;

public class BookNotBorrowedException extends Exception {
    public BookNotBorrowedException(String message) {
        super(message);
    }
}