import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;

public class FileManager {

	private Set<ImageData> _imageSet = new TreeSet<ImageData>(new ImageNameComparator());
	private ImageChanger _imageChanger = new ImageChanger();
	
	public void run(String name, int width, int height, int padding, boolean border, boolean unitCoordinates, String path) {
		getImages(path, border);
		saveAtlas(name, width, height, padding, border, unitCoordinates);
	}
	
	public void getImages(String path, boolean border) {
		File file = new File(path);
		
		List<File> imageNameFiles = new ArrayList<File>();
		
		getImageFile(file, imageNameFiles);
		
		System.out.println("Gets: " + imageNameFiles.size() + " images");
		
		for(File f : imageNameFiles) {
			try {
				BufferedImage image = ImageIO.read(f);		
				String name = f.getPath().substring(f.getPath().lastIndexOf("\\") + 1, f.getPath().lastIndexOf("."));				
				
				if(border) {
					image = this._imageChanger.addBorderToImage(image);
				}
				
				_imageSet.add(new ImageData(image, name));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void saveAtlas(String name, int width, int height, int padding, boolean border, boolean unitCoordinates) {
		Texture texture = new Texture(width, height);
		
		int count = 0;
		
		for(ImageData id : _imageSet) {
			System.out.println("Adding " + id.getName() + " to atlas (" + (count + 1) + ")");
			
			if(!texture.addImage(id.getImage(), id.getName(), padding)) {
				System.out.println("Atlas is full, some textures will be missing");
				break;
			}
			
			count++;
		}
		
		texture.write(name, border, unitCoordinates, width, height);
		
		System.out.println("Sukces");
	}
	
	private void getImageFile(File file, List<File> imageNameFiles) {
		if(file.isDirectory()) {
			File[] files = file.listFiles(new ImageFilenameFilter());
			File[] dirs = file.listFiles(new DirectoryFileFilter());
			
			imageNameFiles.addAll(Arrays.asList(files));
			
			for(File d : dirs) {
				getImageFile(d, imageNameFiles);
			}
		}
	}
	
}

