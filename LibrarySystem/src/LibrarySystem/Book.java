package LibrarySystem;

import java.io.Serializable;

public class Book implements Serializable {
    private int bookId;
    private String bookName;
    private String authorName;
   
	public Book( int bookId,String bookName, String authorName) {  
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
		
	}
	public int getbookId() {
		return bookId;
	}
	public void setbookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	
    
	
}