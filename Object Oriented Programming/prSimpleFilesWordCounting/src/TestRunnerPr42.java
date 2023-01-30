
//--------------------------------------------------------------------------
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import prSimpleFilesWordCounting.*;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

//--------------------------------------------------------------------------

public class TestRunnerPr42 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestWordInText {
		private WordInText an1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of WordInText JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of WordInText JUnit Test");
		}
		@Before
		public void setUp() throws Exception {
			// Code executed before each test
			an1 = new WordInText("Word");
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void WordInTextCtorTest1() {
			assertEquals("\n> Error: an1.toString():",
						 normalize("WORD: 1"),
						 normalize(an1.toString()));
		}
		@Test(timeout = 1000)
		public void WordInTextIncrementaTest2() throws Exception {
			an1.increment();
			assertEquals("\n> Error: an1.incrementa(); an1.toString():",
						 normalize("WORD: 2"),
						 normalize(an1.toString()));
		}
		@Test(timeout = 1000)
		public void WordInTextEqualsTest1() throws Exception {
			WordInText an2 = new WordInText("Word");
			assertTrue("\n> Error: an1.equals(an2): ", an1.equals(an2));
			an2.increment();
			assertTrue("\n> Error: an1.equals(an2): ", an1.equals(an2));
			//------------------------
			WordInText an3 = new WordInText("Word");
			assertTrue("\n> Error: an1.equals(an3): ", an1.equals(an3));
			an3.increment();
			assertTrue("\n> Error: an1.equals(an3): ", an1.equals(an3));
			//------------------------
			WordInText an4 = new WordInText("another word");
			assertFalse("\n> Error: an1.equals(an4): ", an1.equals(an4));
			//------------------------
			assertFalse("\n> Error: an1.equals(null): ", an1.equals(null));
			assertFalse("\n> Error: an1.equals(\"This is a String\"): ", an1.equals("This is a String"));
		}
		@Test(timeout = 1000)
		public void WordInTextHashCodeTest1() throws Exception {
			int an1HashCode = an1.hashCode();
			//------------------------
			WordInText an2 = new WordInText("Word");
			assertEquals("\n> Error: an2.hashCode(): ", an1HashCode, an2.hashCode());
			an2.increment();
			assertEquals("\n> Error: an2.hashCode(): ", an1HashCode, an2.hashCode());
			//------------------------
			WordInText an3 = new WordInText("Word");
			assertEquals("\n> Error: an3.hashCode(): ", an1HashCode, an3.hashCode());
			an3.increment();
			assertEquals("\n> Error: an3.hashCode(): ", an1HashCode, an3.hashCode());
			//------------------------
			WordInText an4 = new WordInText("another word");
			assertNotEquals("\n> Error: an4.hashCode(): ", an1HashCode, an4.hashCode());
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestMainWordInText {
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainWordInText JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainWordInText JUnit Test");
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
		public void MainWordInTextMainTest1() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainWordInText.main(new String[]{});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: MainWordInText.main():",
						 normalize("Word 1 = GORRA : 2 Word 2 = GORRA : 1 The words are equal"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestWordCounter {
		private static final String[] inputData = {
			"Guerra tenia una jarra y Parra tenia una perra, ",
			"pero la perra de Parra rompio la jarra de Guerra.",
			"Guerra pego con la porra a la perra de Parra. ",
			"!Oiga usted buen hombre de Parra! ",
			"Por que ha pegado con la porra a la perra de Parra.",
			"Porque si la perra de Parra no hubiera roto la jarra de Guerra,",
			"Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private WordCounter cp1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of WordCounter JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of WordCounter JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			cp1 = new WordCounter(2);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void WordCounterCtorTest1() {
			assertEquals("\n> Error: cp1.toString():",
						 normalize("[]"),
						 normalize(cp1.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterCtorTest2() {
			WordCounter cp2 = new WordCounter();
			assertEquals("\n> Error: cp1.toString():",
						 normalize("[]"),
						 normalize(cp2.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterincludeAllTest1() {
			cp1.includeAll(inputData, delimiters);
			assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
						 normalize(cp1.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterincludeAllFromFileTest1() throws Exception {
			try {
				createFile("inputData.txt", inputData);
				cp1.includeAllFromFile("inputData.txt", delimiters);
				assertEquals("\n> Error: includeAllFromFile() ; toString():",
							 normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
							 normalize(cp1.toString()));
			} finally {
				deleteFile("inputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterEncuentraTest1() {
			cp1.includeAll(inputData, delimiters);
			precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
					normalize(cp1.toString()));
			assertEquals("\n> Error: cp1.find(guerra):", new WordInText("GUERRA"), cp1.find("guerra"));
			assertEquals("\n> Error: cp1.find(jarra):", new WordInText("JARRA"), cp1.find("jarra"));
			assertEquals("\n> Error: cp1.find(roto):", new WordInText("ROTO"), cp1.find("roto"));
		}
		@Test(timeout = 1000)
		public void WordCounterEncuentraTest2() {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				WordInText pal = cp1.find("xxx");
				fail("\n> Error: find(xxx): No exception thrown");
			} catch (java.util.NoSuchElementException e) {
				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
			} catch (Exception e) {
				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterEncuentraTest3() {
			try {
				WordInText pal = cp1.find("xxx");
				fail("\n> Error: find(xxx): No exception thrown");
			} catch (java.util.NoSuchElementException e) {
				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
			} catch (Exception e) {
				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterPresentWordsPWTest1() throws Exception {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentWords(pw);
				}
				assertEquals("\n> Error: presentWords():",
							 normalize("GUERRA: 5 TENIA: 2 UNA: 2 JARRA: 3 Y: 1 PARRA: 7 PERRA: 6 PERO: 1 LA: 10 DE: 8 ROMPIO: 1 PEGO: 1 CON: 3 PORRA: 3 A: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 SI: 1 NO: 2 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")));
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterPresentWordsFichTest1() throws Exception {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, UNA: 2, JARRA: 3, Y: 1, PARRA: 7, PERRA: 6, PERO: 1, LA: 10, DE: 8, ROMPIO: 1, PEGO: 1, CON: 3, PORRA: 3, A: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, SI: 1, NO: 2, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				cp1.presentWords("outputData.txt");
				assertEquals("\n> Error: presentWords():",
							 normalize("GUERRA: 5 TENIA: 2 UNA: 2 JARRA: 3 Y: 1 PARRA: 7 PERRA: 6 PERO: 1 LA: 10 DE: 8 ROMPIO: 1 PEGO: 1 CON: 3 PORRA: 3 A: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 SI: 1 NO: 2 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")));
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestMainWordCounter {
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of MainWordCounter JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of MainWordCounter JUnit Test");
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
		public void MainWordCounterMainTest1() {
			String salida = "";
			SysOutCapture sysOutCapture = new SysOutCapture();
			try {
				sysOutCapture.sysOutCapture();
				MainWordCounter.main(new String[]{});
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: MainWordCounter.main():",
						 normalize("[ ESTA : 2 , ES : 2 , LA : 2 , PRIMERA : 1 , FRASE : 2 , DEL : 1 , EJEMPLO : 1 , Y : 1 , SEGUNDA : 1 ]"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestWordCounterSig {
		private static final String[] inputData = {
			"Guerra tenia una jarra y Parra tenia una perra, ",
			"pero la perra de Parra rompio la jarra de Guerra.",
			"Guerra pego con la porra a la perra de Parra. ",
			"!Oiga usted buen hombre de Parra! ",
			"Por que ha pegado con la porra a la perra de Parra.",
			"Porque si la perra de Parra no hubiera roto la jarra de Guerra,",
			"Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private static final String[] noSig = {
			"Con", "La", "A", "De", "NO", "SI", "y", "una"
		};
		private static final String[] noSigInputData = {
			"Con La A De NO SI y una"
		};
		private WordCounterSig cp1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of WordCounterSig JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of WordCounterSig JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			cp1 = new WordCounterSig(2, noSig);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void WordCounterSigCtorTest1() {
			assertTrue("\n> Error: WordCounterSig extends WordCounter:", ((Object)cp1 instanceof WordCounter));
			assertEquals("\n> Error: cp1.toString():",
						 normalize("[]"),
						 normalize(cp1.toString()));
			cp1.includeAll(inputData, delimiters);
			assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						 normalize(cp1.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterSigCtorTest2() {
			WordCounterSig cp2 = new WordCounterSig(noSig);
			assertEquals("\n> Error: cp2.toString():",
						 normalize("[]"),
						 normalize(cp2.toString()));
			cp2.includeAll(inputData, delimiters);
			assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						 normalize(cp2.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterSigCtorTest3() throws Exception {
			try {
				createFile("noSigInputData.txt", noSigInputData);
				WordCounterSig cp2 = new WordCounterSig("noSigInputData.txt", delimiters);
				assertEquals("\n> Error: cp2.toString():",
							 normalize("[]"),
							 normalize(cp2.toString()));
				cp2.includeAll(inputData, delimiters);
				assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
							 normalize(cp2.toString()));
			} finally {
				deleteFile("noSigInputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigCtorTest4() throws Exception {
			try {
				createFile("noSigInputData.txt", noSigInputData);
				WordCounterSig cp2 = new WordCounterSig(2, "noSigInputData.txt", delimiters);
				assertEquals("\n> Error: cp2.toString():",
							 normalize("[]"),
							 normalize(cp2.toString()));
				cp2.includeAll(inputData, delimiters);
				assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
							 normalize(cp2.toString()));
			} finally {
				deleteFile("noSigInputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigincludeAllTest1() {
			cp1.includeAll(inputData, delimiters);
			assertEquals("\n> Error: includeAll() ; toString():",
						 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						 normalize(cp1.toString()));
		}
		@Test(timeout = 1000)
		public void WordCounterSigincludeAllFromFileTest1() throws Exception {
			try {
				createFile("inputData.txt", inputData);
				cp1.includeAllFromFile("inputData.txt", delimiters);
				assertEquals("\n> Error: includeAllFromFile() ; toString():",
							 normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
							 normalize(cp1.toString()));
			} finally {
				deleteFile("inputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigEncuentraTest1() {
			cp1.includeAll(inputData, delimiters);
			precond(normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
					normalize(cp1.toString()));
			assertEquals("\n> Error: cp1.find(guerra):", new WordInText("GUERRA"), cp1.find("guerra"));
			assertEquals("\n> Error: cp1.find(jarra):", new WordInText("JARRA"), cp1.find("jarra"));
			assertEquals("\n> Error: cp1.find(roto):", new WordInText("ROTO"), cp1.find("roto"));
		}
		@Test(timeout = 1000)
		public void WordCounterSigEncuentraTest2() {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				WordInText pal = cp1.find("xxx");
				fail("\n> Error: find(xxx): No exception thrown");
			} catch (java.util.NoSuchElementException e) {
				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
			} catch (Exception e) {
				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigEncuentraTest3() {
			try {
				WordInText pal = cp1.find("xxx");
				fail("\n> Error: find(xxx): No exception thrown");
			} catch (java.util.NoSuchElementException e) {
				//assertEquals("\n> Error: find(xxx): exception.getMessage():", "Not found word xxx", e.getMessage());
			} catch (Exception e) {
				fail("\n> Error: find(xxx): the exception thrown was not NoSuchElementException");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigPresentWordsPWTest1() throws Exception {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentWords(pw);
				}
				assertEquals("\n> Error: presentWords():",
							 normalize("GUERRA: 5 TENIA: 2 JARRA: 3 PARRA: 7 PERRA: 6 PERO: 1 ROMPIO: 1 PEGO: 1 PORRA: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")));
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void WordCounterSigPresentWordsFichTest1() throws Exception {
			try {
				cp1.includeAll(inputData, delimiters);
				precond(normalize("[GUERRA: 5, TENIA: 2, JARRA: 3, PARRA: 7, PERRA: 6, PERO: 1, ROMPIO: 1, PEGO: 1, PORRA: 3, OIGA: 1, USTED: 1, BUEN: 1, HOMBRE: 1, POR: 1, QUE: 1, HA: 1, PEGADO: 2, PORQUE: 1, HUBIERA: 2, ROTO: 1]"),
						normalize(cp1.toString()));
				cp1.presentWords("outputData.txt");
				assertEquals("\n> Error: presentWords():",
							 normalize("GUERRA: 5 TENIA: 2 JARRA: 3 PARRA: 7 PERRA: 6 PERO: 1 ROMPIO: 1 PEGO: 1 PORRA: 3 OIGA: 1 USTED: 1 BUEN: 1 HOMBRE: 1 POR: 1 QUE: 1 HA: 1 PEGADO: 2 PORQUE: 1 HUBIERA: 2 ROTO: 1"),
							 normalize(loadFile("outputData.txt")));
				
			} finally {
				deleteFile("outputData.txt");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@RunWith(Suite.class)
	@Suite.SuiteClasses({ JUnitTestWordInText.class ,
				JUnitTestMainWordInText.class , 
				JUnitTestWordCounter.class ,
				JUnitTestMainWordCounter.class ,
				JUnitTestWordCounterSig.class
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
				System.out.println(">>> Error: No se pudieron realizar "+ac+" tests debido a errores en otros metodos");
			}
			if (fc > 0) {
				System.out.println(">>> Error: Fallaron "+fc+" tests debido a errores en metodos");
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
	private static final String precondMessage = "\n> Aviso: No se pudo realizar el test debido a errores en otros metodos";
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
