package prJugs;

public class Jug {
	private final int capacity;
	private int content;

	public Jug(int x) {
		content = 0;
		if (x <= 0) {
			throw new RuntimeException("negative or zero capacity");
		}
		capacity = x;

	}

	public int getCapacity() {
		return capacity;
	}

	public int getContent() {
		return content;
	}

	public void fill() {
		content = capacity;
	}

	public void empty() {
		content = 0;
	}

	public void pourFrom(Jug j) {
		if (this == j) {
			throw new RuntimeException("Can't pour from same Jug");
		}

		if ((this.capacity - this.content - j.content) < 0) {
			j.content = j.content - (this.capacity-this.content);
			this.content = this.capacity;

		} else {
			this.content += j.content;
			j.content = 0;
		}
	}

	public String toString() {
		return ("J(" + this.capacity + "," + this.content + ")");
	}
}
