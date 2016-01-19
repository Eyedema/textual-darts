import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class Game extends GameTurn {
	private ASCIIArt art = new ASCIIArt();

	public Game() {
		art.draw("DARTS");
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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci i punti con cui iniziare: ");
		while (puntiTotali == 0) {
			try {
				puntiTotali = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException nfe) {
			}
		}
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
		Collections.sort(giocatori, new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {

				return Integer.compare(p1.getPoints(), p2.getPoints());
			}
		});
		for (int i = 0; i < giocatori.size(); i++) {
			giocatori.get(i).getInfo();
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
		Scanner scanner = new Scanner(System.in);
		System.out.println("Inserisci il numero di giocatori: ");
		while (numGioc == 0) {
			try {
				numGioc = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException nfe) {
			}
		}
	}

	private void inserisciNomiGiocatori() {
		Scanner scanner = new Scanner(System.in);
		int tmp = numGioc;
		while (tmp > 0) {
			System.out.println("Inserisci giocatore numero " + (numGioc - (tmp - 1)));
			String name = "";
			while (name.equals("")) {
				name = scanner.nextLine();
			}
			giocatori.add(new Player(name, puntiTotali));
			tmp--;
		}
	}

}
