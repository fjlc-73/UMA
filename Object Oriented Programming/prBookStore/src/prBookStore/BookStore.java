package prBookStore;

import java.util.Arrays;



public class BookStore {

	protected static final int INITIAL_CAPACITY = 8;
	private int numBooks;
	private Book[] books;

	public BookStore() {
		this(INITIAL_CAPACITY);
	}

	public BookStore(int n) {
		books = new Book[n];
	}

    public void addBook(String a, String t, double p) {
		
		this.addBook(new Book(a, t, p));
	}

	public void deleteBook(String a, String t) {
		int pos = this.seekBook(a, t);
		if (pos != -1) {
			this.deleteBook(pos);
		}
	}

	public double getBasePrice(String a, String t) {
		int pos = this.seekBook(a, t);
		if (pos != -1) {
			return books[pos].getBasePrice();
		} else {
			return 0;
		}
	}
	
	public double getFinalPrice(String a, String t) {
		int pos = this.seekBook(a, t);
		if (pos != -1) {
			return books[pos].getFinalPrice();
		} else {
			return 0;
		}
	}
	
	public String toString() {
		String ans = "[";
		
		for (int i = 0; i < numBooks; i++) {
			ans+=books[i];
			if (i < numBooks-1) {
			ans+=",";
			}
			
		}
		ans+="]";
		return ans;
		
	}

	protected void addBook(Book b) {
		int pos = this.seekBook(b.getAuthor(), b.getTitle());
		if (pos != -1) {
			books[pos] = b;
		} else if (numBooks == books.length) {
			books = Arrays.copyOf(books, numBooks*2);
			books[numBooks]=b;
			numBooks++;
		} else {
			books[numBooks]=b;
			numBooks++;
		}
	}
	
	private void deleteBook(int n) {
		books[n]=books[numBooks-1];
		numBooks--;
	}
	
	private int seekBook(String a, String t) {
		boolean found = false;
		int cont = 0;
		while (!found && cont<numBooks) {
			if (a.equalsIgnoreCase(books[cont].getAuthor())  && (t.equalsIgnoreCase(books[cont].getTitle()))) {
				found = true;
			} else {
				cont++;
			}
		}
		
		if (found) {
			return cont;
		} else {
			return -1;
		}
	}
	
	

}
