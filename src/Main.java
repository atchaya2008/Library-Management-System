package librarymanagement;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        int choice;
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");
        do {
            printMenu();
            choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> addMember();
                    case 3 -> borrowBook();
                    case 4 -> returnBook();
                    case 5 -> displayAvailableBooks();
                    case 6 -> System.out.println("Exiting. Thank you!");
                    default -> System.out.println("Invalid choice. Enter a number from 1 to 6.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            System.out.println();
        } while (choice != 6);
        sc.close();
    }

    private static void printMenu() {
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Display Available Books");
        System.out.println("6. Exit");
    }

    private static void addBook() {
        System.out.print("Book ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Book title: ");
        String title = sc.nextLine().trim();
        System.out.print("Author name: ");
        String authorName = sc.nextLine().trim();
        System.out.print("Author email (optional): ");
        String email = sc.nextLine().trim();
        int stock = readInt("Initial stock: ");
        if (id.isEmpty() || title.isEmpty() || authorName.isEmpty())
            throw new IllegalArgumentException("ID, title, and author name cannot be empty.");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");

        library.addBook(new Book(id, title, new Author(authorName, email), stock));
        System.out.println("Book added successfully.");
        System.out.println("Updated stock: " + library.findBook(id).getAvailableStock());
    }

    private static void addMember() {
        System.out.print("Member ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Member name: ");
        String name = sc.nextLine().trim();
        System.out.print("Membership type (1-Regular, 2-Premium): ");
        int type = readInt("");
        if (id.isEmpty() || name.isEmpty())
            throw new IllegalArgumentException("Member ID and name cannot be empty.");
        if (type == 1) library.addMember(new Member(id, name));
        else if (type == 2) library.addMember(new PremiumMember(id, name));
        else throw new IllegalArgumentException("Choose 1 for Regular or 2 for Premium.");
        System.out.println("Member added successfully.");
    }

    private static void borrowBook() {
        System.out.print("Book ID: ");
        String bookId = sc.nextLine().trim();
        System.out.print("Member ID: ");
        String memberId = sc.nextLine().trim();
        BorrowTransaction tx = library.borrowBook(bookId, memberId);
        System.out.println("\n--- BORROW RECEIPT ---");
        System.out.println("Transaction ID: " + tx.getTransactionId());
        System.out.println("Book: " + tx.getBook().getTitle() + " (" + tx.getBook().getBookId() + ")");
        System.out.println("Member: " + tx.getMember().getName());
        System.out.println("Borrow date: " + tx.getBorrowDate());
        System.out.println("Due date: " + tx.getDueDate());
        System.out.println("Updated stock: " + tx.getBook().getAvailableStock());
    }

    private static void returnBook() {
        int id = readInt("Borrow transaction ID: ");
        ReturnTransaction tx = library.returnBook(id);
        System.out.println("\n--- RETURN RECEIPT ---");
        System.out.println("Return transaction ID: " + tx.getTransactionId());
        System.out.println("Book: " + tx.getBook().getTitle() + " (" + tx.getBook().getBookId() + ")");
        System.out.println("Member: " + tx.getMember().getName());
        System.out.println("Return date: " + tx.getReturnDate());
        System.out.println("Late days: " + tx.getLateDays());
        System.out.printf("Fine: Rs. %.2f%n", tx.getFine());
        System.out.println("Updated stock: " + tx.getBook().getAvailableStock());
    }

    private static void displayAvailableBooks() {
        System.out.println("--- AVAILABLE BOOKS ---");
        if (library.getAvailableBooks().isEmpty()) {
            System.out.println("No books are currently available.");
            return;
        }
        for (Book book : library.getAvailableBooks()) System.out.println(book);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }
}
