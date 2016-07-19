package ActualJavaGame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Display extends JPanel
							implements ActionListener{
	ArrayList<Sprite> objects = new ArrayList<Sprite>();
	private Sprite hero = new Sprite("link.png");
	private Sprite prize = new Sprite("triforce.png");
	private Sprite heart1 = new Sprite("Heart.fw.png");
	private Sprite heart2 = new Sprite("Heart.fw.png");
	private Sprite heart3 = new Sprite("Heart.fw.png");
	Timer gameLoop;
	Timer prizeLoop;
	Random gen = new Random();
	int tx, ty;
	int score = 0;
	int life = 3;
	int enemyScore = 0;
	int time = 0;
	public Display(){
		addKeyListener(new KeyPressing());
		//Sets the focus to the JPanel
		setFocusable(true);
		//Makes the movement smooth
		setDoubleBuffered(true);
		//Must be the same size as the window
		setSize(1675, 1010);
		prize.setX(660);
		prize.setY(660);
		
		hero.setX(500);
		hero.setY(500);
		
		heart1.setX(400);
		heart1.setY(70);
		
		heart2.setX(800);
		heart2.setY(70);
		
		heart3.setX(1200);
		heart3.setY(70);
		
		//Create the timer at 20 milliseconds
		gameLoop = new Timer(20, this);
		gameLoop.start();
		
		prizeLoop = new Timer(3000, new CollectableTimer());
		prizeLoop.start();
	}
	class CollectableTimer implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(prize.getX() == 2000){
				tx = gen.nextInt(1565);
				ty = gen.nextInt(900);
				prize.setX(tx);
				prize.setY(ty);
			}
			else if(prize.getX() < 2000){
				prize.setX(2000);
				prize.setY(2000);
			}
			if(hero.isCollision(prize)){
				prize.setX(2000);
				prize.setY(2000);
			}
			
		}
	}
	
	
	/*
	 * Timer event
	 */
	Random random = new Random();
	public void actionPerformed(ActionEvent e){
		hero.move();
		time++;
		if(random.nextInt(250-Math.round(time/10)) == 0){
			System.out.println("MAKE ONE!!!");
			Monster m = new Monster();
			m.setX(10);
			m.setY(10);
			objects.add(m);
		}
		//refreshes the screen
		repaint();
		for(int i = 0; i < objects.size(); i++){
			Sprite obj = objects.get(i);
			obj.move();
			if(!obj.chasesprize){
				chase(obj,hero);
			}else{
				if(prize.getX() != 2000){
					chase(obj,prize);
				}else{
					obj.setXDir(0);
					obj.setYDir(0);
				}
			}
			if(obj.isCollision(hero)){
				if(obj.getY()+obj.getHeight() > hero.getY()+30){
					life--;
					System.out.println((obj.getY()+obj.getHeight()) + " " + hero.getHeight()+30);
				}
				objects.remove(i);
				i--;
			}
			if(obj.isCollision(prize)){
				enemyScore += 100;
				prize.setX(2000);
				prize.setY(2000);
			}
		}
		if(life == 0){
			gameLoop.stop();
			prizeLoop.stop();
			JOptionPane.showMessageDialog(null, "You were killed!");
		}
		
		if(hero.isCollision(prize)){
			score += 100;
			prize.setX(2000);
			prize.setY(2000);
		}
		
		if(score < enemyScore){
			gameLoop.stop();
			prizeLoop.stop();
			JOptionPane.showMessageDialog(null, "The Enemies Won!");
		}
		
		if(life == 2){
			heart1.show=false;
		}
		if(life == 1){
			heart2.show=false;
		}
		if(life == 0){
			heart3.show=false;
		}
		//if(life == 2){
		//	heart1.equals(false);
		//}
	}
	public void chase(Sprite a, Sprite b){//A chases b
		if(b.getY() < a.getY()){
			a.setYDir(-4);
		}
		else if(b.getY() > a.getY()){
			a.setYDir(4);
		}
		if(b.getX() < a.getX()){
			a.setXDir(-4);
		}
		else if(b.getX() > a.getX()){
			a.setXDir(4);
		}
	}
	public void paintComponent(Graphics g){
		//erases the previous screen
		super.paintComponent(g);
		
		hero.paint(g, this);
		
		for(Sprite a : objects){
			a.paint(g, this);
		}
		
		heart1.paint(g, this);
		heart2.paint(g, this);
		heart3.paint(g, this);
		prize.paint(g, this);
		g.drawString("Score: " + score, 50, 50);
		g.drawString("Lives " + life, 150, 50);
		g.drawString("Enemy's Score: " + enemyScore, 250, 50);
	}
	
	public class KeyPressing extends KeyAdapter{
		/*Event that occurs when the user presses a key
		 * Used to move the hero
		 */
		public void keyPressed(KeyEvent e){
			switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				hero.setXDir(10);
			break;
			case KeyEvent.VK_LEFT:
				hero.setXDir(-10);
			break;
			case KeyEvent.VK_UP:
				hero.setYDir(-10);
			break;
			case KeyEvent.VK_DOWN:
				hero.setYDir(10);
			break;
		}
		repaint();
	}
	/*Event that occurs when the user releases a key
	 * Used to stop the hero
	 */
	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_RIGHT:
				hero.setXDir(0);
			break;
			case KeyEvent.VK_LEFT:
				hero.setXDir(0);
			break;
			case KeyEvent.VK_UP:
				hero.setYDir(0);
			break;
			case KeyEvent.VK_DOWN:
				hero.setYDir(0);
			break;
			}
		}
	}
	
}