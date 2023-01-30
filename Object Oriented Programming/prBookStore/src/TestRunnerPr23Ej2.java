
//--------------------------------------------------------------------------
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import prBookStore.*;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

//--------------------------------------------------------------------------

public class TestRunnerPr23Ej2 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestBook {
		private Book lb1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Book JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of Book JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			lb1 = new Book("Isaac Asimov", "La Fundacion", 7.30);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void bookCtorTest1() {
			assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30): Autor:", "Isaac Asimov", lb1.getAuthor());
			assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30): Titulo:", "La Fundacion", lb1.getTitle());
			assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30): BasePrice:", 7.30, lb1.getBasePrice(), 1e-6);
			assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30): PorcIva:", 10.00, Book.getVAT(), 1e-6);
		}
		//@Test(timeout = 1000)
		//public void bookCtorTestX1() {
		//	try {
		//		Book lb2 = new Book("Isaac Asimov", "La Fundacion", -7.30);
		//		fail("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30): No se lanzo ninguna excepcion");
		//	} catch (RuntimeException e) {
		//		//assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30): exception.getMessage():", "", e.getMessage());
		//	} catch (Exception e) {
		//		fail("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30): la excepcion lanzada no es RuntimeException");
		//	}
		//}
		@Test(timeout = 1000)
		public void bookFinalPriceTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("La Fundacion", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			assertEquals("\n> Error: lb1.getFinalPrice(): ", 8.03, lb1.getFinalPrice(), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookSetVatTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("La Fundacion", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			Book.setVAT(20.00);
			double ivaActual = Book.getVAT();
			Book.setVAT(10.00);
			assertEquals("\n> Error: Book.setIVA(20.00): ", 20.00, ivaActual, 1e-6);
		}
		@Test(timeout = 1000)
		public void bookToStringTest1() {
			precond("Isaac Asimov", lb1.getAuthor());
			precond("La Fundacion", lb1.getTitle());
			precond(7.30, lb1.getBasePrice(), 1e-6);
			precond(10.0, Book.getVAT(), 1e-6);
			assertEquals("\n> Error: lb1.toString():",
						 normalize("(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03)"),
						 normalize(lb1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestBookOnSale {
		private BookOnSale lo1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of BookOnSale JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookOnSale JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			lo1 = new BookOnSale("Isaac Asimov", "La Fundacion", 7.30, 20.0);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void salesBookCtorTest1() {
			assertTrue("\n> Error: BookOnSale extends Book:", ((Object)lo1 instanceof Book));
			assertEquals("\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): Autor:", "Isaac Asimov", lo1.getAuthor());
			assertEquals("\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): Titulo:", "La Fundacion", lo1.getTitle());
			assertEquals("\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): BasePrice:", 7.30, lo1.getBasePrice(), 1e-6);
			assertEquals("\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): PorcDto:", 20.00, lo1.getDiscount(), 1e-6);
			assertEquals("\n> Error: new BookOnSale(\"Isaac Asimov\", \"La Fundacion\", 7.30, 20.0): PorcIva:", 10.00, BookOnSale.getVAT(), 1e-6);
		}
		//@Test(timeout = 1000)
		//public void salesBookCtorTestX1() {
		//	try {
		//		Book lb2 = new Book("Isaac Asimov", "La Fundacion", -7.30, -20.0);
		//		fail("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30, -20.0): No se lanzo ninguna excepcion");
		//	} catch (RuntimeException e) {
		//		//assertEquals("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30, -20.0): exception.getMessage():", "", e.getMessage());
		//	} catch (Exception e) {
		//		fail("\n> Error: new Book(\"Isaac Asimov\", \"La Fundacion\", -7.30, -20.0): la excepcion lanzada no es RuntimeException");
		//	}
		//}
		@Test(timeout = 1000)
		public void salesBookFinalPriceTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("La Fundacion", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			assertEquals("\n> Error: lo1.getFinalPrice(): ", 6.424, lo1.getFinalPrice(), 1e-6);
		}
		@Test(timeout = 1000)
		public void salesBookSetVatTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("La Fundacion", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			Book.setVAT(20.00);
			double ivaActual = Book.getVAT();
			Book.setVAT(10.00);
			assertEquals("\n> Error: Book.setIVA(20.00): ", 20.00, ivaActual, 1e-6);
		}
		@Test(timeout = 1000)
		public void salesBookToStringTest1() {
			precond("Isaac Asimov", lo1.getAuthor());
			precond("La Fundacion", lo1.getTitle());
			precond(7.30, lo1.getBasePrice(), 1e-6);
			precond(20.0, lo1.getDiscount(), 1e-6);
			precond(10.0, BookOnSale.getVAT(), 1e-6);
			assertEquals("\n> Error: lo1.toString():",
						 normalize("(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995)"),
						 normalize(lo1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestBookStore {
		private BookStore lr1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of BookStore JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of BookStore JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			lr1 = new BookStore(2);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		// Carlos (19/03/19) // Vicente (21/03/19)
		private static final Class arrayBookClass = (new Book[1]).getClass();
		private static Book[] getArrayBooks(BookStore lb) {
			Book[] libs = null;
			boolean encontrado = false;
			try {
				Class bookStoreClass = lb.getClass();
				java.lang.reflect.Field[] bookStoreFields = bookStoreClass.getDeclaredFields();
				int i = 0;
				while ((i < bookStoreFields.length)
					   &&(bookStoreFields[i].getType() != arrayBookClass)) {
					++i;
				}
				if (i < bookStoreFields.length) {
					bookStoreFields[i].setAccessible(true);
					libs = (Book[])bookStoreFields[i].get(lb);
					encontrado = true;
				}
			} catch (Throwable e) {
				fail("\n> Error: getArrayBooks has thrown an exception " + e);
			}
			if (! encontrado) {
				fail("\n> Error: class BookStore does not have a book array Book[]");
			}
			if (libs == null) {
				fail("\n> Error: the book array has not been properly created");
			}
			return libs;
		} 
		@Test(timeout = 1000)
		public void bookStoreCtorTest1() {
			BookStore lr2 = new BookStore();
			// Carlos (19/03/19)
			assertEquals("\n> Error: new BookStore(): capacity of the book array:", 8, getArrayBooks(lr2).length);
		}

		// Carlos (19/03/19)
		@Test(timeout = 1000)
		public void bookStoreCtorTest2() {
			assertEquals("\n> Error: lr1: capacity of the book array:", 2, getArrayBooks(lr1).length);
		}
			
		@Test(timeout = 1000)
		public void bookStoreAddBookTest1() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			
			// Carlos (19/03/19)
			assertEquals("\n> Error: lr1: capacity of the book array:", 4, getArrayBooks(lr1).length);
						
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			
			// Carlos (19/03/19)
			assertEquals("\n> Error: lr1: capacity of the book array:", 8, getArrayBooks(lr1).length);

			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lr1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lr1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lr1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lr1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lr1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreAddBookTest2() {
			lr1.addBook("isaac asimov", "la fundacion", 5.30);
			lr1.addBook("aldous huxley", "un mundo feliz", 4.50);
			lr1.addBook("william gibson", "neuromante", 6.30);
			lr1.addBook("george orwell", "1984", 4.20);
			lr1.addBook("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lr1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lr1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lr1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lr1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lr1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lr1.getBasePrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lr1.getBasePrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lr1.getBasePrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lr1.getBasePrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lr1.getBasePrice("ray bradbury", "fahrenheit 451"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreGetBasePriceTest1() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lr1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lr1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lr1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lr1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lr1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 0.00, lr1.getBasePrice("XXX", "XXX"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreGetBasePriceTest2() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lr1.getBasePrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lr1.getBasePrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lr1.getBasePrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lr1.getBasePrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lr1.getBasePrice("ray bradbury", "fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 0.00, lr1.getBasePrice("xxx", "xxx"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreGetFinalPriceTest1() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.03, lr1.getFinalPrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 7.15, lr1.getFinalPrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 9.13, lr1.getFinalPrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 6.82, lr1.getFinalPrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.14, lr1.getFinalPrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 0.00, lr1.getFinalPrice("XXX", "XXX"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreGetFinalPriceTest2() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.03, lr1.getFinalPrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 7.15, lr1.getFinalPrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 9.13, lr1.getFinalPrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 6.82, lr1.getFinalPrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.14, lr1.getFinalPrice("ray bradbury", "fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 0.00, lr1.getFinalPrice("xxx", "xxx"), 1e-6);
		}
		@Test(timeout = 1000)
		public void bookStoreDeleteBookTest1() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(7.30, lr1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			precond(6.50, lr1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			precond(8.30, lr1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			precond(6.20, lr1.getBasePrice("George Orwell", "1984"), 1e-6);
			precond(7.40, lr1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			lr1.deleteBook("Isaac Asimov", "La Fundacion");
			lr1.deleteBook("Aldous Huxley", "Un Mundo Feliz");
			lr1.deleteBook("William Gibson", "Neuromante");
			lr1.deleteBook("George Orwell", "1984");
			lr1.deleteBook("Ray Bradbury", "Fahrenheit 451");
			//------------------------
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lr1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lr1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lr1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lr1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lr1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			lr1.deleteBook("xxx", "xxx");
		}
		@Test(timeout = 1000)
		public void bookStoreToStringTest1() {
			assertEquals("\n> Error: lr1.toString():",
						 normalize("[]"),
						 normalize(lr1.toString()));
		}
		@Test(timeout = 1000)
		public void bookStoreToStringTest2() {
			lr1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lr1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lr1.addBook("William Gibson", "Neuromante", 8.30);
			lr1.addBook("George Orwell", "1984", 6.20);
			lr1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: lr1.toString():",
						 normalize("[(Isaac Asimov; La Fundacion; 7.3; 10.0%; 8.03), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 10.0%; 6.82), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lr1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestDiscountAuthor {
		private static final String[] discountAuthors = { "george orwell", "isaac asimov" };
		private DiscountAuthor oa1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of DiscountAuthor JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of DiscountAuthor JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			oa1 = new DiscountAuthor(20.0, discountAuthors);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void discountAuthorCtorTest1() {
			assertTrue("\n> Error: DiscountAuthor implements FlexDiscount:", ((Object)oa1 instanceof FlexDiscount));
		}
		@Test(timeout = 1000)
		public void discountAuthorGetDiscountTest1() {
			Book lb1 = new Book("Isaac Asimov", "La Fundacion", 7.30);
			assertEquals("\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDiscount(new Book(\"Isaac Asimov\", \"La Fundacion\", 7.30)):", 20.0, oa1.getDiscount(lb1), 1e-6);
		}
		@Test(timeout = 1000)
		public void discountAuthorGetDiscountTest2() {
			Book lb1 = new Book("George Orwell", "1984", 6.20);
			assertEquals("\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDiscount(new Book(\"George Orwell\", \"1984\", 6.20)):", 20.0, oa1.getDiscount(lb1), 1e-6);
		}
		@Test(timeout = 1000)
		public void discountAuthorGetDiscountTest3() {
			Book lb1 = new Book("Aldous Huxley", "Un Mundo Feliz", 6.50);
			assertEquals("\n> Error: new DiscountAuthor({\"george orwell\", \"isaac asimov\"}): getDiscount(new Book(\"Aldous Huxley\", \"Un Mundo Feliz\", 6.50)):", 0.0, oa1.getDiscount(lb1), 1e-6);
		}
		@Test(timeout = 1000)
		public void discountAuthorToStringTest1() {
			assertEquals("\n> Error: oa1.toString():",
						 normalize("20.0%[george orwell, isaac asimov]"),
						 normalize(oa1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestDiscountPrice {
		private DiscountPrice op1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of DiscountPrice JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of DiscountPrice JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			op1 = new DiscountPrice(20.0, 10.0);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void discountPriceCtorTest1() {
			assertTrue("\n> Error: DiscountPrice implements FlexDiscount:", ((Object)op1 instanceof FlexDiscount));
		}
		@Test(timeout = 1000)
		public void discountPriceGetDiscountTest1() {
			Book lb1 = new Book("Isaac Asimov", "La Fundacion", 9.90);
			assertEquals("\n> Error: new DiscountPrice(20.0, 10.0): getDiscount(new Book(\"Isaac Asimov\", \"La Fundacion\", 9.90)):", 0.0, op1.getDiscount(lb1), 1e-6);
		}
		@Test(timeout = 1000)
		public void discountPriceGetDiscountTest2() {
			Book lb1 = new Book("Isaac Asimov", "La Fundacion", 10.00);
			assertEquals("\n> Error: new DiscountPrice(20.0, 10.0): getDiscount(new Book(\"Isaac Asimov\", \"La Fundacion\", 10.00)):", 20.0, op1.getDiscount(lb1), 1e-6);
		}
		@Test(timeout = 1000)
		public void discountPriceToStringTest1() {
			assertEquals("\n> Error: op1.toString():",
						 normalize("20.0%(10.0)"),
						 normalize(op1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestFlexSalesBookStore {
		private static final String[] discountAuthors = { "george orwell", "isaac asimov" };
		private DiscountAuthor oa1;
		private FlexSalesBookStore lrof1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of FlexSalesBookStore JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of FlexSalesBookStore JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			oa1 = new DiscountAuthor(20.0, discountAuthors);
			lrof1 = new FlexSalesBookStore(2, oa1);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreCtorTest1() {
			assertTrue("\n> Error: FlexSalesBookStore extends BookStore:", ((Object)lrof1 instanceof BookStore));
			assertSame("\n> Error: getAuthorsOnSale():", oa1, lrof1.getDiscount());
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreCtorTest2() {
			FlexSalesBookStore lrof2 = new FlexSalesBookStore(oa1);
			assertTrue("\n> Error: FlexSalesBookStore extends BookStore:", ((Object)lrof2 instanceof BookStore));
			assertSame("\n> Error: getAuthorsOnSale():", oa1, lrof2.getDiscount());
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreSetSaleTest1() {
			FlexSalesBookStore lrof2 = new FlexSalesBookStore(oa1);
			precond(oa1, lrof2.getDiscount());
			DiscountAuthor oa2 = new DiscountAuthor(20.0, discountAuthors);
			lrof2.setSale(oa2);
			assertSame("\n> Error: getAuthorsOnSale():", oa2, lrof2.getDiscount());
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreAddBookTest1() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lrof1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lrof1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lrof1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lrof1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lrof1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreAddBookTest2() {
			lrof1.addBook("isaac asimov", "la fundacion", 5.30);
			lrof1.addBook("aldous huxley", "un mundo feliz", 4.50);
			lrof1.addBook("william gibson", "neuromante", 6.30);
			lrof1.addBook("george orwell", "1984", 4.20);
			lrof1.addBook("ray bradbury", "fahrenheit 451", 5.40);
			//------------------------
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lrof1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lrof1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lrof1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lrof1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lrof1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lrof1.getBasePrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lrof1.getBasePrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lrof1.getBasePrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lrof1.getBasePrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lrof1.getBasePrice("ray bradbury", "fahrenheit 451"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreGetBasePriceTest1() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lrof1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lrof1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lrof1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lrof1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lrof1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 0.00, lrof1.getBasePrice("XXX", "XXX"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreGetBasePriceTest2() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.30, lrof1.getBasePrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.50, lrof1.getBasePrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 8.30, lrof1.getBasePrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 6.20, lrof1.getBasePrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getBasePrice():", 7.40, lrof1.getBasePrice("ray bradbury", "fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getBasePrice():", 0.00, lrof1.getBasePrice("xxx", "xxx"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreGetFinalPriceTest1() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 6.424, lrof1.getFinalPrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 7.15, lrof1.getFinalPrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 9.13, lrof1.getFinalPrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 5.456, lrof1.getFinalPrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.14, lrof1.getFinalPrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 0.00, lrof1.getFinalPrice("XXX", "XXX"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreGetFinalPriceTest2() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 6.424, lrof1.getFinalPrice("isaac asimov", "la fundacion"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 7.15, lrof1.getFinalPrice("aldous huxley", "un mundo feliz"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 9.13, lrof1.getFinalPrice("william gibson", "neuromante"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 5.456, lrof1.getFinalPrice("george orwell", "1984"), 1e-6);
			assertEquals("\n> Error: addBook(): getFinalPrice():", 8.14, lrof1.getFinalPrice("ray bradbury", "fahrenheit 451"), 1e-6);
			//------------------------
			assertEquals("\n> Error: addBook(): getFinalPrice():", 0.00, lrof1.getFinalPrice("xxx", "xxx"), 1e-6);
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreDeleteBookTest1() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			precond(7.30, lrof1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			precond(6.50, lrof1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			precond(8.30, lrof1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			precond(6.20, lrof1.getBasePrice("George Orwell", "1984"), 1e-6);
			precond(7.40, lrof1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			lrof1.deleteBook("Isaac Asimov", "La Fundacion");
			lrof1.deleteBook("Aldous Huxley", "Un Mundo Feliz");
			lrof1.deleteBook("William Gibson", "Neuromante");
			lrof1.deleteBook("George Orwell", "1984");
			lrof1.deleteBook("Ray Bradbury", "Fahrenheit 451");
			//------------------------
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lrof1.getBasePrice("Isaac Asimov", "La Fundacion"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lrof1.getBasePrice("Aldous Huxley", "Un Mundo Feliz"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lrof1.getBasePrice("William Gibson", "Neuromante"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lrof1.getBasePrice("George Orwell", "1984"), 1e-6);
			assertEquals("\n> Error: deleteBook(): getBasePrice():", 0.00, lrof1.getBasePrice("Ray Bradbury", "Fahrenheit 451"), 1e-6);
			//------------------------
			lrof1.deleteBook("xxx", "xxx");
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreToStringTest1() {
			assertEquals("\n> Error: lrof1.toString():",
						 normalize("20.0%[george orwell, isaac asimov][]"),
						 normalize(lrof1.toString()));
		}
		@Test(timeout = 1000)
		public void flexSalesBookStoreToStringTest2() {
			lrof1.addBook("Isaac Asimov", "La Fundacion", 7.30);
			lrof1.addBook("Aldous Huxley", "Un Mundo Feliz", 6.50);
			lrof1.addBook("William Gibson", "Neuromante", 8.30);
			lrof1.addBook("George Orwell", "1984", 6.20);
			lrof1.addBook("Ray Bradbury", "Fahrenheit 451", 7.40);
			//------------------------
			assertEquals("\n> Error: lrof1.toString():",
						 normalize("20.0%[george orwell, isaac asimov][(Isaac Asimov; La Fundacion; 7.3; 20.0%; 5.84; 10.0%; 6.4239999999999995), (Aldous Huxley; Un Mundo Feliz; 6.5; 10.0%; 7.15), (William Gibson; Neuromante; 8.3; 10.0%; 9.13), (George Orwell; 1984; 6.2; 20.0 % ; 4.96 ; 10.0 % ; 5.4559999999999995), (Ray Bradbury; Fahrenheit 451; 7.4; 10.0%; 8.14)]"),
						 normalize(lrof1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestMainFlexSalesBookStore {
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainFlexSalesBookStore JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainFlexSalesBookStore JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void testMainFlexSalesBookStore() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainFlexSalesBookStore.main(new String[]{});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: MainFlexSalesBookStore.main():",
						 normalize("20.0 % [ George Orwell , Isaac Asimov ] [ ( George Orwell ; 1984 ; 6.2 ; 20.0 % ; 4.96 ; 10.0 % ; 5.4559999999999995 ) , ( Philip K . Dick ; ? Suenan los androides con ovejas electricas ? ; 3.5 ; 10.0 % ; 3.85 ) , ( Isaac Asimov ; Fundacion e Imperio ; 9.4 ; 20.0 % ; 7.5200000000000005 ; 10.0 % ; 8.272 ) , ( Ray Bradbury ; Fahrenheit 451 ; 7.4 ; 10.0 % ; 8.14 ) , ( Aldous Huxley ; Un Mundo Feliz ; 6.5 ; 10.0 % ; 7.15 ) , ( Isaac Asimov ; La Fundacion ; 7.3 ; 20.0 % ; 5.84 ; 10.0 % ; 6.4239999999999995 ) , ( William Gibson ; Neuromante ; 8.3 ; 10.0 % ; 9.13 ) , ( Isaac Asimov ; Segunda Fundacion ; 8.1 ; 20.0 % ; 6.4799999999999995 ; 10.0 % ; 7.127999999999999 ) , ( Isaac Newton ; Arithmetica Universalis ; 10.5 ; 10.0 % ; 11.55 ) ] 20.0 % [ George Orwell , Isaac Asimov ] [ ( William Gibson ; Neuromante ; 8.3 ; 10.0 % ; 9.13 ) , ( Philip K . Dick ; ? Suenan los androides con ovejas electricas ? ; 3.5 ; 10.0 % ; 3.85 ) , ( Isaac Asimov ; Fundacion e Imperio ; 9.4 ; 20.0 % ; 7.5200000000000005 ; 10.0 % ; 8.272 ) , ( Ray Bradbury ; Fahrenheit 451 ; 7.4 ; 10.0 % ; 8.14 ) , ( Isaac Asimov ; Segunda Fundacion ; 8.1 ; 20.0 % ; 6.4799999999999995 ; 10.0 % ; 7.127999999999999 ) , ( Isaac Asimov ; La Fundacion ; 7.3 ; 20.0 % ; 5.84 ; 10.0 % ; 6.4239999999999995 ) ] getFinalPrice ( George Orwell , 1984 ) : 0.0 getFinalPrice ( Philip K . Dick , ? Suenan los androides con ovejas electricas ? ) : 3.85 getFinalPrice ( isaac asimov , fundacion e imperio ) : 8.272 getFinalPrice ( Ray Bradbury , Fahrenheit 451 ) : 8.14 getFinalPrice ( Aldous Huxley , Un Mundo Feliz ) : 0.0 getFinalPrice ( Isaac Asimov , La Fundacion ) : 6.4239999999999995 getFinalPrice ( william gibson , neuromante ) : 9.13 getFinalPrice ( Isaac Asimov , Segunda Fundacion ) : 7.127999999999999 getFinalPrice ( Isaac Newton , Arithmetica Universalis ) : 0.0"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@RunWith(Suite.class)
	@Suite.SuiteClasses({ JUnitTestBook.class ,
				JUnitTestBookOnSale.class ,
				JUnitTestBookStore.class ,
				JUnitTestDiscountAuthor.class ,
				JUnitTestDiscountPrice.class ,
				JUnitTestFlexSalesBookStore.class ,
				JUnitTestMainFlexSalesBookStore.class 
				})
				public static class JUnitTestSuite { /*empty*/ }
	//----------------------------------------------------------------------
	//--TestRunner-----------------------------------------------------
	//----------------------------------------------------------------------
	public static Result result = null;
	public static void main(String[] args) {
		result = JUnitCore.runClasses(JUnitTestSuite.class);
		int rc = result.getRunCount();
		int fc = result.getFailureCount();
		int ac = 0;//result.getAssumptionFailureCount();
		int ic = result.getIgnoreCount();
		if (fc > 0) {
			System.out.println(">>> ------");
		}
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if ((ac > 0)||(fc > 0)) {
			System.out.println(">>> ------");
			if (ac > 0) {
				System.out.println(">>> Error: "+ac+" tests could not be executed due to previous errors");
			}
			if (fc > 0) {
				System.out.println(">>> Error: "+fc+" tests failed");
			}
		}
		if (result.wasSuccessful()) {
			System.out.print(">>> JUnit Test Succeeded");
		} else {
			System.out.print(">>> Error: JUnit Test Failed");
		}
		System.out.println(" ("+rc+", "+fc+", "+ac+", "+ic+")");
	}
	//----------------------------------------------------------------------
	//-- Utils -------------------------------------------------------------
	//----------------------------------------------------------------------
	private static char normalizeUnicode(char ch) {
		switch (ch) {
		case '\n':
		case '\r':
		case '\t':
		case '\f':
			ch = ' ';
			break;
		case '\u20AC':
			ch = 'E';
			break;
		case '\u00A1':
			ch = '!';
			break;
		case '\u00AA':
			ch = 'a';
			break;
		case '\u00BA':
			ch = 'o';
			break;
		case '\u00BF':
			ch = '?';
			break;
		case '\u00C0':
		case '\u00C1':
		case '\u00C2':
		case '\u00C3':
		case '\u00C4':
		case '\u00C5':
		case '\u00C6':
			ch = 'A';
			break;
		case '\u00C7':
			ch = 'C';
			break;
		case '\u00C8':
		case '\u00C9':
		case '\u00CA':
		case '\u00CB':
			ch = 'E';
			break;
		case '\u00CC':
		case '\u00CD':
		case '\u00CE':
		case '\u00CF':
			ch = 'I';
			break;
		case '\u00D0':
			ch = 'D';
			break;
		case '\u00D1':
			ch = 'N';
			break;
		case '\u00D2':
		case '\u00D3':
		case '\u00D4':
		case '\u00D5':
		case '\u00D6':
			ch = 'O';
			break;
		case '\u00D9':
		case '\u00DA':
		case '\u00DB':
		case '\u00DC':
			ch = 'U';
			break;
		case '\u00DD':
			ch = 'Y';
			break;
		case '\u00E0':
		case '\u00E1':
		case '\u00E2':
		case '\u00E3':
		case '\u00E4':
		case '\u00E5':
		case '\u00E6':
			ch = 'a';
			break;
		case '\u00E7':
			ch = 'c';
			break;
		case '\u00E8':
		case '\u00E9':
		case '\u00EA':
		case '\u00EB':
			ch = 'e';
			break;
		case '\u00EC':
		case '\u00ED':
		case '\u00EE':
		case '\u00EF':
			ch = 'i';
			break;
		case '\u00F0':
			ch = 'd';
			break;
		case '\u00F1':
			ch = 'n';
			break;
		case '\u00F2':
		case '\u00F3':
		case '\u00F4':
		case '\u00F5':
		case '\u00F6':
			ch = 'o';
			break;
		case '\u00F9':
		case '\u00FA':
		case '\u00FB':
		case '\u00FC':
			ch = 'u';
			break;
		case '\u00FD':
		case '\u00FF':
			ch = 'y';
			break;
		}
		return ch;
	}
	//----------------------------------------------------------------------
	private static String normalize(String s1) {
		int sz = s1 == null ? 16 : s1.length() == 0 ? 16 : 2*s1.length();
		StringBuilder sb = new StringBuilder(sz);
		sb.append(' ');
		if (s1 != null) {
			for (int i = 0; i < s1.length(); ++i) {
				char ch = normalizeUnicode(s1.charAt(i));
				char sbLastChar = sb.charAt(sb.length()-1);
				if (Character.isLetter(ch)) {
					if ( ! Character.isLetter(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isDigit(ch)) {
					if ((i >= 2)
						&& (s1.charAt(i-1) == '.')
						&& Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 ."
						sb.append('.');
					} else if ((i >= 2)
							   && ((s1.charAt(i-1) == 'e')||(s1.charAt(i-1) == 'E'))
							   && Character.isDigit(s1.charAt(i-2))) {
						sb.setLength(sb.length()-2); // "9 e"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '+')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e +"
						sb.append('e');
					} else if ((i >= 3)
							   && (s1.charAt(i-1) == '-')
							   && ((s1.charAt(i-2) == 'e')||(s1.charAt(i-2) == 'E'))
							   && Character.isDigit(s1.charAt(i-3))) {
						sb.setLength(sb.length()-4); // "9 e -"
						sb.append("e-");
					} else if ( (sbLastChar != '-') && ! Character.isDigit(sbLastChar)) {
						if ( ! Character.isSpaceChar(sbLastChar)) {
							sb.append(' ');
						}
					}
					sb.append(ch);
				} else if (Character.isSpaceChar(ch)) {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
				} else {
					if ( ! Character.isSpaceChar(sbLastChar)) {
						sb.append(' ');
					}
					sb.append(ch);
				}
			}
		}
		if (Character.isSpaceChar(sb.charAt(sb.length()-1))) {
			sb.setLength(sb.length()-1);
		}
		if ((sb.length() > 0) && Character.isSpaceChar(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}
	//----------------------------------------------------------------------
	private static final String precondMessage = "\n> Warning: the test could not be executed due to previous errors";
	//----------------------------------------------------------------------
	private static void precond(boolean expectedValue, boolean currValue) {
		assumeTrue(precondMessage, expectedValue == currValue);
	}
	private static void precond(char expectedValue, char currValue) {
		assumeTrue(precondMessage, expectedValue == currValue);
	}
	private static void precond(short expectedValue, short currValue) {
		assumeTrue(precondMessage, expectedValue == currValue);
	}
	private static void precond(int expectedValue, int currValue) {
		assumeTrue(precondMessage, expectedValue == currValue);
	}
	private static void precond(long expectedValue, long currValue) {
		assumeTrue(precondMessage, expectedValue == currValue);
	}
	private static void precond(float expectedValue, float currValue, float delta) {
		assumeTrue(precondMessage, Math.abs(expectedValue - currValue) <= delta);
	}
	private static void precond(double expectedValue, double currValue, double delta) {
		assumeTrue(precondMessage, Math.abs(expectedValue - currValue) <= delta);
	}
	private static void precond(Object expectedValue, Object currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(precondMessage, expectedValue == currValue);
		} else {
			assumeTrue(precondMessage, expectedValue.equals(currValue));
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void assertEqualsNorm(String msg, String expectedValue, String currValue) {
		assertEquals(msg, normalize(expectedValue), normalize(currValue));
	}
	private static void assertArrayEqualsNorm(String msg, String[] expectedValue, String[] currValue) {
		assertEquals(msg, expectedValue.length, currValue.length);
		for (int i = 0; i < expectedValue.length; ++i) {
			assertEquals(msg, normalize(expectedValue[i]), normalize(currValue[i]));
		}
	}
	//----------------------------------------------------------------------
	private static void deleteFile(String filename) {
		new java.io.File(filename).delete();
	}
	private static void createFile(String filename, String inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			pw.println(inputData);
		}
	}
	private static void createFile(String filename, String[] inputData) throws Exception {
		try (java.io.PrintWriter pw = new java.io.PrintWriter(filename)) {
			for (String linea : inputData) {
				pw.println(linea);
			}
		}
	}
	private static String loadFile(String filename) throws Exception {
		java.util.StringJoiner sj = new java.util.StringJoiner("\n");
		try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(filename))) {
			while (sc.hasNextLine()) {
				sj.add(sc.nextLine());
			}
		}
		return sj.toString();
	}
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	private static class SysOutCapture {
		private SysOutErrCapture systemout;
		private SysOutErrCapture systemerr;
		public SysOutCapture() {
			systemout = new SysOutErrCapture(false);
			systemerr = new SysOutErrCapture(true);
		}
		public void sysOutCapture() throws RuntimeException {
			try {
				systemout.sysOutCapture();
			} finally {
				systemerr.sysOutCapture();
			}
		}
		public String sysOutRelease() throws RuntimeException {
			String s1 = "";
			String s2 = "";
			try {
				s1 = systemout.sysOutRelease();
			} finally {
				s2 = systemerr.sysOutRelease();
			}
			return s1 + " " + s2 ;
		}
		//--------------------------
		private static class SysOutErrCapture {
			//--------------------------------
			private java.io.PrintStream sysoutOld;
			private java.io.PrintStream sysoutstr;
			private java.io.ByteArrayOutputStream baos;
			private final boolean systemErr;
			private int estado;
			//--------------------------------
			public SysOutErrCapture(boolean syserr) {
				sysoutstr = null ;
				baos = null;
				sysoutOld = null ;
				estado = 0;
				systemErr = syserr;
			}
			//--------------------------------
			public void sysOutCapture() throws RuntimeException {
				if (estado != 0) {
					throw new RuntimeException("sysOutCapture:BadState");
				} else {
					estado = 1;
					try {
						if (systemErr) {
							sysoutOld = System.err;
						} else {
							sysoutOld = System.out;
						}
						baos = new java.io.ByteArrayOutputStream();
						sysoutstr = new java.io.PrintStream(baos);
						if (systemErr) {
							System.setErr(sysoutstr);
						} else {
							System.setOut(sysoutstr);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutCapture:InternalError");
					}
				}
			}
			//--------------------------------
			public String sysOutRelease() throws RuntimeException {
				String result = "";
				if (estado != 1) {
					throw new RuntimeException("sysOutRelease:BadState");
				} else {
					estado = 0;
					try {
						if (sysoutstr != null) {
							sysoutstr.flush();
						}
						if (baos != null) {
							baos.flush();
							result = new String(baos.toByteArray()); //java.nio.charset.StandardCharsets.ISO_8859_1);
						}
					} catch (Throwable e) {
						throw new RuntimeException("sysOutRelease:InternalError1");
					} finally {
						try {
							if (systemErr) {
								System.setErr(sysoutOld);
							} else {
								System.setOut(sysoutOld);
							}
							if (sysoutstr != null) {
								sysoutstr.close();
								sysoutstr = null;
							}
							if (baos != null) {
								baos.close();
								baos = null;
							}
						} catch (Throwable e) {
							throw new RuntimeException("sysOutRelease:InternalError2");
						}
					}
				}
				return result;
			}
			//--------------------------------
		}
	}
	//----------------------------------------------------------------------
}
//--------------------------------------------------------------------------
