package exer4;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Fibonacci fib = new Fibonacci(6);
		fib.start();
		
		try {
			fib.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(fib.getRes());

	}

}
