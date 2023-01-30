package prGrading;

public class TrimmedMean implements AverageCalculation {
	
	private double min;
	private double max;
	
	
	
	

	public TrimmedMean(double mi, double ma) {
		min=mi;
		max=ma;
	}




	public double getMin() {
		return min;
	}




	public void setMin(double min) {
		this.min = min;
	}




	public double getMax() {
		return max;
	}




	public void setMax(double max) {
		this.max = max;
	}




	public double calculate(Student[] s) throws StudentException {
		double avg = 0;
		int cont = 0;
		
		for (int i = 0; i < s.length; i++) {
			if(s[i].getGrade() < max && s[i].getGrade() > min) {
			avg += s[i].getGrade();
			cont++;
			}
		}
		
		if(cont == 0) {
			throw new StudentException("No students");
		}
		
		return avg/cont;
	}

}
