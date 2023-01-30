package prGrading;

public class StudentWithDegree extends Student {
	
	private Degree degree;
	
	public StudentWithDegree(String d, String n, Degree de, double g) throws StudentException {
		super(d,n,g);
		degree = de;
	}
	
	public StudentWithDegree(String d, String n, Degree de) throws StudentException {
		super(d,n);
		degree = de;
	}
	
	public StudentWithDegree(String d, String n, String de, double g) throws StudentException {
		super(d,n,g);
		try {
			degree = Degree.valueOf(de.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new StudentException("Wrong degree");
		}
	}
	
	public StudentWithDegree(String d, String n, String de) throws StudentException {
		this(d,n,de,0);
	}

	public Degree getDegree() {
		return degree;
	}
	
	public String toString() {
		return String.format("%s (%s) %s", this.getName(), this.getDni(), degree.toString());
	}
	
	
	

}
