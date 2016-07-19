package ActualJavaGame;


import javax.swing.JFrame;

public class Window extends JFrame{
	
	public Window(){
		//Add the game to your window
		add(new Display());
		//ends the program when the window is closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1675, 1010);
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String args[]){
		new Window();
	}
}
