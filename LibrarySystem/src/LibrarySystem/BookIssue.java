package LibrarySystem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BookIssue implements Serializable {

	private Map<Integer, Book> issuedBooks;
	
	public BookIssue() {
        issuedBooks = new HashMap<>();
    }
	
	public void addIssuedBook(Book book) {
        issuedBooks.put(book.getbookId(), book);
    }

    public boolean isBookIssued(Book book) {
        return issuedBooks.containsKey(book.getbookId());
    }
	
}
