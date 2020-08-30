import java.awt.image.BufferedImage;

public class ImageData {
	
	private BufferedImage image;
	private String name;
		
	public ImageData(BufferedImage image, String name) {
		this.image = image;
		this.name = name;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public String getName() {
		return this.name;
	}
}
