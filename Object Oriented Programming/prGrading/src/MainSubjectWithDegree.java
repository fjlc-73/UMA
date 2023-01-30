
import java.util.Locale;

import prGrading.*;

public class MainSubjectWithDegree {
	static final String[] als = { "25653443S;Garcia Gomez, Juan;Software;8.1",
								  "23322443K;Lopez Turo, Manuel;Informatica;4.3",
								  "24433522M;Merlo Martinez, Juana;Computadores;5.3",
								  "53553421D;Santana Medina, Petra;Software;-7.1",
								  "55343442L,Godoy Molina, Marina;Informatica;6.3",
								  "34242432J;Vara Riera, Pedro;Computadores;2.k",
								  "42424312G;Lopez Gama, Luisa;Software;7.1",
								  "21658324F;Jimenez Ayo, Pepe;Medicina;8.3",
								  "76213433Q;Gomez Ayuso, Laura;Salud;8.3",
								  "89234024N;Navas Perez, Luis;Informatica;6.5",
								  "92748362F;Leon Zarate, Maria;Computadores;7.5" };

	public static void main(String[] args) {
		try {
			SubjectWithDegree algebra = new SubjectWithDegree("Algebra", als, new ArithmeticMean());
			try {
				Student al1 = new StudentWithDegree("23322443k", "Lopez Turo, Manuel", "Informatica");
				Student al2 = new StudentWithDegree("34242432J", "Vara Riera, Pedro", Degree.COMPUTADORES);
				System.out.println("Grade of " + al1 + ": " + algebra.getGrade(al1));
				System.out.println("Grade of " + al2 + ": " + algebra.getGrade(al2));
			} catch (StudentException e) {
				System.out.println(e.getMessage());
			}
			try {
				System.out.printf(Locale.ENGLISH, "Average: %.2f\n", algebra.getAverage());
			} catch (StudentException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Students...");
			for (Student alumno : algebra.getStudents()) {
				System.out.println(alumno + ": " + alumno.getGrade());
			}
			System.out.println("Errors...");
			for (String error : algebra.getErrors()) {
				System.out.println(error);
			}
			System.out.println("Subject...");
			System.out.println(algebra);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
