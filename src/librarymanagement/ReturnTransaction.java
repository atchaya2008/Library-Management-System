package librarymanagement;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnTransaction {
    private Book book;
    private double fine;

    public ReturnTransaction(BorrowTransaction t) {
        book = t.getBook();
        long lateDays = ChronoUnit.DAYS.between(t.getDueDate(), LocalDate.now());

        if (lateDays > 0)
            fine = lateDays * 5;
        else
            fine = 0;
    }

    public Book getBook() { return book; }
    public double getFine() { return fine; }
}
