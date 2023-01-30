package prBookStore;

public class BookOnSale extends Book {
	
	private double discountPerc;
	
	public BookOnSale (String a, String t, double p, double d) {
		super (a,t,p);
		discountPerc = d;
	}
	
	public double getDiscount() {return discountPerc;}
	
	public double getFinalPrice() {
		double Px;
		double Bf;
		
		Px = this.getBasePrice() - ((this.getBasePrice()*this.discountPerc)/100);
		Bf = Px + (Px*percVAT/100);
		return Bf;
	}
	
	protected double getDiscountedPrice() {
		return this.getBasePrice()-(this.getBasePrice()*(discountPerc/100));
	}
	
	public String toString() {
		return ("(" +this.getAuthor() + "; " + this.getTitle() + "; " + this.getBasePrice() + "; " + discountPerc + "%; " + this.getDiscountedPrice() + ";" + percVAT + "%;" + this.getFinalPrice() + ")" );
	}

}

