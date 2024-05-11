package LibrarySystem;

import java.io.*;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BooksMethods {
	private Map<Integer, Book> books;
	private final String CSV_FILE = "library_books.csv";

	public BooksMethods() {
		books = new HashMap<>();
	}

	private void addBook(Book book) {
		books.put(book.getbookId(), book);
	}

	private boolean deleteBook(int bookId) {
		if (books.containsKey(bookId)) {
			books.remove(bookId);
			return true;
		}
		return false;
	}

	private boolean isUniqueBookId(int bookId) {
		return !books.containsKey(bookId);
	}


	public void addBook(Scanner scanner) {
		try {
			int bookId;
			boolean isUniqueSerial;
			do {

				System.out.print("Enter the Book Id: ");
				bookId = scanner.nextInt();
				isUniqueSerial = isUniqueBookId(bookId);
				if (!isUniqueSerial) {
					System.out.println("Book Id already exists. Please enter a unique serial number.");
				}
			} while (!isUniqueSerial);
			System.out.print("Enter the book name: ");
			String bookName = scanner.next();

			System.out.print("Enter the author name: ");
			String authorName = scanner.next();
			if (authorName.matches("[a-zA-Z]+")) {

				addBook(new Book(bookId, bookName, authorName));

				System.out.println("Book added successfully!");

			} else {
				System.out.println("Give correct author name");
			}

		} catch (InputMismatchException ex) {
			System.out.println("BookId should be number");
			scanner.nextLine();
		} catch (Exception e) {
			System.out.println("Error occurred while adding book");
		}

	}

	public void searchBooks(Scanner scanner, BookSearch bookSearch) {
		List<Book> searchResult = null;
		try {
			int subchoice = 0;

			while (true) {

				System.out.println("\n===== Search Book =====");
				System.out.println("1. Search by Book Name");
				System.out.println("2. Search by Author Name");
				System.out.println("3. Search by Alphabet");
				System.out.println("0. Return to main menus");
				System.out.print("Enter your choice: ");
				try {
					subchoice = scanner.nextInt();
					
					switch (subchoice) {
					case 1:
						searchByNameSubChoice(searchResult, scanner, bookSearch);
						break;
					case 2:
						searchByAuthorNameSubChoice(searchResult, scanner, bookSearch);
						break;
					case 3:
						searchByAlphabetSubChoice(searchResult, scanner, bookSearch);
						break;
					case 0:
						System.out.println("Return to main menus");
						return;
					default:
						System.out.println("Invalid choice.");

					}
				} catch (Exception ex) {
					System.out.println("Enter from above options only ");
					scanner.nextLine();
				}

			}
		} catch (Exception ex) {
			System.out.println("Error occurred while searching a book");
		}

	}

	private void searchByNameSubChoice(List<Book> searchResult, Scanner scanner, BookSearch bookSearch) {
		System.out.print("Enter the book name: ");
		String bookName = scanner.next();
		searchResult = bookSearch.searchByName(books, bookName);
		if (!searchResult.isEmpty()) {
			System.out.println("Found book(s): ");
			for (Book book : searchResult) {
				System.out.println("Book Id: " + book.getbookId() + " , Book Name: " + book.getBookName()
						+ " , Author Name: " + book.getAuthorName());
			}
		} else {
			System.out.println("No matching books found.");
		}
	}

	private void searchByAuthorNameSubChoice(List<Book> searchResult, Scanner scanner, BookSearch bookSearch) {
		System.out.print("Enter the author name: ");
		String authorName = scanner.next();
		if (authorName.matches("[a-zA-Z]+")) {
			searchResult = bookSearch.searchByAuthor(books, authorName);
			if (!searchResult.isEmpty()) {
				System.out.println("Found book(s): ");
				for (Book book : searchResult) {
					System.out.println("Book Id: " + book.getbookId() + " , Book Name: " + book.getBookName()
							+ " , Author Name: " + book.getAuthorName());
				}
			} else {
				System.out.println("No matching books found.");
			}
		} else {
			System.out.println("Give correct author name");
		}
	}

	private void searchByAlphabetSubChoice(List<Book> searchResult, Scanner scanner, BookSearch bookSearch) {
		System.out.print("Enter the alphabet: ");
		char alphabet;

		String input = scanner.next();
		if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
			alphabet = input.charAt(0);
			searchResult = bookSearch.searchByAlphabet(books, alphabet);
			if (!searchResult.isEmpty()) {
				System.out.println("Found book(s): ");
				for (Book book : searchResult) {
					System.out.println("Book Id: " + book.getbookId() + " , Book Name: " + book.getBookName()
							+ " , Author Name: " + book.getAuthorName());
				}
			} else {
				System.out.println("No matching books found.");
			}
		} else {
			System.out.println("Give correct alphabet");
		}
	}

	public void issueBook(Scanner scanner, BookIssue bookIssue) {
		try {

			System.out.println("\n===== Issue a Book =====");
			System.out.print("Enter the Book id of the book to issue: ");

			if (scanner.hasNextInt()) {
				int bookId = scanner.nextInt();

				Book bookToIssue = books.get(bookId);

				if (bookToIssue != null) {
					if (!bookIssue.isBookIssued(bookToIssue)) {
						// The book is available and not already issued
						bookIssue.addIssuedBook(bookToIssue);
						System.out.println("Book with Book Id " + bookId + " has been issued.");
					} else {
						System.out.println("Book with Book Id " + bookId + " is already issued.");
					}
				} else {
					System.out.println("Book with Book Id " + bookId + " not found in the library.");
				}
			} else {
				System.out.println("Invalid input for Book Id. Please enter a valid integer.");
				scanner.nextLine();
			}
			scanner.nextLine();
		} catch (Exception ex) {
			System.out.println("error occurred while issuing a book");
		}
	}

	public void listAllBooks() {
		try {
			if (!books.isEmpty()) {
				System.out.println("\n===== List of All Books =====");
				for (Book book : books.values()) {
					System.out.println("Serial Number: " + book.getbookId() + " , Book Name: " + book.getBookName()
							+ " , Author Name: " + book.getAuthorName());
				}
			} else {
				System.out.println("No books is library");
			}
		} catch (Exception ex) {
			System.out.println("Error occurred while listing books");
		}
	}

	public void deleteBook(Scanner scanner) {
		try {
			System.out.println("\n===== Delete Book =====");
			System.out.print("Enter the book id : ");
			if (scanner.hasNextInt()) {
				int bookId = scanner.nextInt();
				boolean deleted = deleteBook(bookId);
				if (deleted) {
					System.out.println("Book with Book Id: " + bookId + " deleted successfully.");
				} else {
					System.out.println("Book with Book Id: " + bookId + " not found in the library.");
				}
			} else {
				System.out.println("Invalid input for Book Id:. Please enter a valid integer.");
				scanner.nextLine();
			}
			scanner.nextLine();
		} catch (Exception ex) {
			System.out.println("Error occurred while deleting a book ");
		}

	}

}
