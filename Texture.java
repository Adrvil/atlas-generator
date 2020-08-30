import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Texture {

	private BufferedImage _atlas;
	private Graphics2D _graphics;
	private Node _root;
	private Map<String, Rectangle> _rectangleMap;
	
	public Texture(int width, int height) {
		_atlas = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		_graphics = _atlas.createGraphics();
		
		_root = new Node(0, 0, width, height);
		_rectangleMap = new TreeMap<String, Rectangle>();
	}
	
	public boolean addImage(BufferedImage image, String name, int padding) {
		Node node = _root.insert(image, padding);
		
		if(node == null) {
			return false;
		}
		
		_rectangleMap.put(name, node.getRect());
		_graphics.drawImage(image, null, node.getRect().x, node.getRect().y);
		
		return true;
	}
	
	public void write(String name, boolean border, boolean unitCoordinates, int width, int height) {
		try {
			ImageIO.write(_atlas, "png", new File(name + ".png"));
			
			JSONArray frames = new JSONArray();
			
			for(Map.Entry<String, Rectangle> e : _rectangleMap.entrySet()) {
				Rectangle rect = e.getValue();
				String keyVal = e.getKey();
				
				if(border) {
					rect.x++;
					rect.y++;
					rect.width = rect.width - 2;
					rect.height = rect.height - 2;
				}
				
				JSONObject frame = new JSONObject();
				frame.put("filename", keyVal);
				
				JSONArray u = new JSONArray();
				JSONArray v = new JSONArray();
				
				if(unitCoordinates) {
					u.add(rect.x/(float)width);
					u.add((rect.y + rect.height)/(float)height);
					
					v.add((rect.x + rect.width)/(float)width);
					v.add(rect.y/(float)height);
				} else {
					u.add(rect.x);
					u.add(rect.y + rect.height);
					
					v.add(rect.x + rect.width);
					v.add(rect.y);
				}
				
				frame.put("u", u);
				frame.put("v", v);
				
				frames.add(frame);
			}
			
			JSONObject jAtlas = new JSONObject();
			
			jAtlas.put("frames", frames);
			jAtlas.put("image", name + ".png");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String jString = gson.toJson(jAtlas);
			
			FileWriter file = new FileWriter(name + ".json");
			file.write(jString);
			
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
