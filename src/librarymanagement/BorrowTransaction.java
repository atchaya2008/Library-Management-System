package librarymanagement;

import java.time.LocalDate;

public class BorrowTransaction {
    private int id;
    private Book book;
    private Member member;
    private LocalDate dueDate;
    private boolean returned;

    public BorrowTransaction(int id, Book book, Member member) {
        this.id = id;
        this.book = book;
        this.member = member;
        dueDate = LocalDate.now().plusDays(member.getDays());
        returned = false;
    }

    public int getId() { return id; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return returned; }
    public void markReturned() { returned = true; }
}
