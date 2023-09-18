package exer1;

public class CircularBuffer {

	private volatile int[] items;
	private volatile int p, c, nItem;
	// peterson algorithm
	private volatile int turn; // 1 for consumer, 2 for producer
	private volatile boolean f1;
	private volatile boolean f2;

	public CircularBuffer(int n) {
		items = new int[n];
		turn = 1;
		f1 = false;
		f2 = false;
	}

	public void consume() {
		// synchronization
		while (nItem == 0) {
			Thread.yield();
		}

		// pre-protocol
		f1 = true;
		turn = 2;
		while (f2 && turn == 2) {
			Thread.yield();
		}

		// critical section
		System.out.println("Consume: " + items[c]);
		c = (c + 1) % items.length;
		nItem--;

		// post-protocol
		f1 = false;
	}

	public void produce(int i) {
		// synchronization
		while (nItem >= items.length) {
			Thread.yield();
		}

		// pre-protocol
		f2 = true;
		turn = 1;
		while (f1 && turn == 1) {
			Thread.yield();
		}
		
		// critical section
		items[p] = i;
		p = (p + 1) % items.length;
		nItem++;
		
		// post-protocol
		f2 = false;

	}

}
