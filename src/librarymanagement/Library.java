package librarymanagement;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<BorrowTransaction> transactions = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
        System.out.println("Book added successfully!");
    }

    public void addMember(Member m) {
        members.add(m);
        System.out.println("Member added successfully!");
    }

    public void borrowBook(String bid, String mid) {
        Book b = null;
        Member m = null;

        for (Book x : books)
            if (x.getId().equals(bid))
                b = x;

        for (Member x : members)
            if (x.getId().equals(mid))
                m = x;

        if (b == null || m == null) {
            System.out.println("Book or member not found!");
            return;
        }

        int count = 0;
        for (BorrowTransaction t : transactions)
            if (t.getMember() == m && !t.isReturned())
                count++;

        if (b.getStock() == 0) {
            System.out.println("Book out of stock!");
        } else if (count >= m.getLimit()) {
            System.out.println("Borrowing limit reached!");
        } else {
            b.borrow();
            BorrowTransaction t = new BorrowTransaction(
                transactions.size() + 1, b, m
            );
            transactions.add(t);

            System.out.println("Borrow successful!");
            System.out.println("Transaction ID: " + t.getId());
            System.out.println("Book: " + b.getTitle());
            System.out.println("Due date: " + t.getDueDate());
            System.out.println("Updated stock: " + b.getStock());
        }
    }

    public void returnBook(int id) {
        for (BorrowTransaction t : transactions) {
            if (t.getId() == id && !t.isReturned()) {
                ReturnTransaction r = new ReturnTransaction(t);
                t.getBook().returnBook();
                t.markReturned();

                System.out.println("Return successful!");
                System.out.println("Book: " + r.getBook().getTitle());
                System.out.println("Fine: Rs. " + r.getFine());
                System.out.println("Updated stock: " + r.getBook().getStock());
                return;
            }
        }
        System.out.println("Invalid transaction ID!");
    }

    public void displayBooks() {
        for (Book b : books) {
            if (b.getStock() > 0) {
                System.out.println(b.getId() + " - " + b.getTitle()
                    + " - Stock: " + b.getStock());
            }
        }
    }
}
