package exer4;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Bridge {
	public static final Random rnd = new Random();
	private Color leftLight = Color.green, rightLight = Color.green;
	private Semaphore mutex = new Semaphore(1, true);
	private int numCrossingCars = 0, leftQueueSize = 0, rightQueueSize = 0;
	private Semaphore leftQueue = new Semaphore(0, true);
	private Semaphore rightQueue = new Semaphore(0, true);

	public void beginLeftToRight(int id) throws InterruptedException {
		mutex.acquire();
		rightLight = Color.red;
		if (leftLight == Color.green) {
			numCrossingCars++;
			mutex.release();
		} else {
			leftQueueSize++;
			mutex.release();
			leftQueue.acquire();
			leftQueueSize--;
			numCrossingCars++;
			if (leftQueueSize > 0) {
				leftQueue.release();
			} else {
				mutex.release();
			}
		}

	}

	public void endLeftToRight(int id) throws InterruptedException {
		mutex.acquire();
		numCrossingCars--;
		if (numCrossingCars == 0) {
			if (rightQueueSize > 0) {
				if (leftQueueSize == 0) {
					rightLight = Color.green;
				}
				rightQueue.release();
			} else {
				rightLight = Color.green;
				mutex.release();
			}
		} else {
			mutex.release();
		}
	}

	public void beginRightToLeft(int id) throws InterruptedException {
		mutex.acquire();
		leftLight = Color.red;
		if (rightLight == Color.green) {
			numCrossingCars++;
			mutex.release();
		} else {
			rightQueueSize++;
			mutex.release();
			rightQueue.acquire();
			rightQueueSize--;
			numCrossingCars++;
			if (rightQueueSize > 0) {
				rightQueue.release();
			} else {
				mutex.release();
			}
		}

	}

	public void endRightToLeft(int id) throws InterruptedException {
		mutex.acquire();
		numCrossingCars--;
		if (numCrossingCars == 0) {
			if (leftQueueSize > 0) {
				if (rightQueueSize == 0) {
					leftLight = Color.green;
				}
				leftQueue.release();
			} else {
				leftLight = Color.green;
				mutex.release();
			}
		} else {
			mutex.release();
		}
	}
}

