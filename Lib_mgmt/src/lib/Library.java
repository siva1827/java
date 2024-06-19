package lib;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void borrowBook(String isbn, String memberId) throws BookNotFoundException, MemberNotFoundException, BookAlreadyBorrowedException {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found.");
        }
        if (book.isBorrowed()) {
            throw new BookAlreadyBorrowedException("Book with ISBN " + isbn + " is already borrowed.");
        }

        book.setBorrowed(true);
        member.borrowBook(book);
        System.out.println(member.getName() + " borrowed " + book.getTitle());
    }

    public void returnBook(String isbn, String memberId) throws BookNotFoundException, MemberNotFoundException, BookNotBorrowedException {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        if (member == null) {
            throw new MemberNotFoundException("Member with ID " + memberId + " not found.");
        }
        if (!book.isBorrowed()) {
            throw new BookNotBorrowedException("Book with ISBN " + isbn + " is not borrowed.");
        }

        book.setBorrowed(false);
        member.returnBook(book);
        System.out.println(member.getName() + " returned " + book.getTitle());
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (!book.isBorrowed()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", members=" + members +
                '}';
    }
}

