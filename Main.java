import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> s = Arrays.asList(args);
		if (s.isEmpty()) {
			Game g = new Game();
		} else {
			FriendsGame f = new FriendsGame(s);
		}
	}
}
