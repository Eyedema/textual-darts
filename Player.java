
public class Player {

	private String name;
	private int points;
	private double media;
	private int turniGiocati;

	public Player(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points){
		this.points = points;
	}
	
	public void addShot(int shot){
		media += shot;
		turniGiocati++;
	}
	
	public double getMedia(){
		return media/turniGiocati;
	}
	
	public void getInfo(){
		System.out.println(getName()+" con "+getPoints()+" punti.\n");
	}
	
	public Player clone(){
		return this;
	}

}
