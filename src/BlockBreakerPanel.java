import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class BlockBreakerPanel extends JPanel implements KeyListener{
	
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate animate;
	int size = 25;
	BlockBreakerPanel()
	{
		paddle = new Block(225, 600, 150, 25, "paddle.jpg");
		for(int i=0; i<10; i++)
		{
			blocks.add(new Block((i*60+2), 0, 60, 25, "blue.png"));
		}
		for(int i=0; i<10; i++)
		{
			blocks.add(new Block((i*60+2), 25, 60, 25, "red.png"));
		}
		for(int i=0; i<10; i++)
		{
			blocks.add(new Block((i*60+2), 50, 60, 25, "green.png"));
		}
		for(int i=0; i<10; i++)
		{
			blocks.add(new Block((i*60+2), 75, 60, 25, "yellow.png"));
		}
		ball.add(new Block(288, 575, 25, 25, "ball.png"));
		
		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for(Block b : blocks)
		{
			b.draw(g, this);
		}
		for(Block ba : ball)
		{
			ba.draw(g, this);
		}
		
		for(Block p : powerup)
		{
			p.draw(g, this);
		}
		
		paddle.draw(g, this);
	}
	
	public void update()
	{
		for(Block p : powerup)
		{
			p.y +=1;
			if(p.intersects(paddle) && !p.destroyed)
			{
				p.destroyed =true ;
				ball.add(new Block(paddle.x+75, paddle.y-size, 25, 25, "ball.png" ));
			}
		}
		
		for(Block ba : ball)
		{
			ba.x +=ba.dx;
			ba.y +=ba.dy; 
			if(ba.x > (getWidth() - size) && ba.dx > 0 || ba.x < 0)
			{
				ba.dx *=-1 ;  		

			}
			if(ba.y < 0 || ba.intersects(paddle))
			{
				ba.dy *=-1 ;   
			}
			
			for(Block b : blocks)
			{
				if((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed )
				{
					ba.dx *= -1;
					b.destroyed = true;
					if(b.powerup)
					{
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
					}
						
				}
				else if(ba.intersects(b) && !b.destroyed)
				{
					b.destroyed = true;
					ba.dy *=-1;
					if(b.powerup)
					{
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
					}
				}
			}
			
			
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()== KeyEvent.VK_ENTER){
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
			
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width) ){
			paddle.x += 30;
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0){
			paddle.x -= 30;
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
