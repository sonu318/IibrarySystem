package LibrarySystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookSearch implements Serializable {
	public List<Book> searchByName(Map<Integer, Book> books, String bookName) {
		List<Book> result = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getBookName().equalsIgnoreCase(bookName)) {
				result.add(book);
			}
		}
		return result;
	}

	public List<Book> searchByAuthor(Map<Integer, Book> books, String authorName) {
		List<Book> result = new ArrayList<>();
		for (Book book : books.values()) {
			if (book.getAuthorName().equalsIgnoreCase(authorName)) {
				result.add(book);
			}
		}
		return result;
	}

	public List<Book> searchByAlphabet(Map<Integer, Book> books, char alphabet) {
		List<Book> result = new ArrayList<>();
		for (Book book :books.values()) {
			if (Character.toUpperCase(book.getBookName().charAt(0)) == Character.toUpperCase(alphabet)) {
				result.add(book);
			}
		}
		return result;
	}

}
