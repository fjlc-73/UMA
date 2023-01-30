package prBookStore;

public class DiscountPrice implements FlexDiscount {

	private double discountPerc;
	private double priceThreshold;
	
	public DiscountPrice(double d, double p) {
		discountPerc = d;
		priceThreshold = p;
	}
	
	public double getDiscount(Book b) {
		if (b.getBasePrice() >= priceThreshold) {
			return discountPerc;
		}
		return 0;
	}
	
	public String toString() {
		return discountPerc + "%(" + priceThreshold + ")";
	}
	
}
