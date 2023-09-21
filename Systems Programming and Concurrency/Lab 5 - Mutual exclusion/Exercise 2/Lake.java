package exer2;

public class Lake {

	private volatile int level = 0;
	private Peterson petRiver = new Peterson();
	private Peterson petDam = new Peterson();
	private Peterson petDamRiver = new Peterson();

	// peterson algorithm for river
	/*
	 * private volatile int rTurn = 0; private volatile boolean rf0 = false; private
	 * volatile boolean rf1 = false;
	 * 
	 * // peterson algorithm for dam private volatile int dTurn = 0; private
	 * volatile boolean df0 = false; private volatile boolean df1 = false;
	 */

	// peterson algorithm for dams and rivers
	/*private volatile int drTurn = 0; // 0 for dams and 1 for rivers
	private volatile boolean drf0 = false;
	private volatile boolean drf1 = false;*/

	// it is only used by rivers
	public void increment(int id) {
		// pre-protocol -> rivers
		/*
		 * if (id == 0) { rf0 = true; rTurn = 1; while (rf1 && rTurn == 1) {
		 * Thread.yield(); } } else { rf1 = true; rTurn = 0; while (rf0 && rTurn == 0) {
		 * Thread.yield(); } }
		 */
		petRiver.preProtocol(id);

		// pre-protocol for rivers and dams
		/*drf1 = true;
		drTurn = 0;
		while (drf0 && drTurn == 0) {
			Thread.yield();
		}*/
		petDamRiver.preProtocol(id);

		// critical section
		level++;

		// post-protocol for rivers and dams
		//drf1 = false;
		petDamRiver.postProtocol(id);

		// post-protocol -> rivers
		/*
		 * if (id == 0) { rf0 = false; } else { rf1 = false; }
		 */
		petRiver.postProtocol(id);

	}

	// it is used only by dams
	public void decrement(int id) {

		// pre-protocol -> dams
		/*
		 * if (id == 0) { df0 = true; dTurn = 1; while (df1 && dTurn == 1) {
		 * Thread.yield(); } } else { df1 = true; dTurn = 0; while (df0 && dTurn == 0) {
		 * Thread.yield(); } }
		 */
		petDam.preProtocol(id);

		// synchronization
		while (level <= 0) {
			Thread.yield();
		}

		// pre-protocol for rivers and dams
		/*drf0 = true;
		drTurn = 1;
		while (drf1 && drTurn == 1) {
			Thread.yield();
		}*/
		petDamRiver.preProtocol(id);

		// critical section
		level--;

		// post-protocol for rivers and dams
		//drf0 = false;
		petDamRiver.postProtocol(id);

		// post-protocol -> dams
		/*
		 * if (id == 0) { df0 = false; } else { df1 = false; }
		 */
		petDam.postProtocol(id);

	}

	public int getLevel() {
		return level;
	}

}
