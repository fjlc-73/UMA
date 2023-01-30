package prBookStore;

public class SalesBookStore extends BookStore {
	
	private int numauthors;
	private double discountPerc;
	private String[] authors;
	
	public SalesBookStore(double d, String[] a) {
		this(INITIAL_CAPACITY, d, a);
		
		
	}
	
	public SalesBookStore(int n, double d, String[] a) {
		super(n);
		discountPerc = d;
		authors = a;
		numauthors = a.length;
	}
	
	public double getDiscount() {return discountPerc;}
	
	public void addBook(String a, String t, double p) {
		int pos = this.seekSalesAuthor(a);
		if (pos != -1) {
			BookOnSale b1 = new BookOnSale(a,t,p,discountPerc);
			this.addBook(b1);
		} else {
			Book b2 = new Book(a, t, p);
			this.addBook(b2);
		}
	}
	
	public void setSale(double d, String[] s) {
		discountPerc=d;
		authors=s;
	}
	
	public String[] getAuthorsOnSale() {return authors;}
	
	public String toString() {
		String s = discountPerc + "%" + "[";
		
		for (int i = 0; i < numauthors; i++) {
			s += authors[i];
			if (i < numauthors-1) {
				s+=",";
				}
		}
		
		s+= "]" +super.toString();
		return s;
	}
	
	
	
	
	
	private int seekSalesAuthor(String a) {
		boolean found = false;
		int cont = 0;
		while (!found && cont<numauthors) {
			if (a.equalsIgnoreCase(authors[cont]))  {
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
