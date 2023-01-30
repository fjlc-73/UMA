
//--------------------------------------------------------------------------
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import prGrading.*;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

//--------------------------------------------------------------------------

public class TestRunnerPr41Ej2 {
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestStudentException {
		private StudentException ae1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of StudentException JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of StudentException JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			ae1 = new StudentException("Mensaje de Error 1");
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void alumnoExceptionCtorTest1() {
			assertTrue("\n> Error: StudentException extends Exception:", ((Object)ae1 instanceof Exception));
			assertFalse("\n> Error: StudentException extends RuntimeException:", ((Object)ae1 instanceof RuntimeException));
			assertEquals("\n> Error: new StudentException(\"Mensaje de Error 1\"): getMessage():", "Mensaje de Error 1", ae1.getMessage());
		}
		@Test(timeout = 1000)
		public void alumnoExceptionCtorTest2() {
			StudentException ae2 = new StudentException();
			assertTrue("\n> Error: StudentException extends Exception:", ((Object)ae2 instanceof Exception));
			assertFalse("\n> Error: StudentException extends RuntimeException:", ((Object)ae2 instanceof RuntimeException));
			assertEquals("\n> Error: new StudentException(): getMessage():", null, ae2.getMessage());
		}
		@Test(timeout = 1000)
		public void alumnoExceptionToStringTest1() {
			precond("Mensaje de Error 1", ae1.getMessage());
			assertEquals("\n> Error: ae1.toString():",
						 normalize("prGrading.StudentException : Mensaje de Error 1"),
						 normalize(ae1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestStudent {
		private Student an1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Student JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of Student JUnit Test");
		}
		@Before
		public void setUp() throws Exception {
			// Code executed before each test
			an1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void alumnoCtorTest1() {
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", 5.5): DNI:", "22456784F", an1.getDni());
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", 5.5): Name:", "Gonzalez Perez, Juan", an1.getName());
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", 5.5): Calificacion:", 5.5, an1.getGrade(), 1e-6);
		}
		@Test(timeout = 1000)
		public void alumnoCtorTest2() throws Exception {
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan");
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): DNI:", "22456784F", an2.getDni());
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): Name:", "Gonzalez Perez, Juan", an2.getName());
			assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\"): Calificacion:", 0.0, an2.getGrade(), 1e-6);
		}
		@Test(timeout = 1000)
		public void alumnoCtorTestX1() {
			try {
				Student an2 = new Student("22456784F", "Gonzalez Perez, Juan", -1.0);
				fail("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", -1.0): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", -1.0): exception.getMessage():", normalize("Negative grade"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: new Student(\"22456784F\", \"Gonzalez Perez, Juan\", -1.0): the exception thrown is not StudentException");
			}
		}
		@Test(timeout = 1000)
		public void alumnoEqualsTest1() throws Exception {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			//------------------------
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan", 7.7);
			precond("22456784F", an2.getDni());
			precond("Gonzalez Perez, Juan", an2.getName());
			precond(7.7, an2.getGrade(), 1e-6);
			assertTrue("\n> Error: an1.equals(an2): ", an1.equals(an2));
			//------------------------
			Student an3 = new Student("22456784f", "Gonzalez Perez, Juan", 7.7);
			precond("22456784f", an3.getDni());
			precond("Gonzalez Perez, Juan", an3.getName());
			precond(7.7, an3.getGrade(), 1e-6);
			assertTrue("\n> Error: an1.equals(an3): ", an1.equals(an3));
			//------------------------
			Student an4 = new Student("22456784F", "gonzalez perez, juan", 5.5);
			precond("22456784F", an4.getDni());
			precond("gonzalez perez, juan", an4.getName());
			precond(5.5, an4.getGrade(), 1e-6);
			assertFalse("\n> Error: an1.equals(an4): ", an1.equals(an4));
			//------------------------
			Student an5 = new Student("11111111A", "Gonzalez Perez, Juan", 5.5);
			precond("11111111A", an5.getDni());
			precond("Gonzalez Perez, Juan", an5.getName());
			precond(5.5, an5.getGrade(), 1e-6);
			assertFalse("\n> Error: an1.equals(an5): ", an1.equals(an5));
			//------------------------
			assertFalse("\n> Error: an1.equals(null): ", an1.equals(null));
			assertFalse("\n> Error: an1.equals(\"Esto es un String\"): ", an1.equals("Esto es un String"));
		}
		@Test(timeout = 1000)
		public void alumnoHashCodeTest1() throws Exception {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			int an1HashCode = an1.hashCode();
			//------------------------
			Student an2 = new Student("22456784F", "Gonzalez Perez, Juan", 7.7);
			precond("22456784F", an2.getDni());
			precond("Gonzalez Perez, Juan", an2.getName());
			precond(7.7, an2.getGrade(), 1e-6);
			assertEquals("\n> Error: an2.hashCode(): ", an1HashCode, an2.hashCode());
			//------------------------
			Student an3 = new Student("22456784f", "Gonzalez Perez, Juan", 7.7);
			precond("22456784f", an3.getDni());
			precond("Gonzalez Perez, Juan", an3.getName());
			precond(7.7, an3.getGrade(), 1e-6);
			assertEquals("\n> Error: an3.hashCode(): ", an1HashCode, an3.hashCode());
			//------------------------
			Student an4 = new Student("22456784F", "gonzalez perez, juan", 5.5);
			precond("22456784F", an4.getDni());
			precond("gonzalez perez, juan", an4.getName());
			precond(5.5, an4.getGrade(), 1e-6);
			assertNotEquals("\n> Error: an4.hashCode(): ", an1HashCode, an4.hashCode());
			//------------------------
			Student an5 = new Student("11111111A", "Gonzalez Perez, Juan", 5.5);
			precond("11111111A", an5.getDni());
			precond("Gonzalez Perez, Juan", an5.getName());
			precond(5.5, an5.getGrade(), 1e-6);
			assertNotEquals("\n> Error: an5.hashCode(): ", an1HashCode, an5.hashCode());
		}
		@Test(timeout = 1000)
		public void alumnoToStringTest1() {
			precond("22456784F", an1.getDni());
			precond("Gonzalez Perez, Juan", an1.getName());
			precond(5.5, an1.getGrade(), 1e-6);
			assertEquals("\n> Error: an1.toString():",
						 normalize("Gonzalez Perez, Juan (22456784F)"),
						 normalize(an1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestSubject {
		private static final String nmAsg = "Algebra";
		private static final String[] inputData = {
			"25653443S;Garcia Gomez, Juan;8.1",
			"23322443K;Lopez Turo, Manuel;4.3",
			"24433522M;Merlo Martinez, Juana;5.3",
			"53553421D;Santana Medina, Petra;-7.1",
			"55343442L,Godoy Molina, Marina;6.3",
			"34242432J;Fernandez Vara, Pedro;2.k",
			"42424312G;Lopez Gama, Luisa;7.1"
		};
		private static final Student[] inputValues = crearStudents();
		private static final String[] inputErrors = {
			"ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1",
			"ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3",
			"ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k"
		};
		private static Student[] crearStudents() {
			Student[] alumnos = new Student[0];
			try {
				alumnos = new Student[]{
					new Student("25653443S", "Garcia Gomez, Juan", 8.1),
					new Student("23322443K", "Lopez Turo, Manuel", 4.3),
					new Student("24433522M", "Merlo Martinez, Juana", 5.3),
					new Student("42424312G", "Lopez Gama, Luisa", 7.1)
				};
			} catch (Exception e) {
				fail("\n> Error: crearStudents: Average");
			}
			return alumnos;
		}
		private static void precondSubject(Subject asg) {
			precond(inputValues, asg.getStudents());
			precondNorm(inputErrors, asg.getErrors());
		}
		private Subject asg1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of Subject JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of Subject JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			asg1 = new Subject(nmAsg, inputData);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void asignaturaCtorTest1() {
			assertArrayEquals("\n> Error: new Subject(): asg1.getStudents():", inputValues, asg1.getStudents());
			assertArrayEqualsNorm("\n> Error: new Subject(): asg1.getErrors():", inputErrors, asg1.getErrors());
		}
		@Test(timeout = 1000)
		public void asignaturaCalcMediaTest1() throws Exception {
			precondSubject(asg1);
			assertEquals("\n> Error: asg1.getMedia():", 6.20, asg1.getAverage(), 1e-6);
		}
		@Test(timeout = 1000)
		public void asignaturaCalcMediaTest2() {
			precondSubject(asg1);
			try {
				String[] datos = { "xxx" };
				Subject asg2 = new Subject(nmAsg, datos);
				double valor = asg2.getAverage();
				fail("\n> Error: getMedia(): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: getMedia(): exception.getMessage():", normalize("No students"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: getMedia(): the exception thrown is not StudentException");
			}
		}
		@Test(timeout = 1000)
		public void asignaturaGetCalificacionTest1() throws Exception {
			precondSubject(asg1);
			assertEquals("\n> Error: asg1.getGrade(Garcia Gomez, Juan):", 8.1, asg1.getGrade(inputValues[0]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Lopez Turo, Manuel):", 4.3, asg1.getGrade(inputValues[1]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Merlo Martinez, Juana):", 5.3, asg1.getGrade(inputValues[2]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Lopez Gama, Luisa):", 7.1, asg1.getGrade(inputValues[3]), 1e-6);
		}
		@Test(timeout = 1000)
		public void asignaturaGetCalificacionTest2() {
			precondSubject(asg1);
			try {
				Student an2 = new Student("34242432J", "Fernandez Vara, Pedro");
				double valor = asg1.getGrade(an2);
				fail("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): exception.getMessage():", normalize("The student Fernandez Vara, Pedro (34242432J) has not been found"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): the exception thrown is not StudentException");
			}
		}
		@Test(timeout = 1000)
		public void asignaturaToStringTest1() {
			precondSubject(asg1);
			assertEquals("\n> Error: asg1.toString():",
						 normalize("Algebra: { [Garcia Gomez, Juan (25653443S), Lopez Turo, Manuel (23322443K), Merlo Martinez, Juana (24433522M), Lopez Gama, Luisa (42424312G)], [ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1, ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3, ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k] }"),
						 normalize(asg1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestArithmeticMean {
		private static final Student[] emptyValues = new Student[0];
		private static final Student[] inputValues = crearStudents();
		private ArithmeticMean op1;
		private static Student[] crearStudents() {
			Student[] alumnos = new Student[0];
			try {
				alumnos = new Student[]{
					new Student("25653443S", "Garcia Gomez, Juan", 8.1),
					new Student("23322443K", "Lopez Turo, Manuel", 4.3),
					new Student("24433522M", "Merlo Martinez, Juana", 5.3),
					new Student("53553421D", "Santana Medina, Petra", 0.0),
					new Student("42424312G", "Lopez Gama, Luisa", 7.1)
				};
			} catch (Exception e) {
				fail("\n> Error: crearStudents: Average");
			}
			return alumnos;
		}
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of ArithmeticMean JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of ArithmeticMean JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			op1 = new ArithmeticMean();
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void mediaAritmeticaCtorTest1() {
			assertTrue("\n> Error: ArithmeticMean implements AverageCalculation:", ((Object)op1 instanceof AverageCalculation));
		}
		@Test(timeout = 1000)
		public void mediaAritmeticaCalcularTest1() throws Exception {
			assertEquals("\n> Error: new ArithmeticMean.calculate():", 4.96, op1.calculate(inputValues), 1e-6);
		}
		@Test(timeout = 1000)
		public void mediaAritmeticaCalcularTest2() {
			try {
				double valor = op1.calculate(emptyValues);
				fail("\n> Error: op1.calcula(emptyValues): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: op1.calcula(emptyValues): exception.getMessage():", normalize("No students"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: op1.calcula(emptyValues): the exception thrown is not StudentException");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestHarmonicMean {
		private static final Student[] emptyValues = new Student[0];
		private static final Student[] inputValues = crearStudents();
		private HarmonicMean op1;
		private static Student[] crearStudents() {
			Student[] alumnos = new Student[0];
			try {
				alumnos = new Student[]{
					new Student("25653443S", "Garcia Gomez, Juan", 8.1),
					new Student("23322443K", "Lopez Turo, Manuel", 4.3),
					new Student("24433522M", "Merlo Martinez, Juana", 5.3),
					new Student("53553421D", "Santana Medina, Petra", 0.0),
					new Student("42424312G", "Lopez Gama, Luisa", 7.1)
				};
			} catch (Exception e) {
				fail("\n> Error: crearStudents: Average");
			}
			return alumnos;
		}
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of HarmonicMean JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of HarmonicMean JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			op1 = new HarmonicMean();
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void mediaArmonicaCtorTest1() {
			assertTrue("\n> Error: HarmonicMean implements AverageCalculation:", ((Object)op1 instanceof AverageCalculation));
		}
		@Test(timeout = 1000)
		public void mediaArmonicaCalcularTest1() throws Exception {
			assertEquals("\n> Error: new HarmonicMean.calculate():", 5.834823, op1.calculate(inputValues), 1e-6);
		}
		@Test(timeout = 1000)
		public void mediaArmonicaCalcularTest2() {
			try {
				double valor = op1.calculate(emptyValues);
				fail("\n> Error: op1.calcula(emptyValues): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: op1.calcula(emptyValues): exception.getMessage():", normalize("No students"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: op1.calcula(emptyValues): the exception thrown is not StudentException");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestTrimmedMean {
		private static final Student[] emptyValues = new Student[0];
		private static final Student[] inputValues = crearStudents();
		private TrimmedMean op1;
		private static Student[] crearStudents() {
			Student[] alumnos = new Student[0];
			try {
				alumnos = new Student[]{
					new Student("25653443S", "Garcia Gomez, Juan", 8.1),
					new Student("23322443K", "Lopez Turo, Manuel", 4.3),
					new Student("24433522M", "Merlo Martinez, Juana", 5.3),
					new Student("53553421D", "Santana Medina, Petra", 0.0),
					new Student("42424312G", "Lopez Gama, Luisa", 7.1)
				};
			} catch (Exception e) {
				fail("\n> Error: crearStudents: Average");
			}
			return alumnos;
		}
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of TrimmedMean JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of TrimmedMean JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			op1 = new TrimmedMean(5.0, 9.0);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void mediaSinExtremosCtorTest1() {
			assertTrue("\n> Error: TrimmedMean implements AverageCalculation:", ((Object)op1 instanceof AverageCalculation));
		}
		@Test(timeout = 1000)
		public void mediaSinExtremosCalcularTest1() throws Exception {
			assertEquals("\n> Error: new TrimmedMean.calculate():", 6.833333, op1.calculate(inputValues), 1e-6);
		}
		@Test(timeout = 1000)
		public void mediaSinExtremosCalcularTest2() {
			try {
				double valor = op1.calculate(emptyValues);
				fail("\n> Error: op1.calcula(emptyValues): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: op1.calcula(emptyValues): exception.getMessage():", normalize("No students"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: op1.calcula(emptyValues): the exception thrown is not StudentException");
			}
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTest-----------------------------------------------------------
	//----------------------------------------------------------------------
	public static class JUnitTestSubjectWithAverages {
		private static final String nmAsg = "Algebra";
		private static final String[] inputData = {
			"25653443S;Garcia Gomez, Juan;8.1",
			"23322443K;Lopez Turo, Manuel;4.3",
			"24433522M;Merlo Martinez, Juana;5.3",
			"53553421D;Santana Medina, Petra;-7.1",
			"55343442L,Godoy Molina, Marina;6.3",
			"34242432J;Fernandez Vara, Pedro;2.k",
			"42424312G;Lopez Gama, Luisa;7.1"
		};
		private static final Student[] inputValues = crearStudents();
		private static final String[] inputErrors = {
			"ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1",
			"ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3",
			"ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k"
		};
		private static Student[] crearStudents() {
			Student[] alumnos = new Student[0];
			try {
				alumnos = new Student[]{
					new Student("25653443S", "Garcia Gomez, Juan", 8.1),
					new Student("23322443K", "Lopez Turo, Manuel", 4.3),
					new Student("24433522M", "Merlo Martinez, Juana", 5.3),
					new Student("42424312G", "Lopez Gama, Luisa", 7.1)
				};
			} catch (Exception e) {
				fail("\n> Error: crearStudents: Average");
			}
			return alumnos;
		}
		private static void precondSubjectWithAverages(SubjectWithAverages asg) {
			precond(inputValues, asg.getStudents());
			precondNorm(inputErrors, asg.getErrors());
		}
		private SubjectWithAverages asg1;
		@BeforeClass
		public static void beforeClass() {
			// Code executed before the first test method
			System.out.println("Start of SubjectWithAverages JUnit Test");
		}
		@AfterClass
		public static void  afterClass() {
			// Code executed after the last test method
			System.out.println("End of SubjectWithAverages JUnit Test");
		}
		@Before
		public void setUp() {
			// Code executed before each test
			asg1 = new SubjectWithAverages(nmAsg, inputData, null);
		}
		@After
		public void tearDown() {
			// Code executed after each test
		}
		@Test(timeout = 1000)
		public void asignaturaCtorTest1() {
			assertArrayEquals("\n> Error: new SubjectWithAverages(): asg1.getStudents():", inputValues, asg1.getStudents());
			assertArrayEqualsNorm("\n> Error: new SubjectWithAverages(): asg1.getErrors():", inputErrors, asg1.getErrors());
			assertEquals("\n> Error: new SubjectWithAverages(): asg1.getAverageCalculation():", null, asg1.getAverageCalculation());
		}
		@Test(timeout = 1000)
		public void asignaturaCtorTest2() {
			precondSubjectWithAverages(asg1);
			ArithmeticMean cm = new ArithmeticMean();
			SubjectWithAverages asg2 = new SubjectWithAverages(nmAsg, inputData, cm);
			assertArrayEquals("\n> Error: new SubjectWithAverages(): asg2.getStudents():", inputValues, asg2.getStudents());
			assertArrayEqualsNorm("\n> Error: new SubjectWithAverages(): asg2.getErrors():", inputErrors, asg2.getErrors());
			assertEquals("\n> Error: new SubjectWithAverages(): asg2.getAverageCalculation():", cm, asg2.getAverageCalculation());
		}
		@Test(timeout = 1000)
		public void asignaturaSetCalcMediaTest1() throws Exception {
			precondSubjectWithAverages(asg1);
			ArithmeticMean cm = new ArithmeticMean();
			asg1.setAverageCalculation(cm);
			assertEquals("\n> Error: asg1.setAverageCalculation(): asg1.getAverageCalculation():", cm, asg1.getAverageCalculation());
		}
		@Test(timeout = 1000)
		public void asignaturaCalcMediaTest1() throws Exception {
			precondSubjectWithAverages(asg1);
			asg1.setAverageCalculation(new ArithmeticMean());
			assertEquals("\n> Error: asg1.getMedia():", 6.20, asg1.getAverage(), 1e-6);
		}
		@Test(timeout = 1000)
		public void asignaturaCalcMediaTest2() {
			precondSubjectWithAverages(asg1);
			try {
				String[] datos = { "xxx" };
				ArithmeticMean cm = new ArithmeticMean();
				SubjectWithAverages asg2 = new SubjectWithAverages(nmAsg, datos, cm);
				double valor = asg2.getAverage();
				fail("\n> Error: getMedia(): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: getMedia(): exception.getMessage():", normalize("No students"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: getMedia(): the exception thrown is not StudentException");
			}
		}
		@Test(timeout = 1000)
		public void asignaturaGetCalificacionTest1() throws Exception {
			precondSubjectWithAverages(asg1);
			assertEquals("\n> Error: asg1.getGrade(Garcia Gomez, Juan):", 8.1, asg1.getGrade(inputValues[0]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Lopez Turo, Manuel):", 4.3, asg1.getGrade(inputValues[1]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Merlo Martinez, Juana):", 5.3, asg1.getGrade(inputValues[2]), 1e-6);
			assertEquals("\n> Error: asg1.getGrade(Lopez Gama, Luisa):", 7.1, asg1.getGrade(inputValues[3]), 1e-6);
		}
		@Test(timeout = 1000)
		public void asignaturaGetCalificacionTest2() {
			precondSubjectWithAverages(asg1);
			try {
				Student an2 = new Student("34242432J", "Fernandez Vara, Pedro");
				double valor = asg1.getGrade(an2);
				fail("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): No exception thrown");
			} catch (StudentException e) {
				assertEquals("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): exception.getMessage():", normalize("The student Fernandez Vara, Pedro (34242432J) has not been found"), normalize(e.getMessage()));
			} catch (Exception e) {
				fail("\n> Error: asg1.getGrade(Fernandez Vara, Pedro): the exception thrown is not StudentException");
			}
		}
		@Test(timeout = 1000)
		public void asignaturaToStringTest1() {
			precondSubjectWithAverages(asg1);
			assertEquals("\n> Error: asg1.toString():",
						 normalize("Algebra: { [Garcia Gomez, Juan (25653443S), Lopez Turo, Manuel (23322443K), Merlo Martinez, Juana (24433522M), Lopez Gama, Luisa (42424312G)], [ERROR. Negative grade: 53553421D;Santana Medina, Petra;-7.1, ERROR. Missing data: 55343442L,Godoy Molina, Marina;6.3, ERROR. Non numerical grade: 34242432J;Fernandez Vara, Pedro;2.k] }"),
						 normalize(asg1.toString()));
		}
		//------------------------------------------------------------------
	}
	//----------------------------------------------------------------------
	//--JUnitTestSuite------------------------------------------------------
	//----------------------------------------------------------------------
	@RunWith(Suite.class)
	@Suite.SuiteClasses({ JUnitTestStudentException.class ,
				JUnitTestStudent.class ,
				JUnitTestSubject.class ,
				JUnitTestArithmeticMean.class ,
				JUnitTestHarmonicMean.class ,
				JUnitTestTrimmedMean.class ,
				JUnitTestSubjectWithAverages.class
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
				System.out.println(">>> Error: " + ac + "tests could not be executed due to errors in other methods");
			}
			if (fc > 0) {
				System.out.println(">>> Error: "+fc+" tests failed due to errors in methods");
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
	//------------------------------------------------------------------
	private static void precond(int[] expectedValue, int[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(precondMessage, expectedValue == currValue);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	private static void precond(double[] expectedValue, double[] currValue, double delta) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(precondMessage, expectedValue == currValue);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i], delta);
			}
		}
	}
	private static <T> void precond(T[] expectedValue, T[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(precondMessage, expectedValue == currValue);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precond(expectedValue[i], currValue[i]);
			}
		}
	}
	//----------------------------------------------------------------------
	private static void precondNorm(String expectedValue, String currValue) {
		precond(normalize(expectedValue), normalize(currValue));
	}
	private static void precondNorm(String[] expectedValue, String[] currValue) {
		if ((expectedValue == null)||(currValue == null)) {
			assumeTrue(precondMessage, expectedValue == currValue);
		} else {
			precond(expectedValue.length, currValue.length);
			for (int i = 0; i < expectedValue.length; ++i) {
				precondNorm(expectedValue[i], currValue[i]);
			}
		}
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
