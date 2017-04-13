import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Block extends Rectangle
{
	Image pic;
	int dx = 3, dy = -3;
	boolean destroyed = false;
	boolean powerup = false;
	Rectangle left, right;
	
	Block(int a, int c, int w, int h, String s)
	{
		x = a;
		y = c;
		width = w;
		height = h;
		pic = Toolkit.getDefaultToolkit().getImage(s);
		left = new Rectangle(a-1, c, 1, h);
		right = new Rectangle(a+w+1, c, 1, h);
	}
	
	public void draw(Graphics g, Component c){
		if(!destroyed)
			g.drawImage(pic, x, y, width, height, c);
		
	}

}
