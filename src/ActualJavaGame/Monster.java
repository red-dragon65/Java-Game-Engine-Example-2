package ActualJavaGame;

import java.util.Random;

import javax.swing.ImageIcon;

public class Monster extends Sprite{
	Random rand = new Random();
	
	public Monster(){
		super();
		chasesprize = rand.nextBoolean();
		String fname;
		if(chasesprize){
			fname = "red_chu.fw.png";
		}else{
			fname = "red_chu_chu.fw.png";
		}
		try{
			this.image = new ImageIcon(getClass().getResource(fname));
		}
		catch(Exception e){
			image = null;
		}
	}
}