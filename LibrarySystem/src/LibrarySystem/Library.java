package LibrarySystem;

import java.util.InputMismatchException;

import java.util.Scanner;

public class Library {

	public static void main(String[] args) {
		try {
		BooksMethods booksOp = new BooksMethods();
		BookSearch bookSearch = new BookSearch();
		BookIssue bookIssue = new BookIssue();
		Scanner scanner = new Scanner(System.in);

		int choice = 0;
		while(true){
			
			System.out.println("\n===== Library Management System =====");
			System.out.println("1. Add a book");
			System.out.println("2. Search book");
			System.out.println("3. Issue a book");
			System.out.println("4. List all books");
			System.out.println("5. Delete a book");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");

			try {
				
				choice = scanner.nextInt();

				switch (choice) {
				case 1:

					booksOp.addBook(scanner);
					break;
				case 2:
					booksOp.searchBooks(scanner, bookSearch);
					break;
				case 3:
					booksOp.issueBook(scanner, bookIssue);
					break;
				case 4:
					booksOp.listAllBooks();
					break;
				case 5:
					booksOp.deleteBook(scanner);
					break;
				case 0:
					System.out.println("Exiting the Library Management System.");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException ex) {
				System.out.println("Enter from above options only");
				scanner.nextLine();
			}
//			booksOp.saveToCSV();

		} 
		}catch(Exception ex) {
			System.out.println("Error occurred while displaying main menus");
		}
		
	}

}
