package librarymanagement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<BorrowTransaction> borrowTransactions = new ArrayList<>();
    private final List<ReturnTransaction> returnTransactions = new ArrayList<>();
    private int nextBorrowId = 1;
    private int nextReturnId = 1;

    public void addBook(Book book) {
        if (findBook(book.getBookId()) != null)
            throw new IllegalArgumentException("A book with this ID already exists.");
        books.add(book);
    }

    public void addMember(Member member) {
        if (findMember(member.getMemberId()) != null)
            throw new IllegalArgumentException("A member with this ID already exists.");
        members.add(member);
    }

    public Book findBook(String id) {
        for (Book book : books)
            if (book.getBookId().equalsIgnoreCase(id)) return book;
        return null;
    }

    public Member findMember(String id) {
        for (Member member : members)
            if (member.getMemberId().equalsIgnoreCase(id)) return member;
        return null;
    }

    public BorrowTransaction borrowBook(String bookId, String memberId) {
        Book book = findBook(bookId);
        Member member = findMember(memberId);
        if (book == null) throw new IllegalArgumentException("Book ID not found.");
        if (member == null) throw new IllegalArgumentException("Member ID not found.");
        if (!book.isAvailable()) throw new IllegalStateException("Book is out of stock.");

        int activeCount = 0;
        for (BorrowTransaction tx : borrowTransactions) {
            if (tx.getMember().getMemberId().equalsIgnoreCase(memberId) && !tx.isReturned())
                activeCount++;
        }
        if (activeCount >= member.getBorrowLimit())
            throw new IllegalStateException("Borrowing limit reached (" + member.getBorrowLimit() + ").");

        book.borrowOne();
        BorrowTransaction tx = new BorrowTransaction(nextBorrowId++, book, member, LocalDate.now());
        borrowTransactions.add(tx);
        return tx;
    }

    public ReturnTransaction returnBook(int borrowId) {
        BorrowTransaction borrowTx = null;
        for (BorrowTransaction tx : borrowTransactions) {
            if (tx.getTransactionId() == borrowId) {
                borrowTx = tx;
                break;
            }
        }
        if (borrowTx == null) throw new IllegalArgumentException("Borrow transaction ID not found.");
        if (borrowTx.isReturned()) throw new IllegalStateException("This book has already been returned.");

        ReturnTransaction returnTx = new ReturnTransaction(nextReturnId++, borrowTx, LocalDate.now());
        borrowTx.getBook().returnOne();
        borrowTx.markReturned();
        returnTransactions.add(returnTx);
        return returnTx;
    }

    public List<Book> getBooks() { return new ArrayList<>(books); }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books) if (book.isAvailable()) available.add(book);
        return available;
    }
}
