package ActualJavaGame;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Sprite{
	protected int x, y; //X and Y are the location of the sprite
	int xdir, ydir; //These are the directions
	ImageIcon image; //The image
	boolean show = true;
	boolean chasesprize;
	//The default constructor
	public Sprite(){
		image = null;
		xdir = 0;
		ydir = 0;
		x = 0;
		y = 0;
	}
	
	//Overload Constructor
	public Sprite(String filename){
		setImage(filename);
		
		xdir = 0;
		ydir = 0;
		x = 0;
		y = 0;
	}
	public void setImage(String filename){
		try{
			this.image = new ImageIcon(getClass().getResource(filename));
		}
		catch(Exception e){
			image = null;
		}
	}
	//Constructor with coordinates
	public Sprite(int x, int y){
		this.image = null;
		this.x = x;
		this.y = y;
		this.xdir = 0;
		this.ydir = 0;
	}
	
	//sprite("Hello.png" 3, 10);
	public Sprite(String filename, int x, int y){
		this.image = new ImageIcon(getClass().getResource(filename));
		this.x = x;
		this.y = y;
		this.xdir = 0;
		this.ydir = 0;
	}
	
	//Left or Right
	public void setXDir(int xdir){
		this.xdir = xdir;
	}
	
	//Up and Down
	public void setYDir(int ydir){
		this.ydir = ydir;
	}
	
	//A setter for the X Variable
	public void setX(int x){
		this.x = x;
	}
	
	//A setter for the Y Variable
	public void setY(int y){
		this.y = y;
	}
	
	//Moves your character based on the X & Y position
	public void move(){
		x += xdir;
		y += ydir;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	/*
	 * returns the width of the image
	 * or 20 if there is no image
	 */
	
	public void chase(Sprite other){
		if(other.getX() < this.getX()){
			this.setXDir(-1);
		}
		else if(other.getX() > this.getX()){
			this.setXDir(1);
		}
		if(other.getY() < this.getY()){
			this.setYDir(-1);
		}
		else if(other.getY() > this.getY()){
			this.setYDir(1);
		}
	}
	
	
	public int getWidth(){
		if(image == null){
			return 20;
		}
		return image.getIconWidth();
	}
	
	/*
	 * returns the height of the image
	 * or 20 if there is no image
	 */
	
	public int getHeight(){
		if(image == null){
			return 20;
		}
		return image.getIconHeight();
	}
	
	public boolean isCollision(Sprite o){
		
		Rectangle you = new Rectangle(x, y, getWidth(), getHeight());
		Rectangle other = new Rectangle(o.getX(), o.getY(), o.getWidth(), o.getHeight());
		
		return you.intersects(other);
	}
	
	public void paint(Graphics g, JPanel panel){
		if(show){
			if(image == null){
				g.drawRect(x, y, 20, 20);
			}
			else{
				image.paintIcon(panel, g, x, y);
			}
		}
	}
}