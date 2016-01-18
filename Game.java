import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Game {
	private static int idTurn;
	private int numGioc;
	private int puntiTotali;
	private List<Player> giocatori = new ArrayList<Player>();
	private int currentPlayer = 0;
	private final int maxShots = 3;
	private int turno = 0;
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
		while(it.hasNext()){
			Player current = it.next();
			current.setPoints(puntiTotali);
		}
	}

	private void summarize() {
		System.out.println("\n\n");
		System.out.println("\n%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("Situazione attuale:\n");
	    Comparator<Player> sorted = (e1, e2) -> Integer.compare(
	            e1.getPoints(), e2.getPoints());

	    giocatori.stream().sorted(sorted)
	            .forEach(e -> e.getInfo());
		System.out.println("%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*%*");
		System.out.println("\n\n");
	}

	public void passaTurno() {
		if (turno < numGioc - 1) {
			turno++;
		} else {
			turno = 0;
		}
	}
	
	private void media(){
		System.out.println("La media dei giocatori e' stata:\n");
		Iterator<Player> it2 = giocatori.iterator();
		while (it2.hasNext()) {
			Player current2 = it2.next();
			System.out.println(
					 current2.getName() + ": " + current2.getMedia());
		}
	}

	private boolean checkWin() {
		Iterator<Player> it = giocatori.iterator();
		while (it.hasNext()) {
			Player current = it.next();
			if (current.getPoints() == 0) {
				System.out.println(current.getName() + " ha vinto!\n");
				media();
				System.exit(0);
				return true;
			}
		}
		return false;
	}

	private void Welcome() {
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

	public void gioca(Player p) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("E' il turno di " + p.getName() + ", sei a " + p.getPoints() + " punti.");
		int tmpShot = p.getPoints();
		int currentTurn = 1;
		while (currentTurn <= maxShots) {
			System.out.println("Lancio numero " + currentTurn + ":");
			int shot = -1;
			while (shot == -1) {
				try {
					String s = scanner.nextLine();
					shot = Integer.parseInt(s);
				} catch (NumberFormatException nfe) {
				}
			}
			if (!checkShot(shot)) {
				System.out.println("\nTiro non valido!");
				currentTurn--;
			} else if (p.getPoints() - shot == 0) {
				p.setPoints(p.getPoints() - shot);
				p.addShot(shot);
				checkWin();
			} else {
				if (p.getPoints() - shot < 0) {
					p.setPoints(tmpShot);
					currentTurn = 3;
				} else {
					p.setPoints(p.getPoints() - shot);
					p.addShot(shot);
					System.out.println("\n"+p.getName() + " e' a " + p.getPoints() + " punti.");
				}
			}
			currentTurn++;
		}
	}

	public boolean checkShot(int shot) {
		for (int i = 0; i <= 20; i++) {
			if (shot == i || shot == i * 2 || shot == i * 3 || shot == 25 || shot == 50) {
				return true;
			}
		}
		return false;
	}

}
