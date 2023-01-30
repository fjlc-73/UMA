
//--------------------------------------------------------------------------
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import prWordIndex.*;

//--------------------------------------------------------------------------

public class TestRunnerPr52 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestCounterIndex {
		private static final String[] inputData = {
			"Guerra tenia una jarra y Parra tenia una perra, pero la perra de Parra rompio la jarra de Guerra.",
			"Guerra pego con la porra a la perra de Parra. !Oiga usted buen hombre de Parra! Por que ha pegado con la porra a la perra de Parra.",
			"Porque si la perra de Parra no hubiera roto la jarra de Guerra, Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private CounterIndex cp1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of CounterIndex JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of CounterIndex JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			cp1 = new CounterIndex();
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void counterIndexCtorTest1() throws Exception {
			try {
				assertTrue("\n> Error: CounterIndex implements Index:", ((Object)cp1 instanceof Index));
				assertTrue("\n> Error: CounterIndex extends AbstractIndex:", ((Object)cp1 instanceof AbstractIndex));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize(""),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void counterIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize("a 3 buen 1 con 3 de 8 guerra 5 ha 1 hombre 1 hubiera 2 jarra 3 la 10 no 2 oiga 1 parra 7 pegado 2 pego 1 pero 1 perra 6 por 1 porque 1 porra 3 que 1 rompio 1 roto 1 si 1 tenia 2 una 2 usted 1 y 1"),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void counterIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndex();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: presentIndex():",
						 normalize("a 3 buen 1 con 3 de 8 guerra 5 ha 1 hombre 1 hubiera 2 jarra 3 la 10 no 2 oiga 1 parra 7 pegado 2 pego 1 pero 1 perra 6 por 1 porque 1 porra 3 que 1 rompio 1 roto 1 si 1 tenia 2 una 2 usted 1 y 1"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestLineIndex {
		private static final String[] inputData = {
			"Guerra tenia una jarra y Parra tenia una perra, pero la perra de Parra rompio la jarra de Guerra.",
			"Guerra pego con la porra a la perra de Parra. !Oiga usted buen hombre de Parra! Por que ha pegado con la porra a la perra de Parra.",
			"Porque si la perra de Parra no hubiera roto la jarra de Guerra, Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private LineIndex cp1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of LineIndex JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of LineIndex JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			cp1 = new LineIndex();
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void lineIndexCtorTest1() throws Exception {
			try {
				assertTrue("\n> Error: LineIndex implements Index:", ((Object)cp1 instanceof Index));
				assertTrue("\n> Error: LineIndex extends AbstractIndex:", ((Object)cp1 instanceof AbstractIndex));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize(""),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void lineIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize("a <2,3> buen <2> con <2,3> de <1,2,3> guerra <1,2,3> ha <2> hombre <2> hubiera <3> jarra <1,3> la <1,2,3> no <3> oiga <2> parra <1,2,3> pegado <2,3> pego <2> pero <1> perra <1,2,3> por <2> porque <3> porra <2,3> que <2> rompio <1> roto <3> si <3> tenia <1> una <1> usted <2> y <1>"),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void lineIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndex();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: presentIndex():",
						 normalize("a <2,3> buen <2> con <2,3> de <1,2,3> guerra <1,2,3> ha <2> hombre <2> hubiera <3> jarra <1,3> la <1,2,3> no <3> oiga <2> parra <1,2,3> pegado <2,3> pego <2> pero <1> perra <1,2,3> por <2> porque <3> porra <2,3> que <2> rompio <1> roto <3> si <3> tenia <1> una <1> usted <2> y <1>"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestPositionInLineIndex {
		private static final String[] inputData = {
			"Guerra tenia una jarra y Parra tenia una perra, pero la perra de Parra rompio la jarra de Guerra.",
			"Guerra pego con la porra a la perra de Parra. !Oiga usted buen hombre de Parra! Por que ha pegado con la porra a la perra de Parra.",
			"Porque si la perra de Parra no hubiera roto la jarra de Guerra, Guerra no hubiera pegado con la porra a la perra de Parra."
		};
		private static final String delimiters = "[ .,:;\\-\\!\\?]+";
		private PositionInLineIndex cp1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of PositionInLineIndex JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of PositionInLineIndex JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			cp1 = new PositionInLineIndex();
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void positionInLineIndexCtorTest1() throws Exception {
			try {
				assertTrue("\n> Error: PositionInLineIndex implements Index:", ((Object)cp1 instanceof Index));
				assertTrue("\n> Error: PositionInLineIndex extends AbstractIndex:", ((Object)cp1 instanceof AbstractIndex));
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize(""),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void positionInLineIndexPresentIndexPWTest1() throws Exception {
			try {
				for (String x : inputData) {
					cp1.addSentence(x);
				}
				cp1.solve(delimiters);
				try (java.io.PrintWriter pw = new java.io.PrintWriter("outputData.txt")) {
					cp1.presentIndex(pw);
				}
				assertEquals("\n> Error: presentIndex():",
							 normalize("a 2 <6,24> 3 <21> buen 2 <13> con 2 <3,21> 3 <18> de 1 <13,18> 2 <9,15,27> 3 <5,12,24> guerra 1 <1,19> 2 <1> 3 <13,14> ha 2 <19> hombre 2 <14> hubiera 3 <8,16> jarra 1 <4,17> 3 <11> la 1 <11,16> 2 <4,7,22,25> 3 <3,10,19,22> no 3 <7,15> oiga 2 <11> parra 1 <6,14> 2 <10,16,28> 3 <6,25> pegado 2 <20> 3 <17> pego 2 <2> pero 1 <10> perra 1 <9,12> 2 <8,26> 3 <4,23> por 2 <17> porque 3 <1> porra 2 <5,23> 3 <20> que 2 <18> rompio 1 <15> roto 3 <9> si 3 <2> tenia 1 <2,7> una 1 <3,8> usted 2 <12> y 1 <5>"),
							 normalize(loadFile("outputData.txt")));
			} finally {
				deleteFile("outputData.txt");
			}
		}
		@Test(timeout = 1000)
		public void positionInLineIndexPresentIndexCsTest1() {
			for (String x : inputData) {
				cp1.addSentence(x);
			}
			cp1.solve(delimiters);
			SysOutCapture sysOutCapture = new SysOutCapture();
			String salida = "";
			try {
				sysOutCapture.sysOutCapture();
				cp1.presentIndex();
			} finally {
				salida = sysOutCapture.sysOutRelease();
			}
			assertEquals("\n> Error: presentIndex():",
						 normalize("a 2 <6,24> 3 <21> buen 2 <13> con 2 <3,21> 3 <18> de 1 <13,18> 2 <9,15,27> 3 <5,12,24> guerra 1 <1,19> 2 <1> 3 <13,14> ha 2 <19> hombre 2 <14> hubiera 3 <8,16> jarra 1 <4,17> 3 <11> la 1 <11,16> 2 <4,7,22,25> 3 <3,10,19,22> no 3 <7,15> oiga 2 <11> parra 1 <6,14> 2 <10,16,28> 3 <6,25> pegado 2 <20> 3 <17> pego 2 <2> pero 1 <10> perra 1 <9,12> 2 <8,26> 3 <4,23> por 2 <17> porque 3 <1> porra 2 <5,23> 3 <20> que 2 <18> rompio 1 <15> roto 3 <9> si 3 <2> tenia 1 <2,7> una 1 <3,8> usted 2 <12> y 1 <5>"),
						 normalize(salida));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@RunWith(Suite.class)
	@Suite.SuiteClasses({ JUnitTestCounterIndex.class ,
				JUnitTestLineIndex.class ,
				JUnitTestPositionInLineIndex.class
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
				System.out.println(">>> Error: "+ac+" tests where not executed due to errors in other methods");
			}
			if (fc > 0) {
				System.out.println(">>> Error: "+fc+" tests tests failed due to errors in methods");
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
