import java.io.File;
import java.io.FilenameFilter;

public class ImageFilenameFilter implements FilenameFilter{

	@Override
	public boolean accept(File file, String name) {
		if(name.toLowerCase().endsWith(".png")) {
			return true;
		} else if(name.toLowerCase().endsWith(".jpg")) {
			return true;
		} else if(name.toLowerCase().endsWith(".jpeg")) {
			return true;
		}
		return false;
	}	
}
