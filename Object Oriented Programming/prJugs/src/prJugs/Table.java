package prJugs;

public class Table {
	private Jug jug1, jug2;

	public Table(Jug a, Jug b) {
		if (a == b) {
			throw new RuntimeException("2 jugs in table can't be the same");
		}
		jug1 = a;
		jug2 = b;
	}

	public Table(int x, int y) {
		jug1 = new Jug(x);
		jug2 = new Jug(y);
	}

	public int getCapacity(int x) {
		if (x == 1) {
			return jug1.getCapacity();
		} else if (x == 2) {
			return jug2.getCapacity();
		} else {
			throw new RuntimeException("Jugs in Table are only 1 and 2");
		}
	}

	public int getContent(int x) {
		if (x == 1) {
			return jug1.getContent();
		} else if (x == 2) {
			return jug2.getContent();
		} else {
			throw new RuntimeException("Jugs in Table are only 1 and 2");
		}
	}

	public void fill(int x) {
		if (x == 1) {
			jug1.fill();
		} else if (x == 2) {
			jug2.fill();
		} else {
			throw new RuntimeException("Jugs in Table are only 1 and 2");
		}
	}

	public void empty(int x) {
		if (x == 1) {
			jug1.empty();
		} else if (x == 2) {
			jug2.empty();
		} else {
			throw new RuntimeException("Jugs in Table are only 1 and 2");
		}
	}

	public void pourFrom(int x) {
		if (x == 1) {
			jug2.pourFrom(jug1);
		} else if (x == 2) {
			jug1.pourFrom(jug2);
		} else {
			throw new RuntimeException("Jugs in Table are only 1 and 2");
		}
	}

	public String toString() {
		return ("M(" + jug1 + "," + jug2 +")");
	}

}