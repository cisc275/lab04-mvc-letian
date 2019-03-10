import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.*;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    
    
    HashMap<Direction,BufferedImage[]> picsFile = new HashMap<>();
    
    private final int width = 500;
    private final int height = 300;
    private final int imageWidth = 165;
    private final int imageHeight = 165;
    
    private Direction direct;
    private int x;
    private int y;
    
    boolean initial = true;
    JFrame frame;
    
    
    public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	
	public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	
//    	try {
    		g.drawImage(picsFile.get(direct)[picNum], x, y, Color.gray, this);
//    	}catch(Exception e) {
//    		e.getStackTrace();
//    	}
    	
    	
	}
	
	public void update(int x, int y, Direction d) {
		
		this.x = x;
		this.y = y;
		this.direct = d;
		frame.repaint();
    	
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
    
    public View(){
    	
    	
    	HashMap<Direction,BufferedImage> orcMoves = createImage();
    	for(Direction key: orcMoves.keySet()) {
    		BufferedImage img = orcMoves.get(key); //read image
    		pics = new BufferedImage[10];
    		for(int i = 0; i < frameCount; i++) {
    			pics[i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		}
    		picsFile.put(key, pics);	
    	}
    	
    	frame = new JFrame();
    	frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	
    	
    	
    	
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
    
    private HashMap<Direction,BufferedImage> createImage(){
    	BufferedImage bufferedImage;
    	HashMap<Direction,BufferedImage> orcMoves = new HashMap<>();
    	
    	try {
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
    		orcMoves.put(Direction.SOUTHEAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_east.png"));
    		orcMoves.put(Direction.EAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_north.png"));
    		orcMoves.put(Direction.NORTH, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northeast.png"));
    		orcMoves.put(Direction.NORTHEAST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_northwest.png"));
    		orcMoves.put(Direction.NORTHWEST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_south.png"));
    		orcMoves.put(Direction.SOUTH, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southwest.png"));
    		orcMoves.put(Direction.SOUTHWEST, bufferedImage);
    		
    		bufferedImage = ImageIO.read(new File("images/orc/orc_forward_west.png"));
    		orcMoves.put(Direction.WEST, bufferedImage);
    		
    		return orcMoves;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
    
    
}