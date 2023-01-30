package prBookStore;

public class Book {
	
	protected static double percVAT = 10;
	private String author, title;
	private double basePrice;
	
	public Book(String a, String t, double p) {
		author = a;
		title = t;
		basePrice = p;
	}
	
	public String getAuthor() { return author; }
	public String getTitle() { return title; }
	public double getBasePrice() { return basePrice; }
	
	public double getFinalPrice() {
		return basePrice + (basePrice*percVAT/100);
	}
	
	public String toString() {
		return ("(" +author + "; " + title + "; " + basePrice + "; " + percVAT + "%; " + getFinalPrice() + ")" );
	}
	
	public static double getVAT() { return percVAT; }
	
	public static void setVAT (double d) {
		percVAT = d;
		
	}
}
	
	
	
	
	
	
	


