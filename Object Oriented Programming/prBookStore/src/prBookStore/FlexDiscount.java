package prBookStore;

public interface FlexDiscount {
	
	public default double getDiscount(Book b) {
		if (b.getClass().getSimpleName() == "BookOnSale") {
			return ((BookOnSale) b).getDiscount();
		} else {
			return 0;
		}
	}
	

}
