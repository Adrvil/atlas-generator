import java.util.Comparator;

public class ImageNameComparator implements Comparator<ImageData>{

	@Override
	public int compare(ImageData image1, ImageData image2) {
		int area1 = image1.getImage().getWidth() * image1.getImage().getHeight();
		int area2 = image2.getImage().getWidth() * image2.getImage().getHeight();
		
		if(area1 != area2) {
			return area2 - area1;
		}else {
			return image1.getName().compareTo(image2.getName());
		}
	}
	
}
