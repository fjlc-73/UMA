import prWordIndex.LineIndex;
import prWordIndex.PositionInLineIndex;
import prWordIndex.CounterIndex;
import prWordIndex.Index;

public class MainIndex {
	public static void main(String args[]) {
		// String delimiters = "[^a-zA-Z0-9áéíóúüÁÉÍÓÚÜ]+";
		String delimiters = "[ .,:;\\-\\!\\¡\\¿\\?]+";
		//Index cp = new CounterIndex();
		// Index cp = new LineIndex();
		 Index cp = new PositionInLineIndex();
		cp.addSentence("Guerra tenia una jarra y Parra tenia una perra, "
				+ "pero la perra de Parra rompio la jarra de Guerra.");
		cp.addSentence("Guerra pego con la porra a la perra de Parra. " + "¡Oiga usted buen hombre de Parra! "
				+ "Por que ha pegado con la porra a la perra de Parra.");
		cp.addSentence("Porque si la perra de Parra no hubiera roto " + "la jarra de Guerra, "
				+ "Guerra no hubiera pegado con la porra " + "a la perra de Parra.");
		cp.solve(delimiters);
		cp.presentIndex();
	}
}
