import java.util.StringJoiner;

import prGrading.Student;
import prGrading.StudentException;
import prGrading.Subject;

public class MainSubject {

	public static void main(String[] args) {
		String[] ss = {"12455666F;Lopez Perez, Pedro;6.7","33678999D;Merlo Gomez, Isabel;5.8","23555875G;Martinez Herrera, Lucia;9.1"};
		Subject poo = new Subject("POO", ss);
		
		try {
			System.out.println("Average " + poo.getAverage());
			
			StringJoiner sj = new StringJoiner(",", "", "");
			for (int i = 0; i < poo.getStudents().length; i++) {
				sj.add(poo.getStudents()[i].getDni());
			}
			
			System.out.println("DNIs: " + sj.toString());
			
			Student t = new Student("12455666F", "Lopez Perez, Pedro", 6.7);
			System.out.println("Grade of " + t + ": "  + poo.getGrade(t));
			
			Student tt = new Student("12455666F", "Lopez Lopez, Pedro");
			System.out.println("Grade of " + tt + poo.getGrade(tt));
		} catch (StudentException e) {
			System.out.println(e.getMessage());
		}

	}

}
