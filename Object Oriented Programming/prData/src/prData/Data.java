package prData;

import java.util.Arrays;

public class Data {
	
	private double[] data;
	private String[] errors;
	private double min;
	private double max;
	
	
	public Data (String[] values, double d1, double d2) {
		processData(values);
		min = d1;
		max = d2;
		optData(data);
		optError(errors);
	}
	
	public double calcAverage() {
		double num=0;
		double sum=0;
		
		for (double d: data) {
			if (d <= max && d>= min) {
				sum += d;
				num ++;
			}
		}
		
		if(num==0) {
			throw new DataException("There is no data in the given range");
		}
		return sum/num;
	}
	
	public double calcStandardDeviation() {
		double average = calcAverage();
		double num = 0;
		double dev=0;
		
		for (double d: data) {
			if (d <= max && d>= min) {
				dev+= Math.pow((d-average), 2);
				num++;
			}
		}
		dev = Math.sqrt(dev/num);
		return dev;
	}

	public void setRange(String s) {
		String [] pieces = s.split("[;]");
		
		try {
			min = Double.parseDouble(pieces[0]);
			max = Double.parseDouble(pieces[1]);
		}
		catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			throw new DataException("Data error setting range");
		}
	}
	
	public double[] getData() {return data;}
	
	public String[] getErrors() {return errors;}
	
	public String toString() {
		String s = "Min: " + min + ", Max: " + max + ",";
		s+=java.util.Arrays.toString(data) + "," + java.util.Arrays.toString(errors) + ", ";
		
		try {
		s+= " Average: " + calcAverage() + ", StandardDeviation: " + calcStandardDeviation();
		} catch (DataException e) {
			s+= " Average: " + "ERROR" + ", StandardDeviation: " + "ERROR";
		}
		return s;
	}
	
	private void optData(double [] d) {
		int cont = 0;
		for(int i = 0; i < d.length; i++) {
			if(d[i]!=0) {
				cont++;
			}
		}
		data = Arrays.copyOfRange(d, 0, cont);
	}
	
	private void optError(String [] s) {
		int cont = 0;
		for(int i = 0; i < s.length; i++) {
			if(s[i]!= null) {
				cont++;
			}
		}
		errors = Arrays.copyOfRange(s, 0, cont);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void processData(String[] values) {
		data = new double[values.length];
		errors = new String[values.length];
		int numData = 0;
		int numErrors = 0;
		
		for (String s: values) {
			try {
				data[numData]=Double.parseDouble(s);
				numData++;
			}
			catch (NumberFormatException e) {
				errors[numErrors]=s;
				numErrors++;
			}
		}
	}

}
