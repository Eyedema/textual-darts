
public class Turn {
	private int points;
	private Player p;

	public Turn(int points, Player p){
		this.points = points;
		this.p = p;
	}

	public int getPoints(){
		return points;
	}
	
	public Player getPlayer(){
		return p;
	}
	
}
 