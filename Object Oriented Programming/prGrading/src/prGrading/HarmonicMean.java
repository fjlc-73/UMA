package prGrading;

public class HarmonicMean implements AverageCalculation {

	@Override
	public double calculate(Student[] s) throws StudentException {
		double avg = 0;
		int cont =0;
		if (s.length == 0) {
			throw new StudentException("No students");
		}
		
	
		for (int i = 0; i < s.length; i++) {
			if(s[i].getGrade()>0) {
			avg += 1/s[i].getGrade();
			cont++;
			}
		}
		avg = cont/avg;
		
		
		return avg;
	}

}
