
# Library Management System

## Problem statement
Design and implement a Java console application for a Library Management System that manages books, members, borrowing, and returns. The system follows object-oriented principles and tracks availability.

## Features
- Add books and members (regular or premium).
- Borrow only when stock is available and the member is within their borrowing limit.
- Update stock when borrowing and returning.
- Premium members may borrow up to 5 books for 30 days; regular members may borrow up to 2 books for 14 days.
- Late return fine: Rs. 5 per day.
- Borrow and return receipts, available-book listing, and input validation.

## Classes
`Book`, `Author`, `Member`, `PremiumMember`, `BorrowTransaction`, `ReturnTransaction`, `Library`, `Main`.

## Run in Eclipse
1. Create a Java project named `LibraryManagementSystem`.
2. Under `src`, create package `librarymanagement`.
3. Add all eight `.java` files from this folder to that package.
4. Run `Main.java` as **Java Application**.

## Compile and run from a terminal
From the project directory:
```bash
javac -d out src/librarymanagement/*.java
java -cp out librarymanagement.Main
```

## OOP concepts
- Encapsulation: private fields and public methods.
- Inheritance: `PremiumMember extends Member`.
- Polymorphism: premium members override borrowing limit and duration methods.
- Aggregation: `Library` stores collections of independently created `Book` and `Member` objects.
- Association: borrowing and return transactions directly reference the relevant book and member.

## Author details
- Name: Atchaya B
- Roll No.: 717825i207
# Library-Management-System
Java assignment project implementing a Library Management System to handle book inventory, user records, and issue/return workflows

