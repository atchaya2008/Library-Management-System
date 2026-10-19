package librarymanagement;

import java.time.LocalDate;

public class BorrowTransaction {
    private final int transactionId;
    private final Book book;
    private final Member member;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;
    private boolean returned;

    public BorrowTransaction(int transactionId, Book book, Member member, LocalDate borrowDate) {
        this.transactionId = transactionId;
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(member.getBorrowDurationDays());
        this.returned = false;
    }

    public int getTransactionId() { return transactionId; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public void markReturned() { returned = true; }
}
