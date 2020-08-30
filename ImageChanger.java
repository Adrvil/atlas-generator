import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageChanger {
	
	public BufferedImage addBorderToImage(BufferedImage original) throws IOException {
		int original_w = original.getWidth();
		int original_h = original.getHeight();
		
		BufferedImage altered = new BufferedImage(original_w + 2, original_h + 2, BufferedImage.TYPE_4BYTE_ABGR);
		
		Graphics2D g2 = altered.createGraphics();
		
		g2.setColor(new Color(original.getRGB(0, 0), true));
		g2.fillRect(0, 0, 1, 1);
		
		g2.setColor(new Color(original.getRGB(0, original_h - 1), true));
		g2.fillRect(0, original_h + 1, 1, 1);
		
		g2.setColor(new Color(original.getRGB(original_w - 1, original_h - 1), true));
		g2.fillRect(original_w + 1, original_h + 1, 1, 1);
		
		g2.setColor(new Color(original.getRGB(original_w - 1, 0), true));
		g2.fillRect(original_w + 1, 0, 1, 1);
		
		for(int i = 1; i < original_w + 1; i++) {
			g2.setColor(new Color(original.getRGB(i - 1, 0), true));
			g2.fillRect(i, 0, 1, 1);
			g2.setColor(new Color(original.getRGB(i - 1, original_h - 1), true));
			g2.fillRect(i, original_h + 1, 1, 1);
		}
		
		for(int i = 1; i < original_h + 1; i++) {
			g2.setColor(new Color(original.getRGB(0, i - 1), true));
			g2.fillRect(0, i, 1, 1);
			g2.setColor(new Color(original.getRGB(original_w - 1, i - 1), true));
			g2.fillRect(original_w + 1, i, 1, 1);
		}
		
		g2.drawImage(original, 1, 1, null);
		g2.dispose();
		
		return altered;
	}
	
}
