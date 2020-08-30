public class AtlasGenerator {
	
	public static void main(String[] args) {
		
		if(args.length < 2) {
			System.out.println("Texture Atlas Generator.");
			System.out.println("\tUsage: AtlasGenerator <name> <width> <height> <padding> <border> <unitCoordinates> <directory>");
			System.out.println("\t\t<padding>: Padding between images in the final texture atlas.");
			System.out.println("\t\t<border>: Every image will have a 1 pixel border");
			System.out.println("\t\t<unitCoordinates>: Coordinates will be written to atlas txt file in 0..1 range instead of 0..width, 0..height range");
			System.out.println("\tExample: AtlasGenerator atlas 2048 2048 5 1 1 images");
		}
		
		FileManager fm = new FileManager();
		fm.run(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]) != 0, Integer.parseInt(args[5]) != 0, args[6]);
	}

}
