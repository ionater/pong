import javax.swing.*;
import java.awt.event.*;
public class Main {
	static JFrame f = new JFrame("Pong");
	public static void main(String[] args) {
		//exit program on close
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		//set size of window
		f.setSize(650, 495);
		
		//initialize Pong game
		PongGame game = new PongGame();
		
		//add game to frame
		f.add(game);
		
		//make window visible
		f.setVisible(true);
		
		//creating timer
		Timer timer = new Timer(33, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//game logic
				game.gameLogic();
				
				//repaint screen
				game.repaint();
			}
		});
		timer.start();
	}
}
