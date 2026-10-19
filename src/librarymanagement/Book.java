package librarymanagement;

public class Book {
    private String id, title;
    private Author author;
    private int stock;

    public Book(String id, String title, Author author, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public int getStock() { return stock; }

    public void borrow() {
        if (stock > 0)
            stock--;
    }

    public void returnBook() {
        stock++;
    }
}
