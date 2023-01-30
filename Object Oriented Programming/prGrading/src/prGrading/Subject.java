package prGrading;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Subject {
	
	private String name;
	

	protected String[] errors;
	protected Student[] students;
	
	
	public Subject(String s, String[] ss) {
		name = s;
		processStudents(ss);
	}
	
	public String[] getErrors() {
		return errors;
	}
	
	public String getName() {
		return name;
	}
	
    public Student[] getStudents() {
		return students;
	}

	public double getGrade(Student s) throws StudentException {
		int pos = searchStudent(s);
		if (pos == -1) {
			throw new StudentException("The student " + s + " has not been found");
		}
		
		return students[pos].getGrade();
	}
	
	public String toString() {
		StringJoiner sj = new StringJoiner(",", "{", "}");
		sj.add(java.util.Arrays.toString(students)).add(java.util.Arrays.toString(errors));
		return this.getName() + ": " + sj.toString();
	}
	
	public double getAverage() throws StudentException {
		double avg = 0;
		if (students.length == 0) {
			throw new StudentException("No students");
		}
		
		for (int i = 0; i < students.length; i++) {
			avg += students[i].getGrade();
		}
		
		return avg/students.length;
	}
	
	
	
	
	
	
	private int searchStudent (Student s) {
		int cont = 0;
		int pos = -1;
		boolean found = false;
		
		while (!found && cont < students.length) {
			if (students[cont].equals(s)){
				found = true;
			} else {
			cont ++;
			}
		
		}
		if(found) {
			pos = cont;
		}
		return pos;
	}
	
	private void processStudents(String[] s) {
		
		int numerrors = 0;
		int numstudents = 0;
		Student[] st = new Student[s.length];
		String [] er = new String[s.length];
		
		for(int i = 0; i < s.length; i++) {
			try (Scanner sc = new Scanner(s[i])){
				
				sc.useLocale(Locale.ENGLISH);
				sc.useDelimiter("\\s*[;]\\s*");
				
				String dni = sc.next();
				String name = sc.next();
				double grade = sc.nextDouble();
				Student t = new Student(dni,name,grade);
				st[numstudents]=t;
				numstudents++;
				
			 } catch(InputMismatchException eee) {
				er[numerrors]="ERROR. Non numerical grade: " + s[i];
				numerrors++;
				
			 } catch(NoSuchElementException e) {
				er[numerrors]="ERROR. Missing data: " + s[i];
				numerrors++;
			
			}catch (StudentException ee) {
				er[numerrors]="ERROR. Negative grade: " + s[i];
				numerrors++;
			}
			
			
		}
		
		students = Arrays.copyOfRange(st,0,numstudents);
		errors = Arrays.copyOfRange(er, 0, numerrors);
	}
	

}
