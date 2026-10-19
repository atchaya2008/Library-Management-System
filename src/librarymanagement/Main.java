package librarymanagement;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display Available Books");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Book ID: ");
                    String bid = sc.nextLine();
                    System.out.print("Book title: ");
                    String title = sc.nextLine();
                    System.out.print("Author name: ");
                    String author = sc.nextLine();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    if (stock < 0) {
                        System.out.println("Invalid stock!");
                        break;
                    }

                    lib.addBook(new Book(bid, title, new Author(author), stock));
                    break;

                case 2:
                    System.out.print("Member ID: ");
                    String mid = sc.nextLine();
                    System.out.print("Member name: ");
                    String name = sc.nextLine();
                    System.out.print("1. Regular  2. Premium: ");
                    int type = sc.nextInt();
                    sc.nextLine();

                    if (type == 1)
                        lib.addMember(new Member(mid, name));
                    else if (type == 2)
                        lib.addMember(new PremiumMember(mid, name));
                    else
                        System.out.println("Invalid member type!");
                    break;

                case 3:
                    System.out.print("Book ID: ");
                    bid = sc.nextLine();
                    System.out.print("Member ID: ");
                    mid = sc.nextLine();
                    lib.borrowBook(bid, mid);
                    break;

                case 4:
                    System.out.print("Transaction ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    lib.returnBook(id);
                    break;

                case 5:
                    lib.displayBooks();
                    break;

                case 6:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
