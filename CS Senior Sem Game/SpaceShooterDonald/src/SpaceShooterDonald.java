/***********************************************************************************************
* Evan Donald																				   *
* SpaceShooter                                                                                 * 
* Senior Seminar                                                                               *
* November 17, 2018                                                                            *                                                                                           
*                                                                                              *                                                                                                                                                                                                                                                                                    
***********************************************************************************************/

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SpaceShooterDonald implements Runnable 
{
	
	//Declaring the JFrame, Canvas, Thread, BufferStrategy, Graphics, and Images
	//that will be used later in the project
	private JFrame frame;
	private Canvas canvas;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics g;
	private Image ship;
	private Image alien;
	private Image shot;
	private Image enemyshot;
	
	//Creating a private User
	private User user;
	
	//Initializing the starting point for a player when the game begins
	int px = 400, py = 750;
	int ex = 0, ey = 0;
	
	//Creating an array for shots and declaring a variable to keep track of the shots
	//and declaring a boolean to keep track if a bullet is shot
	Shot[] shots;
	enemyShot[] enemyshots;
	byte shotNumber = 0;
	byte enemyshotNumber = 0;
	boolean shoot = false;
	boolean enemyshoot = false;
	
	//number of enemies killed
	int enemyNum = 0;
	
	//set enemy speed and enemy shots to 0
	int enemySpeed = 0;
	int enemyshotcounter = 0;
	
	//Creating an array for the enemies
	NPC[] npcs;
	
	//Random number to be used for spawning of enemies
	//and declaring an int to keep the players scores
	Random rand;
	int score = 0;
	int highScore = 0;
	int wave = 1;
	
	
	
	public SpaceShooterDonald() 
	{
		
		//Random number to be used later in code
		rand = new Random();
		//Making array for shots an array of 250 elements
		shots = new Shot[250];
		enemyshots = new enemyShot[250];
		//Making array for enemies length 10
		npcs = new NPC[10];
		//Creating the user
		user = new User();
		
		
		//Frame code
		//Name the frame Donald Shooter
		frame = new JFrame("Donald Shooter");
		//Set size of the frame to 800x800
		frame.setSize(800,800);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		canvas = new Canvas();
		//Size code
		canvas.setPreferredSize(new Dimension(800,800));
		canvas.setMaximumSize(new Dimension(800,800));
		canvas.setMinimumSize(new Dimension(800,800));
		frame.add(canvas);
		frame.pack();
		frame.addKeyListener(user);
		
		//Loading sprites into variables
		try 
		{
			ship = ImageIO.read(getClass().getResourceAsStream("/ship.gif"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			alien = ImageIO.read(getClass().getResourceAsStream("/alien.gif"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			shot = ImageIO.read(getClass().getResourceAsStream("/shot.gif"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			enemyshot = ImageIO.read(getClass().getResourceAsStream("/enemyshot.gif"));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		//for loop to spawn enemies in a range between -20 to 800 for x values
		//and above the 200 y value
		for(int i = 0; i < npcs.length; i++) 
		{
			npcs[i] = new NPC(rand.nextInt(820)-20, rand.nextInt(200), true);
		}
		
	}
	
	//Method to make thread start running
	public synchronized void start() 
	{
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() 
	{
		
		//Code for one iteration of time
		long now, lastTime = System.nanoTime();
		double delta = 0, nsPerTick = 1000000000/60;
		while (true) 
		{
			
			now = System.nanoTime();
			delta += (now-lastTime)/nsPerTick;
			lastTime = now;
			if(delta >= 1) 
			{
				tick();
				render();
				delta--;
			}
		}
	}
	
	//Method for everything that happens in one tick (unit of time)
	public void tick() 
	{
		//calls for user tick
		user.tick();
		
		//for each enemy in array call for enemy tick
		for(NPC npc:npcs) 
		{
			npc.tick();
		}
		
		//if a shot is fired in the current unit of time
		if(shoot) 
		{
			//create bullet and place it in the correct place and increase shot number
			shots[shotNumber] = new Shot(px+10, py-20, true);
			shotNumber++;
			shoot = false;
		}
		
		//if an enemy shot is fired
		if(enemyshoot) 
		{
			//create bullet
			enemyshotNumber++;
			enemyshoot = false;
		}
		
		//Keeps array from going out of bounds and screen from containing too many shots
		if(shotNumber > 44) 
		{
			shotNumber = 0;
		}
		if(enemyshotNumber > 44) 
		{
			enemyshotNumber = 0;
		}
		
		//for every active shot in shots array
		for(int i = 0; i < shotNumber; i++) 
		{
			//if the shot is active
			if(shots[i] != null) 
			{
				//calls for a tick for each active shot
				shots[i].tick();
				//if shot is off of screen delete it
				if(shots[i].x > 800) 
				{
					shots[i] = null;
				}
			}
		}
		//for every active enemyshot in shots array
		for(int i = 0; i < enemyshotNumber; i++) 
		{
			//if the shot is active
			if(enemyshots[i] != null) 
			{
				//calls for a tick for each active shot
				enemyshots[i].tick();
				//if shot is off of screen delete it
				if(enemyshots[i].x > 800) 
				{
					enemyshots[i] = null;
				}
			}
		}
	}
	
	//method that renders images to screen
	public void render() 
	{
		//updates the buffer strategy to use graphics
		bs = canvas.getBufferStrategy();
		
		//if buffer strategy is null create buffer strategy and return
		if (bs == null) 
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		//set up graphics
		g = bs.getDrawGraphics();
		
		//create 800 by 800 square for game visuals
		g.clearRect(0, 0, 800, 800);
		
		//make square black
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		
		
		//render a player onto the screen
		g.drawImage(ship, px, py, null);
		
		//render shots to screen
		for(int i = 0; i < shotNumber; i++) 
		{
			if(shots[i] != null)
			{
				shots[i].render();
			}
		}
		//render enemy shots to screen
		for(int i = 0; i < enemyshotNumber; i++) 
		{
			if(enemyshots[i] != null)
			{
				enemyshots[i].render();
			}
		}
		
		//render enemies to screen
		for(NPC npc:npcs) 
		{
			npc.render();
		}
		
		//print score to screen in white
		g.setColor(Color.WHITE);
		//change font size
		Font current = g.getFont();
		Font newFont = current.deriveFont(current.getSize() * 1.4F);
		g.setFont(newFont);
		//and banner for scores
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, 800, 40);
		g.setColor(Color.WHITE);
		//print scores to display
		g.drawString("Score: " + score, 50, 25);
		g.drawString("High Score: " + highScore, 150, 25);
		g.drawString("Wave: " + wave, 700, 25);
		
		//show and dispose graphics
		bs.show();
		g.dispose();
		
	}
	
	//stops program when player is done playing
	public synchronized void stop() 
	{
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.exit(0);
	}

	//main method to start program
	public static void main(String[] args) 
	{
		SpaceShooterDonald s = new SpaceShooterDonald();
		s.start();
	}
	
	//class to control the user ship
	class User implements KeyListener 
	{
		//create array of booleans for each player movement direction
		boolean[] keys;
		boolean up, down, left, right;
		
		//makes keys array 250 elements long
		public User() 
		{
			keys = new boolean[250];
		}

		//user tick method
		public void tick() 
		{
			//depending on the key the user presses set booleans to true
			up = keys[KeyEvent.VK_UP];
			down = keys[KeyEvent.VK_DOWN];
			left = keys[KeyEvent.VK_LEFT];
			right = keys[KeyEvent.VK_RIGHT];
			
			//makes user go up by 3 units each tick if up key is pressed
			if(up) 
			{
				//keeps user from going off of screen
				if(!(py <= 0))
					py += -3;
			}
			//makes user go down by 3 units each tick if down key is pressed
			if(down) 
			{
				//keeps user from going off of screen
				if(!(py >= 780))
					py += 3;
			}
			//makes user go left by 3 units each tick if left key is pressed
			if(left) 
			{
				//keeps user from going off of screen
				if(!(px <= 0))
					px += -3;
			}
			//makes user go right by 3 units each tick if right key is pressed
			if(right) 
			{
				//keeps user from going off of screen
				if(!(px >= 780))
					px += 3;
			}
			
		}
		
		//gets the key pressed by user
		@Override
		public void keyPressed(KeyEvent e) 
		{
			keys[e.getKeyCode()] = true;
			
		}

		//used to tell when the user releases key
		@Override
		public void keyReleased(KeyEvent e) 
		{
			
			//if space is pressed
			if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			{
				//shoot is set to true
				shoot = true;
			}
			keys[e.getKeyCode()] = false;

		}

		//satisfies key listener
		@Override
		public void keyTyped(KeyEvent e) 
		{
		}
		
	}
	
	//class for a shot
	class Shot 
	{
		//create variables for x and y value for shot
		int x, y;
		//boolean to keep track of shots
		boolean run;
		
		public Shot(int x, int y, boolean b) 
		{
			//if shot is true set run to true
			run = b;
			//set x and y values of shot
			this.x = x;
			this.y = y;
		}
		
		//tick for a shot
		public void tick()
		{
			//if bullets x value exceeds 800 stop updating bullets location
			if(x > 800)
				run = false;
			//if run is true
			if(run) 
			{
				//move bullet up by for units per tick
				y -= 4;
			}
		}
		
		//render bullets to screen
		public void render() 
		{
			g.drawImage(shot, x, y, null);
		}
		
	}
	
	//class for a shot
	class enemyShot 
	{
		//create variables for x and y value for shot
		int x, y;
		//boolean to keep track of shots
		boolean run;
		
		public enemyShot(int x, int y, boolean b) 
		{
			//if shot is true set run to true
			run = b;
			//set x and y values of shot
			this.x = x;
			this.y = y;
		}
			
		//tick for a shot
		public void tick()
		{
			//if bullets x value exceeds 800 stop updating bullets location
			if(x > 800)
				run = false;
			//if run is true
			if(run) 
			{
				//move bullet up by for units per tick
				y += 4;
			}
		}
			
		//render bullets to screen
		public void render() 
		{
			g.drawImage(enemyshot, x, y, null);
		}
		
	}
	
	//class for enemies
	class NPC 
	{
		//create variables for enemies location
		int x, y;
		boolean run;
		
		//creates enemy
		public NPC(int x, int y, boolean b) 
		{
			//sets run to true or false
			run = b;
			//updates x and y value for enemy
			this.x = x;
			this.y = y;
		}
		
		//tick for enemy
		public void tick()
		{
			//set enemy speed to 1
			enemySpeed = 1;
			//update speed when a player kills 40 enemies
			if (enemyNum >= 40)
			{
				enemySpeed = 2;
				//update wave
				wave = 3;
			}
			//if run is true
			if(run) 
			{
				//move enemy towards user ship
				if(px>x)
					x += enemySpeed;
				if(px<x)
					x -= enemySpeed;
				if(py>y)
					y += enemySpeed;
				if(py<y)
					y -= enemySpeed;
				
				//if enemy touches user
				if(new Rectangle(x,y,20,20).intersects(new Rectangle(px,py,20,20))) 
				{
					//reset user location to beginning location and reset score
					px = 400;
					if (score > highScore)
					{
						highScore = score;
					}
					
					for(int i = 0; i < shots.length; i++)
					{
						shots[i] = null;
					}
					wave = 1;
					score = 0;
					py = 750;
					enemyNum = 0;
					//spawn all enemies
					for(int i = 0; i < npcs.length; i++) 
					{
						npcs[i] = new NPC(rand.nextInt(820)-20, rand.nextInt(200), true);
					}
				}
				
				for(int i = 0; i < enemyshotNumber; i++) 
				{
					if(new Rectangle(px,py,20,20).intersects(new Rectangle(enemyshots[i].x, enemyshots[i].y, 5, 5))) 
					{
						//reset user location to beginning location and reset score
						px = 400;
						if (score > highScore)
						{
							highScore = score;
						}
						wave = 1;
						score = 0;
						py = 750;
						enemyNum = 0;
						//spawn all enemies
						for(int j = 0; j < npcs.length; j++) 
						{
							npcs[j] = new NPC(rand.nextInt(820)-20, rand.nextInt(200), true);
						}
					}
				}
				
				
				//for each shot
				for(int i = 0; i < shotNumber; i++) 
				{
					//if shot is active
					if(shots[i] != null) 
					{
						//if shot hits enemy
						if(new Rectangle(x,y,20,20).intersects(new Rectangle(shots[i].x, shots[i].y, 5, 5))) 
						{
							//deactivate shot and respawn enemy
							run = false;
							shots[i] = null;
							score++;
							//stop respawning after 20 kills
							if (enemyNum < 20) 
							{
								respawn();
							}
							
							if (enemyNum >= 20)
							{
								spawnShooter();
								wave = 2;
							}
						}
					}
				}
			}
		}
		
		//respawn for enemy
		public void respawn() 
		{
			//respawn enemy in random location above 200 y and in between -20 and 800 x
			enemyNum++;
			//System.out.println(enemyNum);
			run = true;
			x = rand.nextInt(820)-20;
			y = rand.nextInt(200);
		}
		
		public void spawnShooter()
		{
			enemyNum++;
			x = rand.nextInt(820)-20;
			y = rand.nextInt(200);
			run = true;
			enemyshots[enemyshotNumber] = new enemyShot(x, y, true);
			enemyshoot = true;
		}
		
		//render enemy to screen
		public void render() 
		{
			if(run) 
			{
				g.drawImage(alien, x, y, null);
			}
		}
		
	}
	
	
	
	
	

}
