package prGrading;

public class Student {

	private String dni;
	private String name;
	private double grade;

	public Student(String d, String n, double g) throws StudentException {
		if (g < 0) {
			throw new StudentException("Negative grade");
		}

		dni = d;
		name = n;
		grade = g;
	}

	public Student(String d, String n) throws StudentException {
		this(d, n, 0);
	}

	public boolean equals(Object o) {
		return (o instanceof Student) && this.dni.equalsIgnoreCase(((Student) o).dni)
				&& (((Student) o).name.equals(this.name));
	}

	public int hashCode() {
		return (this.dni.toLowerCase().hashCode() + this.name.hashCode());
	}

	public String toString() {

		return String.format("%s (%s)", name, dni);
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public double getGrade() {
		return grade;
	}

}
