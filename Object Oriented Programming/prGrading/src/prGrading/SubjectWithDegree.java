package prGrading;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SubjectWithDegree extends SubjectWithAverages {
	
	public SubjectWithDegree(String s, String[] ss, AverageCalculation a) {
		super(s, ss, a);
		processStudentsWithDegrees(ss);
	}
	
	public String toString() {
		return super.toString();
	}
	
	public double getAverage(Degree d) throws StudentException {
		int numstudents = 0;
		StudentWithDegree[] st = new StudentWithDegree[students.length];
		for(int i = 0; i < students.length; i++) {
			if(((StudentWithDegree)students[i]).getDegree() == d) {
				st[numstudents]=(StudentWithDegree) students[i];
				numstudents++;
			}
		}
		return getAverageCalculation().calculate(st);
	}
	
	
	
	
	private void processStudentsWithDegrees(String[] s) {
		
		int numerrors = 0;
		int numstudents = 0;
		StudentWithDegree[] st = new StudentWithDegree[s.length];
		String [] er = new String[s.length];
		
		for(int i = 0; i < s.length; i++) {
			try (Scanner sc = new Scanner(s[i])){
				
				sc.useLocale(Locale.ENGLISH);
				sc.useDelimiter("\\s*[;]\\s*");
				
				String dni = sc.next();
				String name = sc.next();
				String de = sc.next();
				double grade = sc.nextDouble();
				if(grade < 0) {
					throw new RuntimeException();
				}
				StudentWithDegree t = new StudentWithDegree(dni,name,de,grade);
				st[numstudents]=t;
				numstudents++;
				
			 } catch(InputMismatchException eee) {
				er[numerrors]="ERROR. Non numerical grade: " + s[i];
				numerrors++;
				
					
			 } catch(NoSuchElementException e) {
				er[numerrors]="ERROR. Missing data: " + s[i];
				numerrors++;
			
			}catch (RuntimeException ee) {
				er[numerrors]="ERROR. Negative grade: " + s[i];
				numerrors++;
			}catch (StudentException eee) {
				er[numerrors]="ERROR. Wrong degree: " + s[i];
				numerrors++;
			}
		
			
			
		}
		
		students = Arrays.copyOfRange(st,0,numstudents);
		errors = Arrays.copyOfRange(er, 0, numerrors);
	}

}
