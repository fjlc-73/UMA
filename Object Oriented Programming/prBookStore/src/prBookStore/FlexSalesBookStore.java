package prBookStore;

public class FlexSalesBookStore extends BookStore {
	
	private FlexDiscount discount;
	
	public FlexSalesBookStore(FlexDiscount d) {
		super();
		discount = d;
		
	}
	
	public FlexSalesBookStore(int n, FlexDiscount d) {
		super(n);
		discount = d;
	}
	
	public FlexDiscount getDiscount() {return discount;}
	
	public void setSale (FlexDiscount d) {
		discount = d;
	}
	
	public void addBook(String a, String t, double p) {
		Book b = new Book(a,t,p);
		if (discount.getDiscount(b) != 0) {
			b = new BookOnSale(a,t,p,discount.getDiscount(b));
		}
		this.addBook(b);
	}
	
	public String toString() {
		String s = discount.toString();
		s+=super.toString();
		return s;
	}

}
