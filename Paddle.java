import java.awt.*;
public class Paddle {
	private int height, x, y, speed;
	private Color color;
	static final int PADDLE_WIDTH = 15;
	public Paddle(int x, int y, int height, int speed, Color color) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.speed = speed;
		this.color = color;
	}
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, PADDLE_WIDTH, height);
	}
	public void moveTowards(int moveToY) {
		int centerY = y + height / 2;
		if(Math.abs(centerY - moveToY) > speed) {
			if(centerY > moveToY) {
				y -= speed;
			}
			if(centerY < moveToY) {
				y += speed;
			}
		}
	}
	public boolean checkCollision(Ball b) {
		int rightX = x + PADDLE_WIDTH;
		int bottomY = y + height;
		if(b.getX() > x && b.getX() < rightX) {
			if(b.getY() > y && b.getY() < bottomY) {
				return true;
			}
		}
		return false;
	}
}
