import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FriendsGame extends GameTurn {
	private ASCIIArt art = new ASCIIArt();
	private List<String> parameters;

	public FriendsGame(List<String> s) {
		if (!s.contains("-r") && !s.contains("-u") && !s.contains("-fr") && !s.contains("-fe") && !s.contains("-a")) {
			Game g = new Game();
		}
		art.draw("DARTS");
		parameters = s;
		inserisciNumGiocatori();
		inserisciNomiGiocatori();
		inserisciPunti();
		Welcome();
		while (!checkWin()) {
			gioca(giocatori.get(turno));
			passaTurno();
			summarize();
		}
	}

	private void inserisciPunti() {
		puntiTotali = 501;
		Iterator<Player> it = giocatori.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			current.setPoints(puntiTotali);
		}
	}

	private void summarize() {
		System.out.println("\n\n");
		System.out.println("\n%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("Situazione attuale:\n");
		// Comparator<Player> sorted = (e1, e2) -> Integer.compare(
		// e1.getPoints(), e2.getPoints());
		// giocatori.stream().sorted(sorted)
		// .forEach(e -> e.getInfo());

		// Sorting
		ArrayList<Player> sortable = new ArrayList<Player>(giocatori.size());
		for (Player item : giocatori)
			sortable.add(item.clone());

		Collections.sort(sortable, new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {

				return Integer.compare(p1.getPoints(), p2.getPoints());
			}
		});
		for (int i = 0; i < sortable.size(); i++) {
			sortable.get(i).getInfo();
		}

		System.out.println("%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("\n\n");
	}

	public void Welcome() {
		System.out.println("\n\n");
		System.out.println("%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("La partita ha inizio! Giocano:\n");
		Iterator<Player> it = giocatori.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			System.out.println(current.getName() + " con " + current.getPoints() + " punti.");
		}
		System.out.println("%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("\n\n");
	}

	private void inserisciNumGiocatori() {
		numGioc = parameters.size();
	}

	private void inserisciNomiGiocatori() {
		Iterator<String> it = parameters.iterator();
		while (it.hasNext()) {
			String current = it.next();
			if (current.equals("-r"))
				giocatori.add(new Player("Riccardo", puntiTotali));

			if (current.equals("-u"))
				giocatori.add(new Player("Ubaldo", puntiTotali));

			if (current.equals("-fe"))
				giocatori.add(new Player("Federico", puntiTotali));

			if (current.equals("-a"))
				giocatori.add(new Player("Arman", puntiTotali));

			if (current.equals("-fr"))
				giocatori.add(new Player("Francesca", puntiTotali));

		}
	}

}
