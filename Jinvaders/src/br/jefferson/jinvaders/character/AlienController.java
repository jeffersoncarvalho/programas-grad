/*
 * Created on 30/07/2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package br.jefferson.jinvaders.character;

import br.jefferson.jinvaders.gui.Painter;
import br.jefferson.jinvaders.util.SingletonObject;

/**
 * @author Usuario
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AlienController {

	public static final int DOWN = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	private int moveDownCounter = 0;
	private Alien aliens[] = new Alien[25];
	private int direction = AlienController.RIGHT;
	private int lastDirection = AlienController.RIGHT;
	private boolean conquer = false;
	private SpaceCraft sc;
	private int jumps = 0;
	private int sleep = 500;
	private static int deads = 1;
	
	/**
	 * 
	 */
	public AlienController(SpaceCraft sc) {

		this.sc = sc;
		for(int i=0;i<10;i++)
		{
			int y =  (int)(Math.random()*3);
			Alien alien = new Alien(this,false,y);
			alien.setX(i*30+15);
			alien.setY(30);
			this.aliens[i] = alien;
		}
		
		for(int i=10;i<20;i++)
		{
			int y =  (int)(Math.random()*3);
			Alien alien = new Alien(this,false,y);
			alien.setX((i-10)*30+15);
			alien.setY(60);
			this.aliens[i] = alien;
		}
		
		for(int i=20;i<25;i++)
		{
			int y =  (int)(Math.random()*3);
			Alien alien = new Alien(this,false,y);
			alien.setX((i-20)*30+90);
			alien.setY(0);
			this.aliens[i] = alien;
		}
		
		//aliens[0].setCanShot(true);
		//aliens[0].shot(sc);
		this.moveAliens();
	}
	/**
	 * @return Returns the aliens.
	 */
	public Alien[] getAliens() {
		return aliens;
	}
	/**
	 * @param aliens The aliens to set.
	 */
	public void setAliens(Alien[] aliens) {
		this.aliens = aliens;
	}
	
	public void moveAliens(){
		
		Thread t = new Thread(){ 
		public void run() {
			
			while(!conquer){
				
				if(AlienController.deads%3==0)
				{
					//System.out.println("pega!");
					if(sleep-30>=0)
						sleep -=30;
					AlienController.deads=AlienController.deads+1;
				}
				
				try {
					Thread.sleep(sleep);
					//vendo para onde tenho que mover
					for(int i = 0; i< aliens.length;i++)
					{
						Alien alien = (Alien)aliens[i];
						if(alien!=null)
						{
//							atirando
							int y =  (int)(Math.random()*150);
							if(y==2)
								alien.shot(sc);
							//escolhendo o lado..
							if(alien.getY()>=330)
								conquer = true;
							if(direction == AlienController.DOWN && 400 - alien.getX() <= 30)
							{
								direction = AlienController.LEFT;
								break;
							}
							
							if(direction == AlienController.DOWN && alien.getX() <= 30)
							{
								direction = AlienController.RIGHT;
								break;
							}
							
							if((400 - alien.getX() <= 30 ||alien.getX() <= 10))
							{
								direction = AlienController.DOWN;
								jumps++;
								switch(jumps)
								{
									case 3:
										sleep = 400;
									break;
									case 6:
										sleep = 300;
									break;
									case 9:
										sleep = 200;
									break;
									case 12:
										sleep = 100;
									break;
									case 15:
										sleep = 50;
									break;
								}
								break;
							}
						}else{
							//quantos Morreram??
						}
					}
					
					
					//movendo
					if(!conquer)
					for(int i = 0; i< aliens.length;i++)
					{
						Alien alien = (Alien)aliens[i];
						if(alien!=null)
						{
							
							if(direction==AlienController.RIGHT)
								alien.moveRight();
							else
								if(direction==AlienController.LEFT)
									alien.moveLeft();
								else
								{
									alien.moveDown();
								}
						}
							
					}
					 
					SingletonObject.repaint();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				Painter.blink = !Painter.blink;
			}
			
		}};
		
		t.start();
		t.setPriority(Thread.MAX_PRIORITY);
	}
	/**
	 * @return Returns the direction.
	 */
	public int getDirection() {
		return direction;
	}
	/**
	 * @param direction The direction to set.
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
	/**
	 * @return Returns the deads.
	 */
	public static int getDeads() {
		return deads;
	}
	/**
	 * @param deads The deads to set.
	 */
	public static void setDeads(int deads) {
		AlienController.deads = deads;
	}
}
