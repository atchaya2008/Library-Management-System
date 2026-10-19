package librarymanagement;

public class Book {
    private String bookId;
    private String title;
    private Author author;
    private int totalStock;
    private int availableStock;

    public Book(String bookId, String title, Author author, int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalStock = stock;
        this.availableStock = stock;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public int getTotalStock() { return totalStock; }
    public int getAvailableStock() { return availableStock; }

    public boolean isAvailable() { return availableStock > 0; }

    public void borrowOne() {
        if (!isAvailable()) throw new IllegalStateException("Book is out of stock.");
        availableStock--;
    }

    public void returnOne() {
        if (availableStock >= totalStock) {
            throw new IllegalStateException("Returned quantity would exceed total stock.");
        }
        availableStock++;
    }

    @Override
    public String toString() {
        return bookId + " | " + title + " | Author: " + author.getName()
                + " | Available: " + availableStock + "/" + totalStock;
    }
}
