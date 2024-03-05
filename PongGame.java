import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class PongGame extends JPanel implements MouseMotionListener {
	private Ball gameBall;
	private Paddle userPaddle, pcPaddle;
	static final int WINDOW_WIDTH = 640, WINDOW_HEIGHT = 480;
	private int userScore, pcScore;
	public int bounceCount = 0;
	private int userMouseY;
	private boolean gameOver = false;
	
	
	
	public PongGame() {
		gameBall = new Ball(300, 200, 3, 3, 3, Color.YELLOW, 10);
		userPaddle = new Paddle(10, 200, 75, 3, Color.BLUE);
		pcPaddle = new Paddle(610, 200, 75, 3, Color.RED);
		userScore = 0; pcScore = 4;
		userMouseY = 0;
		
		//listen for motion events on this object
		addMouseMotionListener(this);
	}
	
	public void playPaddleSFX()
	{
		try
		{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("PaddleHit.wav"));
		Clip Paddle = AudioSystem.getClip();
		Paddle.open(audioInput);
		Paddle.start();
		}
		catch (Exception e)
		{
			
			System.out.println(e);
			
		}
	}
	
	
	
	public void playScoreSFX()
	{
		try
		{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("Score.wav"));
		Clip Paddle = AudioSystem.getClip();
		Paddle.open(audioInput);
		Paddle.start();
		}
		catch (Exception e)
		{
			
			System.out.println(e);
			
		}
	}
	
	public void playGameOverSFX()
	{
		try
		{
		AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("GameOver.wav"));
		Clip Paddle = AudioSystem.getClip();
		Paddle.open(audioInput);
		Paddle.start();
		}
		catch (Exception e)
		{
			
			System.out.println(e);
			
		}
	}
	
	
	public void paintComponent(Graphics g) {
		//draw background and set colors
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		if (gameOver == false)
		{
		//draw ball
		gameBall.paint(g);
		
		//draw paddles
		userPaddle.paint(g);
		pcPaddle.paint(g);
		
		//update score
		g.setColor(Color.WHITE);
		//the drawString method needs a String to print, and a location to print it at.
		g.drawString("Score - User [ " + userScore + " ]   PC [ " + pcScore + " ]", 230, 20   );
		}
		else
		{
			String s = "Player1";
			if (pcScore == 5)
				s = "PC";
			
			g.drawString(s + "WINS" , 230, 20   );
			
			//pause before exiting program
		    try{
		    	g.drawString(s + "WINS" , 230, 20   );
		        Thread.sleep(10000);
		        
		    } //catch error if Thread.sleep is interrupted.
		    catch(Exception e){
		        e.printStackTrace();
		    }
			
			System.exit(0);
		}
		
	}
	public void gameLogic() {
		gameBall.moveBall();
		gameBall.bounceOffEdges(0, WINDOW_HEIGHT);
		userPaddle.moveTowards(userMouseY);
		//userPaddle.moveTowards(gameBall.getY());
		pcPaddle.moveTowards(gameBall.getY());
		if(pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)) {
			playPaddleSFX();
			gameBall.reverseX();
		}
		
		// Increases speed if ball bounces 3 times.
		if (bounceCount == 3){
		    bounceCount = 0; 	
		    gameBall.increaseSpeed();

		}
		//adds score if ball touches your wall and resets game
		if(gameBall.getX() < 0){
		    pcScore++;
		    playScoreSFX();
		    reset();
		}
		else if(gameBall.getX() > WINDOW_WIDTH){
		    userScore++;
		    playScoreSFX();
		    reset();
		}
		
		
	}
	@Override
	public void mouseDragged(MouseEvent e ) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e ) {
		userMouseY = e.getY();
	}
	

	
	public void reset() {
		
		//pause before resetting (miliseconds)
	    try{

	        Thread.sleep(2000);
	        
	    } //catch error if Thread.sleep is interrupted.
	    catch(Exception e){
	        e.printStackTrace();
	    }
		
	    if ( userScore == 5 ||  pcScore == 5 )
	    {
	 
	    	gameOver = true;
	    	
	    }
		gameBall.setX(300);
	    gameBall.setY(200);
	    gameBall.setCx(3);
	    gameBall.setCy(3);
	    gameBall.setSpeed(3);
	    //bounceCount = 0;
	}
	
	
	
	
}
