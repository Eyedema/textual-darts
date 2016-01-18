import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ASCIIArt {

  public void draw(String s){

	int width = 100;
	int height = 30;

	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	g.setFont(new Font("SansSerif", Font.BOLD, 24));

	Graphics2D graphics = (Graphics2D) g;
	graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	graphics.drawString(s, 10, 20);

	System.out.println("\n\n");
	for (int y = 0; y < height; y++) {
		StringBuilder sb = new StringBuilder();
		for (int x = 0; x < width; x++) {

			sb.append(image.getRGB(x, y) == -16777216 ? " " : "&");
				
		}

		if (sb.toString().trim().isEmpty()) {
			continue;
		}

		System.out.println(sb);
	}

	System.out.println("\n\n");
  }

}