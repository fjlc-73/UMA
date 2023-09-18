package ex3;

public class Smoker extends Thread {
	private Table t;
	private Ingredients ing;
	public Smoker(Ingredients ing, Table t) {
		this.ing = ing;
		this.t = t;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				t.lookingForEverythingBut(ing);
				sleep(100);
				System.out.println("Smoker "+ing+" is smoking.");
				t.smokingFinished();
				System.out.println("Smoker "+ing+" finished smoking.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
