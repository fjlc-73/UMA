package exer4;

public class Fibonacci extends Thread {
	
	private Integer num,res;
	private boolean ready;
	
	
	public Fibonacci(Integer n) {
		num=n;
		ready=false;
	}

	
	
	public boolean isReady() {
		return ready;
	}



	public void setReady(boolean ready) {
		this.ready = ready;
	}



	public Integer getRes() {
		return res;
	}



	public void setRes(Integer res) {
		this.res = res;
	}



	public Integer getNum() {
		return num;
	}



	public void setNum(Integer num) {
		this.num = num;
	}



	public void run() {
		if(num==0) {
			res=0;
			ready=true;
		} else if(num==1) {
			res=1;
			ready=true;
		}else {
			Fibonacci fn1 = new Fibonacci(num-1);
			Fibonacci fn2 = new Fibonacci(num-2);
			fn1.start();
			fn2.start();
			
			while(!fn1.isReady() || !fn2.isReady()) {
				Thread.yield();
			}
						
			res = fn1.getRes()+fn2.getRes();
			ready = true;
			
			
		}
	}
}
