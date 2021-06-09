import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

class Shot {

	private int x;
	private int y;
	private boolean active;

	public Shot(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		active=true;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public void setActive(boolean active)
	{
		this.active=active;
	}
	public boolean getActive()
	{
		return active;
	}
}

public class Game extends JPanel implements KeyListener, ActionListener {
	Tree tree=new Tree();
	File file;
	File fileTree;
	JLabel lblPoint;
	JLabel lblPowerUp;
	Random rnd=new Random();
	Random rndHowMany=new Random();
	Queue q1=new Queue(1000);
	Queue q2=new Queue(1000);
	Queue q3=new Queue(1000);
	Queue q4=new Queue(1000);
	ArrayList<Queue> queuearry=new ArrayList<Queue>();
	Timer timer = new Timer(4, this);
	private int time_passed = 0;
	private int shot_wasted = 0;
	private BufferedImage image;
	private BufferedImage imageEnmy;
	private BufferedImage imageSEnmy;
	//private BufferedImage imagePlanet;
	private BufferedImage imageEnmy1;
	private BufferedImage imageEnmy2;
	private BufferedImage imageSEnmy1;
	private BufferedImage imageSEnmy2;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private int shotdirY = 5;
	/*private int ballY = 0;
	private int balldirY = 1;*/
	private int shipX = 70;
	private int shipdirX = 200;
	JPanel pnl;
	boolean b=true;
	//public int i=0;
	boolean forControl=true;
	boolean enabled=true;
	//Powerup
	boolean isActive=false;
	public int sayac=0;
	int whichPath;
	int point;
	long imgCounter=0;
	ShotPowerUp powerUp=new ShotPowerUp();

