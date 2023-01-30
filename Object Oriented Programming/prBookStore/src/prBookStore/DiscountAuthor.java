package prBookStore;

public class DiscountAuthor implements FlexDiscount {
	
	private double discountPerc;
	private String[] authorsOnSale;
	
	public DiscountAuthor(double d, String[] a) {
		discountPerc = d;
		authorsOnSale = a;
	}
	
	public double getDiscount(Book b) {
		boolean found = false;
		for (int i = 0; i < authorsOnSale.length; i++) {
			if(b.getAuthor().equalsIgnoreCase(authorsOnSale[i])) {
				found = true;
			}
		}
		if (found) {
			return discountPerc;
		}
		return 0;
	}
	
	public String toString() {
		String s = discountPerc + "%[";
		for (int i = 0; i < authorsOnSale.length; i++) {
			s+=authorsOnSale[i];
			if (i < authorsOnSale.length-1) {
				s+=", ";
				}
		}
		s+="]";
		return s;
	}
}
		
       
		
