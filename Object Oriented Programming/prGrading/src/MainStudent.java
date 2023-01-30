import prGrading.Student;
import prGrading.StudentException;

public class MainStudent {

	public static void main(String[] args) {

		try {
			Student t1 = new Student("22456784F", "Gonzalez Perez, Juan", 5.5);
			Student t2 = new Student("33456777S", "Gonzalez Perez, Juan", 3.4);

			System.out.println(String.format("DNI: %s, Name: %s, Grade: %f", t1.getDni(), t1.getName(), t1.getGrade()));
			System.out.println(String.format("DNI: %s, Name: %s, Grade: %f", t2.getDni(), t2.getName(), t2.getGrade()));
			
			if(t1.equals(t2)) {
				System.out.println(String.format("The students %s and %s are equal", t1.toString(), t2.toString()));
			} else {
				System.out.println(String.format("The students %s and %s are not equal", t1.toString(), t2.toString()));
			}

			Student t3 = new Student("33456776S", "Gonzalez Perez, Juan", -3.4);

		} catch (StudentException e) {
			e.printStackTrace();
		}

	}

}
