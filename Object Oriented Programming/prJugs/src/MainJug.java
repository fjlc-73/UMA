import prJugs.Jug;

public class MainJug {

	public static void main(String[] args) {
		Jug jugA = new Jug(7);
		Jug jugB = new Jug(4);

		jugA.fill();
		System.out.println(jugA + ", " + jugB);

		jugB.pourFrom(jugA);
		System.out.println(jugA + ", " + jugB);

		jugB.empty();
		System.out.println(jugA + ", " + jugB);

		jugB.pourFrom(jugA);
		System.out.println(jugA + ", " + jugB);

	}

}