	public Game() {	
		try {
   			lblPoint=new JLabel("Point:"+point);
   			lblPoint.setFont(new Font("Serif", Font.BOLD, 20));
			lblPoint.setForeground(Color.RED);
			add(lblPoint);
			
			lblPowerUp=new JLabel("PowerUp:Passive");
			lblPowerUp.setFont(new Font("Serif", Font.BOLD, 20));
			lblPowerUp.setForeground(Color.RED);
			add(lblPowerUp);
			
			Enemy e1=new Enemy();
			enemies.add(e1);
			image = ImageIO.read(new FileImageInputStream(new File("svJ1Jp6.png")));
			/*
			imageEnmy=ImageIO.read(new FileImageInputStream(new File("Enemy.png")));
			imagePlanet=ImageIO.read(new FileImageInputStream(new File("Planet.png")));
			*/
			
			imageEnmy=ImageIO.read(new FileImageInputStream(new File("rocket.png")));
			imageSEnmy=ImageIO.read(new FileImageInputStream(new File("strongRocket.png")));
			
			imageEnmy1=ImageIO.read(new FileImageInputStream(new File("rocket.png")));
			imageEnmy2=ImageIO.read(new FileImageInputStream(new File("rocket2.png")));
			imageSEnmy1=ImageIO.read(new FileImageInputStream(new File("strongRocket.png")));
			imageSEnmy2=ImageIO.read(new FileImageInputStream(new File("strongRocket2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fileTree=new File("Tree.dat");
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileTree);
			ObjectInputStream ois=new ObjectInputStream(fis);
			tree=(Tree)ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(" ");
			
		}
		
		setBackground(Color.BLACK);
		
		queuearry.add(q1);
		queuearry.add(q2);
		queuearry.add(q3);
		queuearry.add(q4);
		timer.start();
	}
	
	
	public boolean control() {
		for(Queue q:queuearry)
		{
			int counter=q.getCount();
			for (int i = q.front; i < q.getSize(); i++) {
				if(counter!=0)
				{
					Shot shot=q.queueArray[i];
					if(shot!=null)
					{
							for(Enemy enemy:enemies)
							{
								if(new Rectangle(shot.getX(), shot.getY(), 10, 20).intersects(new Rectangle(enemy.getX(), enemy.getY(), 50, 50))) {
									if(enemy.getEnemyType()==1)
									{
										if(enemy.health==2)
										{
											enemy.health-=1;
											shot.setActive(false);
											q.deleteQueue();
											return false;
										}
										else if(enemy.health==1)
										{
											Random rnd=new Random();
											if(rnd.nextInt(2)==0 && !powerUp.isActive())
											{
												powerUp.setActive(true);
												lblPowerUp.setText("PowerUp:Active");
												System.out.println("Power up geldi");
											}
											forControl=true;
											point+=2;
											lblPoint.setText("Point:"+point);
											shot.setActive(false);
											enemy.setActive(false);
											whichPath=enemy.getPath();
											enemies.remove(enemy);
											q.deleteQueue();
											return true;
										}
									
									}
									else
									{
									forControl=true;
									point++;
									lblPoint.setText("Point:"+point);
									shot.setActive(false);
									enemy.setActive(false);
									whichPath=enemy.getPath();
									enemies.remove(enemy);
									q.deleteQueue();
									return true;
									}
								} 
							
			 				}
				}	
				//}
				forControl=false;
				counter--;
				}
			}
			
			for (int i = 0; i <= q.rear; i++) 
			{
				if(counter!=0)
				{
					Shot shot=q.queueArray[i];
					if(shot!=null)
					{
							for(Enemy enemy:enemies)
							{
								if(new Rectangle(shot.getX(), shot.getY(), 10, 20).intersects(new Rectangle(enemy.getX(), enemy.getY(), 50, 50))) {
									if(enemy.getEnemyType()==1)
									{
										if(enemy.health==2)
										{
											enemy.health-=1;
											shot.setActive(false);
											q.deleteQueue();
											return false;
										}
										else if(enemy.health==1)
										{
											Random rnd=new Random();
											if(rnd.nextInt(2)==0 && !powerUp.isActive())
											{
												powerUp.setActive(true);
												lblPowerUp.setText("PowerUp:Active");
												System.out.println("Power up geldi");
											}
											forControl=true;
											point++;
											lblPoint.setText("Point:"+point);
											shot.setActive(false);
											enemy.setActive(false);
											whichPath=enemy.getPath();
											enemies.remove(enemy);
											q.deleteQueue();
											return true;
										}
									
									}
									else
									{
									forControl=true;
									point++;
									lblPoint.setText("Point:"+point);
									shot.setActive(false);
									enemy.setActive(false);
									whichPath=enemy.getPath();
									enemies.remove(enemy);
									q.deleteQueue();
									return true;
									}
								} 
								
							}
				}	
				//}
				forControl=false;
				counter--;

				}
			}

		}
				return false;	
	}
		
	public boolean control2()
	{
		for(Enemy enemy:enemies)
		{
			if(enemy.getY()>=950-(image.getHeight()/2))
			{
				return true;
			}
		}
		
		return false;
	}

	public void DrawLine()
	{
		pnl=new JPanel();
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setStroke(new BasicStroke(2));
		g2.drawLine(200, 0, 200, 1600);
		g2.drawLine(400, 0, 400, 1600);
		g2.drawLine(600, 0, 600, 1600);
		
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		time_passed += 5;
		if(powerUp.isActive())
		{
			System.out.println(powerUp.getDuration());
			powerUp.setDuration(powerUp.getDuration()+1);
		}
		if(powerUp.getDuration()>=690)
		{
			powerUp.setActive(false);
			lblPowerUp.setText("PowerUp:Passive");
			powerUp.setDuration(0);
			System.out.println("süre sýfýrlandý");
		}

		g.setColor(Color.RED);
		/*g.fillOval(80, ballY, 20, 20);
		g.fillOval(280, ballY, 20, 20);
		g.fillOval(480, ballY, 20, 20);
		g.fillOval(680, ballY, 20, 20);*/
		for (Enemy enemy : enemies) {
			if(enemy.getActive() && enemy.getEnemyType()==0)
			{
			//g.drawImage(imageEnmy, enemy.getX(), enemy.getY(), imageEnmy.getWidth()/20,imageEnmy.getHeight()/20,this);
			g.drawImage(imageEnmy, enemy.getX(), enemy.getY(), imageEnmy.getWidth()/10,imageEnmy.getHeight()/10,this);
			if(imgCounter%60<30)
			{
				try {
					imageEnmy=imageEnmy1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			else
			{
				try {
					imageEnmy=imageEnmy2;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			//Alttakiler can barý için
			g.setColor(Color.GREEN);
			g.fillRect(enemy.getX(), enemy.getY()-10, 30, 10);
			}
			else if(enemy.getActive()&&enemy.getEnemyType()==1)
			{
				if(imgCounter%60<30)
				{
					imageSEnmy=imageSEnmy1;
				}
				else
				{
					imageSEnmy=imageSEnmy2;
				}
				if(enemy.health==2)
				{
					
					g.drawImage(imageSEnmy, enemy.getX()-20, enemy.getY(), imageSEnmy.getWidth()/5,imageSEnmy.getHeight()/5,this);
					g.setColor(Color.GREEN);
					g.fillRect(enemy.getX()+10, enemy.getY()-10, 30, 10);
				}
				else if(enemy.health==1)
				{
					g.drawImage(imageSEnmy, enemy.getX()-20, enemy.getY(), imageSEnmy.getWidth()/5,imageSEnmy.getHeight()/5,this);
					g.setColor(Color.GREEN);
					g.fillRect(enemy.getX()+10, enemy.getY()-10, 15, 10);
					g.setColor(Color.RED);
					g.fillRect(enemy.getX()+25, enemy.getY()-10, 15, 10);
				}
			
			}
			imgCounter++;	
		}
		
		
		g.drawImage(image, shipX, 950, image.getWidth() / 5, image.getHeight() / 5, this);

		/*for (int a=0;a<=i;a++) {
			Shot shot=q1.queueArray[a];*/
		
		for (Queue queue : queuearry) {
			Shot shot=queue.getFront();
			if(shot !=null)
				if (shot.getY() < 10)
					queue.deleteQueue();
		}
			
		//}
		g.setColor(Color.BLUE);
		
		for (Queue queue1 : queuearry) {
			int count=queue1.getCount();
			for (int a=queue1.front; a<queue1.getSize();a++) {
				if(count!=0)
				{
					Shot shots=queue1.queueArray[a];
					if(shots!=null && shots.getActive()==true)
					g.fillOval(shots.getX(), shots.getY(), 10, 20);
					count--;
				}
			}
			for (int a=0; a<=queue1.rear;a++)
			{
				if(count!=0)
				{
					Shot shots=queue1.queueArray[a];
					if(shots!=null && shots.getActive()==true)
					g.fillOval(shots.getX(), shots.getY(), 10, 20);
					count--;
				}
			}
		}
		
		if (control()==true && forControl!=false ) {
			/*timer.stop();
			String message = "Kazandýnýz!\nHarcanan ateþ : " + shot_wasted + "\nGeçen süre : " + (time_passed / 1000.0) + " saniye";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);*/
			int rndEnemyNumber=(rndHowMany.nextInt(2))+1;
			int path=(rnd.nextInt(4))+1;
			if(path!=whichPath)
			{
			for (int i = 0; i < rndEnemyNumber; i++) {
				path=(path%4)+1;
				enemies.add(new Enemy(path,5));
			}
			}
			else
				enemies.add(new Enemy(path,5,2,1));
		}
		if(control2())
		{
			file=new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ateþ : " + shot_wasted + "\nGeçen süre : " + (time_passed / 1000.0) + " saniye";
			int run=JOptionPane.showConfirmDialog(this, message,"Game Over",JOptionPane.YES_NO_OPTION);
			
				if(run==JOptionPane.YES_OPTION)
				{
					GameScreen g1=new GameScreen("BauSpace");
					g1.setVisible(true);
				}
				
		    if(run==JOptionPane.NO_OPTION)
		    {
		    	FileWriter fw;
				try {
					fileTree=new File("Tree.dat");
					tree.insert(point, shot_wasted, time_passed/1000.0);
					FileOutputStream fos=new FileOutputStream(fileTree);
					ObjectOutputStream oos=new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();
					
					fw = new FileWriter(file,true);
					fw.write(point+"	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:"+point);
					fw.flush();
					fw.close();
					Veriler v=new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   	
		    }
		    if(run==JOptionPane.CLOSED_OPTION)
		    {
		    	System.exit(0);
		    }
					
		}	
		}

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			whenPerformed();
		} catch (Exception e2) {
			// TODO: handle exception
			
		} 
	if(b)
	{
		for (Enemy enemy : enemies) {
			enemy.setY(enemy.getY()+enemy.getDirY());
		}
		
	}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			if (shipX <= 70)
				shipX = 70;
			else
				shipX -= shipdirX;
			enabled=true;
			sayac=0;
		}
		else if (c == KeyEvent.VK_RIGHT) {
			if (shipX >= 600)
				shipX = 670;
			else
				shipX += shipdirX;
			enabled=true;
			sayac=0;
		}
		if(sayac<=3)
		{
		 if (c == KeyEvent.VK_X &&enabled==true) {
			/*q1.queueArray[i]=new Shot(shipX+15, 470);
			Shot shot=q1.queueArray[i];*/	
			 if(shipX==70)
			 {
				 q1.addQueue(new Shot(shipX+15,950), 1);
				System.out.println("Eklendi 1 ");
			 }
			 else if(shipX==270)
			 {
				 q2.addQueue(new Shot(shipX+15,950), 2);
				System.out.println("Eklendi 2 ");
			 }
			 else if(shipX==470)
			 {
				 q3.addQueue(new Shot(shipX+15,950), 3);
				System.out.println("Eklendi 3 ");
			 }
			 else if(shipX==670)
			 {
				 q4.addQueue(new Shot(shipX+15,950), 4);
				 System.out.println("Eklendi 4 ");
			 }
			 
			 shot_wasted++;
			 
			 if(!powerUp.isActive())
			 {
					sayac++;
					//i++; 
			 }
			 else
			 {
				
			 }
		}
		}
		else {
		sayac=0;
		enabled=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	public void whenPerformed()
	{
		for(Queue qu:queuearry) {
			int counter=qu.getCount();
			for (int i = qu.front; i < qu.getSize(); i++) {
				if(counter!=0)
				{
					qu.queueArray[i].setY(qu.queueArray[i].getY()-shotdirY);
					counter--;
				}
			}
			
			for (int i = 0; i <= qu.rear; i++) 
			{
				if(counter!=0)
				{
					qu.queueArray[i].setY(qu.queueArray[i].getY()-shotdirY);
					counter--;

				}
		}
	
		
}
	}
	
}
		
	
	


