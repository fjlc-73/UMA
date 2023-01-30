import java.util.Arrays;

import prData.Data;
import prData.DataException;

public class MainData {

	public static void main(String[] args) {
		double min;
		double max;
		String[] s;
		
		try {
		min = Double.parseDouble(args[0]);
		max = Double.parseDouble(args[1]);
		s = Arrays.copyOfRange(args, 2, args.length);
        Data d = new Data(s, min, max);
		
		System.out.println(d);
		d.setRange("0;4");
		System.out.println(d);
		d.setRange("15 25");
		
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error, there are not enough values");
		} catch (NumberFormatException ee) {
			System.out.println("Error, when converting a value to a real number (" + ee.getMessage() + ")");
		} catch(DataException eee) {
			System.out.println(eee.getMessage());
		}
		
		
		

	}

}
