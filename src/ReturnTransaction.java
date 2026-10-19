package librarymanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnTransaction {
    private final int transactionId;
    private final BorrowTransaction borrowTransaction;
    private final LocalDate returnDate;
    private final long lateDays;
    private final double fine;

    public ReturnTransaction(int transactionId, BorrowTransaction borrowTransaction, LocalDate returnDate) {
        this.transactionId = transactionId;
        this.borrowTransaction = borrowTransaction;
        this.returnDate = returnDate;
        this.lateDays = Math.max(0,
                ChronoUnit.DAYS.between(borrowTransaction.getDueDate(), returnDate));
        this.fine = lateDays * 5.0; // Fine: Rs. 5 per late day
    }

    public int getTransactionId() { return transactionId; }
    public BorrowTransaction getBorrowTransaction() { return borrowTransaction; }
    public Book getBook() { return borrowTransaction.getBook(); }
    public Member getMember() { return borrowTransaction.getMember(); }
    public LocalDate getReturnDate() { return returnDate; }
    public long getLateDays() { return lateDays; }
    public double getFine() { return fine; }
}
