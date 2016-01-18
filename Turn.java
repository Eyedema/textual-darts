
public class Turn {
	private int id, firstShot, secondShot, thirdShot;

	public Turn(int idTurn) {
		id = idTurn;
	}

	public void shot(int shot, int value) {
		if (shot == 1)
			firstShot = value;
		if (shot == 2)
			secondShot = value;
		if (shot == 3)
			thirdShot = value;
	}
	
}
