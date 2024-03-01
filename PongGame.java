import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
public class PongGame extends JPanel implements MouseMotionListener {
	private Ball gameBall;
	private Paddle userPaddle, pcPaddle;
	static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
	private int userMouseY;
	public PongGame() {
		gameBall = new Ball(300, 200, 3, 3, 3, Color.YELLOW, 10);
		userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
		pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);
		userMouseY = 0;
		addMouseMotionListener(this);
	}
	public void paintComponent(Graphics g) {
		//draw background and set colors
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//draw ball
		gameBall.paint(g);
		
		//draw paddles
		userPaddle.paint(g);
		pcPaddle.paint(g);
	}
	public void gameLogic() {
		gameBall.moveBall();
		gameBall.bounceOffEdges(0, WINDOW_HEIGHT);
		userPaddle.moveTowards(userMouseY);
		//userPaddle.moveTowards(gameBall.getY());
		pcPaddle.moveTowards(gameBall.getY());
		if(pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)) {
			gameBall.reverseX();
		}
	}
	@Override
	public void mouseDragged(MouseEvent e ) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e ) {
		userMouseY = e.getY();
	}
}
