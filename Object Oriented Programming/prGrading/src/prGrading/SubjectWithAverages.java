package prGrading;

public class SubjectWithAverages extends Subject {
	
	private AverageCalculation calc;
	
	public SubjectWithAverages(String s, String[] ss, AverageCalculation a) {
		super(s, ss);
		calc = a;
	}
	
	public double getAverage() throws StudentException {
		return calc.calculate(this.getStudents());
	}

	public AverageCalculation getAverageCalculation() {
		return calc;
	}

	public void setAverageCalculation(AverageCalculation calc) {
		this.calc = calc;
	}


}
