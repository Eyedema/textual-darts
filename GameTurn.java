import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameTurn{

	private static int idTurn;
	protected int numGioc;
	protected int puntiTotali;
	protected List<Player> giocatori = new ArrayList<Player>();
	private int currentPlayer = 0;
	private final int maxShots = 3;
	protected int turno = 0;

	public GameTurn() {
		super();
	}

	public void passaTurno() {
		if (turno < numGioc - 1) {
			turno++;
		} else {
			turno = 0;
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
				validShot(p, shot);
				checkWin();
			} else {
				if (p.getPoints() - shot < 0) {
					p.setPoints(tmpShot);
					currentTurn = maxShots;
				} else {
					validShot(p, shot);
					System.out.println("\n"+p.getName() + " e' a " + p.getPoints() + " punti.");
				}
			}
			currentTurn++;
		}
	}

	private void validShot(Player p, int shot) {
		p.setPoints(p.getPoints() - shot);
		p.addShot(shot);
	}

	public boolean checkShot(int shot) {
		for (int i = 0; i <= 20; i++) {
			if (shot == i || shot == i * 2 || shot == i * 3 || shot == 25 || shot == 50) {
				return true;
			}
		}
		return false;
	}
	
	public boolean checkWin() {
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
	

	private void media(){
		System.out.println("La media dei giocatori e' stata:\n");
		Iterator<Player> it2 = giocatori.iterator();
		while (it2.hasNext()) {
			Player current2 = it2.next();
			System.out.println(
					 current2.getName() + ": " + current2.getMedia());
		}
	}

}