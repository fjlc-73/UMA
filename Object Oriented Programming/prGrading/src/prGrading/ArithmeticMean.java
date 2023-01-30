package prGrading;

public class ArithmeticMean implements AverageCalculation {

	
	public double calculate(Student[] s) throws StudentException {
		double avg = 0;
		int cont = 0;
		if (s.length == 0) {
			throw new StudentException("No students");
		}
		
		for (int i = 0; i < s.length; i++) {
			if(s[i]!=null) {
			avg += s[i].getGrade();
			cont++;
			}
		}
		
		return avg/cont;
	}

}
