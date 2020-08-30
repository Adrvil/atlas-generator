import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Node {
	
	private Rectangle rect;
	private Node child[];
	private BufferedImage image;
	
	public Node(int x, int y, int width, int height) {
		this.rect = new Rectangle(x, y, width, height);
		this.child = new Node[2];
		this.child[0] = null;
		this.child[1] = null;
		this.image = null;
	}
	
	private boolean isLeaf() {
		return child[0] == null && child[1] ==null;
	}
	
	public Node insert(BufferedImage image, int padding) {
		if(!isLeaf()) {
			Node newNode = child[0].insert(image, padding);
			
			if(newNode != null) {
				return newNode;
			}
			
			return child[1].insert(image, padding);
		}else {
			if(this.image != null) {
				return null;
			}
			
			if(image.getWidth() > rect.width || image.getHeight() > rect.height) {
				return null;
			}
			
			if(image.getWidth() == rect.width && image.getHeight() == rect.height) {
				this.image = image;
				return this;
			}
			
			int dw = rect.width - image.getWidth();
			int dh = rect.height - image.getHeight();
			
			if(dw > dh) {
				child[0] = new Node(rect.x, rect.y, image.getWidth(), rect.height);
				child[1] = new Node(rect.x + image.getWidth() + padding, rect.y, rect.width - image.getWidth() - padding, rect.height);
			}else {
				child[0] = new Node(rect.x, rect.y, rect.width, image.getHeight());
				child[1] = new Node(rect.x, rect.y + image.getHeight() + padding, rect.width, rect.height - image.getHeight() - padding);
			}
			
			return child[0].insert(image, padding);
		}
	}
	
	public Rectangle getRect() {
		return this.rect;
	}
}
