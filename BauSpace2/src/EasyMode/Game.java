package EasyMode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {
	Tree tree = new Tree();
	File file;
	File fileTree;
	Random random = new Random();

	boolean isActive = false;
	Timer timer = new Timer(4, this);
	Timer enemyTimer = new Timer(10, this);
	Timer shotTimer = new Timer(10, this);
	int shotDelay = 0;

	int duration = 0;
	int temp = 0;
	
	JLabel powerUp;
	
	private int time_passed = 0;
	private int shot_wasted = 0;

	private BufferedImage ship;
	private BufferedImage enemy1;
	private BufferedImage enemy2;
	private BufferedImage enemy3;
	private BufferedImage enemy4;
	private BufferedImage enemy5;

	private BufferedImage enemy11;
	private BufferedImage enemy22;
	private BufferedImage enemy33;
	private BufferedImage enemy44;
	private BufferedImage enemy55;

	private BufferedImage meteor11;
	private BufferedImage meteor21;
	private BufferedImage meteor31;
	private BufferedImage meteor41;
	private BufferedImage meteor51;

	private BufferedImage meteor12;
	private BufferedImage meteor22;
	private BufferedImage meteor32;
	private BufferedImage meteor42;
	private BufferedImage meteor52;

	private BufferedImage meteor13;
	private BufferedImage meteor23;
	private BufferedImage meteor33;
	private BufferedImage meteor43;
	private BufferedImage meteor53;

	private BufferedImage meteor14;
	private BufferedImage meteor24;
	private BufferedImage meteor34;
	private BufferedImage meteor44;
	private BufferedImage meteor54;

	private ArrayList<Shot> shots1 = new ArrayList<Shot>();
	private ArrayList<Shot> shots2 = new ArrayList<Shot>();
	private ArrayList<Shot> shots3 = new ArrayList<Shot>();
	private ArrayList<Shot> shots4 = new ArrayList<Shot>();
	private ArrayList<Shot> shots5 = new ArrayList<Shot>();

	private ArrayList<Enemy> enemies1 = new ArrayList<Enemy>();
	private ArrayList<Enemy> enemies2 = new ArrayList<Enemy>();
	private ArrayList<Enemy> enemies3 = new ArrayList<Enemy>();
	private ArrayList<Enemy> enemies4 = new ArrayList<Enemy>();
	private ArrayList<Enemy> enemies5 = new ArrayList<Enemy>();

	private ArrayList<Rectangle> enemyHitBox = new ArrayList<Rectangle>();
	private ArrayList<Rectangle> shotHitBox = new ArrayList<Rectangle>();

	Queue q1 = new Queue(1000);
	Queue q2 = new Queue(1000);
	Queue q3 = new Queue(1000);
	Queue q4 = new Queue(1000);
	Queue q5 = new Queue(1000);

	Queue queuearray[] = { q1, q2, q3, q4, q5 };

	private int enemyX = 1700;
	private int enemydirX = 2;
	private int enemyX2 = 1700;

	private int shotdirX = 2;

	private int shipY = 0;
	private int shipdirY = 150;

	public int i = 0;
	int enemySpawn = 0;
	int n = 0;
	int point = 0;

	int an = 0;

	public Game() {
		
		powerUp = new JLabel("");
		powerUp.setFont(new Font("Serif", Font.BOLD, 20));
		powerUp.setForeground(Color.RED);
		add(powerUp);

		try {
			ship = ImageIO.read(new FileImageInputStream(new File("ship.png")));

			enemy1 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));
			enemy2 = ImageIO.read(new FileImageInputStream(new File("enemy2.png")));
			enemy3 = ImageIO.read(new FileImageInputStream(new File("enemy2.png")));
			enemy4 = ImageIO.read(new FileImageInputStream(new File("enemy2.png")));
			enemy5 = ImageIO.read(new FileImageInputStream(new File("enemy2.png")));

			enemy11 = ImageIO.read(new FileImageInputStream(new File("rocket2.png")));
			enemy22 = ImageIO.read(new FileImageInputStream(new File("enemy21.png")));
			enemy33 = ImageIO.read(new FileImageInputStream(new File("enemy21.png")));
			enemy44 = ImageIO.read(new FileImageInputStream(new File("enemy21.png")));
			enemy55 = ImageIO.read(new FileImageInputStream(new File("enemy21.png")));

			meteor11 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));
			meteor21 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));
			meteor31 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));
			meteor41 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));
			meteor51 = ImageIO.read(new FileImageInputStream(new File("rocket1K.png")));

			meteor12 = ImageIO.read(new FileImageInputStream(new File("rocket2K.png")));
			meteor22 = ImageIO.read(new FileImageInputStream(new File("rocket2K.png")));
			meteor32 = ImageIO.read(new FileImageInputStream(new File("rocket2K.png")));
			meteor42 = ImageIO.read(new FileImageInputStream(new File("rocket2K.png")));
			meteor52 = ImageIO.read(new FileImageInputStream(new File("rocket2K.png")));

			meteor13 = ImageIO.read(new FileImageInputStream(new File("rocket3K.png")));
			meteor23 = ImageIO.read(new FileImageInputStream(new File("rocket3K.png")));
			meteor33 = ImageIO.read(new FileImageInputStream(new File("rocket3K.png")));
			meteor43 = ImageIO.read(new FileImageInputStream(new File("rocket3K.png")));
			meteor53 = ImageIO.read(new FileImageInputStream(new File("rocket3K.png")));

			meteor14 = ImageIO.read(new FileImageInputStream(new File("rocket4K.png")));
			meteor24 = ImageIO.read(new FileImageInputStream(new File("rocket4K.png")));
			meteor34 = ImageIO.read(new FileImageInputStream(new File("rocket4K.png")));
			meteor44 = ImageIO.read(new FileImageInputStream(new File("rocket4K.png")));
			meteor54 = ImageIO.read(new FileImageInputStream(new File("rocket4K.png")));

		} catch (IOException e) {
			System.err.println("io exception");
		}
		fileTree = new File("TreeEasy.dat");
		FileInputStream fis;
		try {
			fis = new FileInputStream(fileTree);
			ObjectInputStream ois = new ObjectInputStream(fis);
			tree = (Tree) ois.readObject();
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("burda");
		}

		setBackground(Color.BLACK);

		timer.start();
	}

	public boolean queueControl1() {
		int counter = q1.getCount();
		for (int i = q1.front; i < q1.getSize(); i++)
			if (counter != 0) {
				Shot shot = q1.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies1)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies1.remove(enemy);
							shot.isEnable = false;
							q1.deleteQueue();
							return true;
						}
				counter--;
			}

		for (int i = 0; i <= q1.rear; i++)
			if (counter != 0) {
				Shot shot = q1.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies1)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies1.remove(enemy);
							shot.isEnable = false;
							q1.deleteQueue();
							return true;
						}
				counter--;
			}
		return false;
	}

	public boolean queueControl2() {
		int counter = q2.getCount();
		for (int i = q2.front; i < q2.getSize(); i++)
			if (counter != 0) {
				Shot shot = q2.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies2)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies2.remove(enemy);
							shot.isEnable = false;
							q2.deleteQueue();
							return true;
						}
				counter--;
			}

		for (int i = 0; i <= q2.rear; i++)
			if (counter != 0) {
				Shot shot = q2.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies2)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies2.remove(enemy);
							shot.isEnable = false;
							q2.deleteQueue();
							return true;
						}
				counter--;
			}
		return false;
	}

	public boolean queueControl3() {
		int counter = q3.getCount();
		for (int i = q3.front; i < q3.getSize(); i++)
			if (counter != 0) {
				Shot shot = q3.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies3)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies3.remove(enemy);
							shot.isEnable = false;
							q3.deleteQueue();
							return true;
						}
				counter--;
			}

		for (int i = 0; i <= q3.rear; i++)
			if (counter != 0) {
				Shot shot = q3.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies3)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies3.remove(enemy);
							shot.isEnable = false;
							q3.deleteQueue();
							return true;
						}
				counter--;
			}
		return false;
	}

	public boolean queueControl4() {
		int counter = q4.getCount();
		for (int i = q4.front; i < q4.getSize(); i++)
			if (counter != 0) {
				Shot shot = q4.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies4)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies4.remove(enemy);
							shot.isEnable = false;
							q4.deleteQueue();
							return true;
						}
				counter--;
			}

		for (int i = 0; i <= q4.rear; i++)
			if (counter != 0) {
				Shot shot = q4.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies4)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies4.remove(enemy);
							shot.isEnable = false;
							q4.deleteQueue();
							return true;
						}
				counter--;
			}
		return false;
	}

	public boolean queueControl5() {
		int counter = q5.getCount();
		for (int i = q5.front; i < q5.getSize(); i++)
			if (counter != 0) {
				Shot shot = q5.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies5)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies5.remove(enemy);
							shot.isEnable = false;
							q5.deleteQueue();
							return true;
						}
				counter--;
			}

		for (int i = 0; i <= q5.rear; i++)
			if (counter != 0) {
				Shot shot = q5.queueArray[i];
				if (shot != null)
					for (Enemy enemy : enemies5)
						if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
							enemy.setAlive(false);
							enemies5.remove(enemy);
							shot.isEnable = false;
							q5.deleteQueue();
							return true;
						}
				counter--;
			}
		return false;
	}

	public boolean testControl1() {
		for (Shot shot : shots1) {
			for (Enemy enemy : enemies1) {
				if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
					enemy.setAlive(false);
					shots1.remove(shot);
					enemies1.remove(enemy);
					return true;
				}
			}
		}
		return false;
	}

	public boolean testControl2() {
		for (Shot shot : shots2) {
			for (Enemy enemy : enemies2) {
				if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
					enemy.setAlive(false);
					shots2.remove(shot);
					enemies2.remove(enemy);
					return true;
				}
			}
		}
		return false;
	}

	public boolean testControl3() {
		for (Shot shot : shots3) {
			for (Enemy enemy : enemies3) {
				if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
					enemy.setAlive(false);
					shots3.remove(shot);
					enemies3.remove(enemy);
					return true;
				}
			}
		}
		return false;
	}

	public boolean testControl4() {
		for (Shot shot : shots4) {
			for (Enemy enemy : enemies4) {
				if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
					enemy.setAlive(false);
					shots4.remove(shot);
					enemies4.remove(enemy);
					return true;
				}
			}
		}
		return false;
	}

	public boolean testControl5() {
		for (Shot shot : shots5) {
			for (Enemy enemy : enemies5) {
				if (shot.getX() <= enemy.getX() + 10 && shot.getX() >= enemy.getX() - 10) {
					enemy.setAlive(false);
					shots5.remove(shot);
					enemies5.remove(enemy);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		time_passed += 5;
		shotDelay += 5;
		enemySpawn += 5;

		an++;

		g.drawImage(ship, 0, shipY, ship.getWidth() / 3, ship.getHeight() / 3, this);

		// oyunu kazan?lan puana g?re zorla?t?r?yor
		int spawnRate = 400;
		if (point > 50)
			spawnRate = 300;
		if (point > 100)
			spawnRate = 200;
		if (point > 200)
			spawnRate = 100;

		// d??man gemilerin koridorlara rastgele da??lmas? i?in
		if (enemySpawn % spawnRate == 0) {
			int luck = random.nextInt(6);

			if (luck == 1)
				enemies1.add(new Enemy(1, 1700));
			else if (luck == 2)
				enemies2.add(new Enemy(2, 1700));
			else if (luck == 3)
				enemies3.add(new Enemy(3, 1700));
			else if (luck == 4)
				enemies4.add(new Enemy(4, 1700));
			else
				enemies5.add(new Enemy(5, 1700));
		}

		// gemilerin paint edilmesini sa?lar
		for (Enemy enemy : enemies1) {
			if (enemy.isAlive()) {
				if (an % 32 <= 8)
					g.drawImage(meteor11, enemy.getX(), 10, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 16 && an % 32 > 8)
					g.drawImage(meteor12, enemy.getX(), 10, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 24 && an % 32 > 16)
					g.drawImage(meteor13, enemy.getX(), 10, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else
					g.drawImage(meteor14, enemy.getX(), 10, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
			}
		}

		for (Enemy enemy : enemies2) {
			if (enemy.isAlive()) {
				if (an % 32 <= 8)
					g.drawImage(meteor21, enemy.getX(), 160, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 16 && an % 32 > 8)
					g.drawImage(meteor22, enemy.getX(), 160, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 24 && an % 32 > 16)
					g.drawImage(meteor23, enemy.getX(), 160, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else
					g.drawImage(meteor24, enemy.getX(), 160, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
			}
		}

		for (Enemy enemy : enemies3) {
			if (enemy.isAlive()) {
				if (an % 32 <= 8)
					g.drawImage(meteor31, enemy.getX(), 310, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 16 && an % 32 > 8)
					g.drawImage(meteor32, enemy.getX(), 310, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 24 && an % 32 > 16)
					g.drawImage(meteor33, enemy.getX(), 310, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else
					g.drawImage(meteor34, enemy.getX(), 310, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
			}
		}

		for (Enemy enemy : enemies4) {
			if (enemy.isAlive()) {
				if (an % 32 <= 8)
					g.drawImage(meteor41, enemy.getX(), 460, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 16 && an % 32 > 8)
					g.drawImage(meteor42, enemy.getX(), 460, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 24 && an % 32 > 16)
					g.drawImage(meteor43, enemy.getX(), 460, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else
					g.drawImage(meteor44, enemy.getX(), 460, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
			}
		}

		for (Enemy enemy : enemies5) {
			if (enemy.isAlive()) {
				if (an % 32 <= 8)
					g.drawImage(meteor51, enemy.getX(), 610, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 16 && an % 32 > 8)
					g.drawImage(meteor52, enemy.getX(), 610, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else if (an % 32 <= 24 && an % 32 > 16)
					g.drawImage(meteor53, enemy.getX(), 610, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
				else
					g.drawImage(meteor54, enemy.getX(), 610, enemy1.getWidth() / 3, enemy1.getHeight() / 3, this);
			}
		}

		g.setColor(Color.BLUE);

		// shotlar?n paint edilmesini sa?lar
		for (Shot shot : shots1) {
			g.fillOval(shot.getX(), shot.getY(), 20, 10);
		}

		for (Shot shot : shots2) {
			g.fillOval(shot.getX(), shot.getY(), 20, 10);
		}

		for (Shot shot : shots3) {
			g.fillOval(shot.getX(), shot.getY(), 20, 10);
		}

		for (Shot shot : shots4) {
			g.fillOval(shot.getX(), shot.getY(), 20, 10);
		}

		for (Shot shot : shots5) {
			g.fillOval(shot.getX(), shot.getY(), 20, 10);
		}

		for (Queue queue1 : queuearray) {
			int count = queue1.getCount();
			for (int a = queue1.front; a < queue1.getSize(); a++) {
				if (count != 0) {
					Shot shots = queue1.queueArray[a];
					if (shots != null && shots.isEnable == true)
						g.fillOval(shots.getX(), shots.getY(), 20, 10);
					count--;
				}
			}
			for (int a = 0; a < queue1.rear; a++) {
				if (count != 0) {
					Shot shots = queue1.queueArray[a];
					if (shots != null && shots.isEnable == true)
						g.fillOval(shots.getX(), shots.getY(), 20, 10);
					count--;
				}
			}
		}

		Shot shot = q1.getFront();
		if (q1.getFront() != null)
			if (shot.getX() == 1750) {
				shot.isEnable = false;
				q1.deleteQueue();
			}

		// checks if shot and ship intersects
		if (testControl1()) {
			point += 1;
			if(!isActive) {
				Random rand = new Random();
				int random = rand.nextInt(1000);
				if(random <= 100) {
					Slow s = new Slow();
					enemydirX += s.getAmount(); 
					duration = s.getDuration();
					duration *= 300;
					temp = time_passed;
					System.out.println("Slow dropped for " + s.getDuration() + " seconds.");
					powerUp.setText("Slow dropped for " + s.getDuration() + " seconds.");
					isActive = true;
				}
			}
		}

		if (testControl2()) {
			point += 1;
			if(!isActive) {
				Random rand = new Random();
				int random = rand.nextInt(1000);
				if(random <= 100) {
					Slow s = new Slow();
					enemydirX += s.getAmount(); 
					duration = s.getDuration();
					duration *= 300;
					temp = time_passed;
					System.out.println("Slow dropped for " + s.getDuration() + " seconds.");
					powerUp.setText("Slow dropped for " + s.getDuration() + " seconds.");
					isActive = true;
				}
			}
		}

		if (testControl3()) {
			point += 1;
			if(!isActive) {
				Random rand = new Random();
				int random = rand.nextInt(1000);
				if(random <= 100) {
					Slow s = new Slow();
					enemydirX += s.getAmount(); 
					duration = s.getDuration();
					duration *= 300;
					temp = time_passed;
					System.out.println("Slow dropped for " + s.getDuration() + " seconds.");
					powerUp.setText("Slow dropped for " + s.getDuration() + " seconds.");
					isActive = true;
				}
			}
		}

		if (testControl4()) {
			point += 1;
			if(!isActive) {
				Random rand = new Random();
				int random = rand.nextInt(1000);
				if(random <= 100) {
					Slow s = new Slow();
					enemydirX += s.getAmount(); 
					duration = s.getDuration();
					duration *= 300;
					temp = time_passed;
					System.out.println("Slow dropped for " + s.getDuration() + " seconds.");
					powerUp.setText("Slow dropped for " + s.getDuration() + " seconds.");
					isActive = true;
				}
			}
		}

		if (testControl5()) {
			point += 1;
			if(!isActive) {
				Random rand = new Random();
				int random = rand.nextInt(1000);
				if(random <= 100) {
					Slow s = new Slow();
					enemydirX += s.getAmount(); 
					duration = s.getDuration();
					duration *= 300;
					temp = time_passed;
					System.out.println("Slow dropped for " + s.getDuration() + " seconds.");
					powerUp.setText("Slow dropped for " + s.getDuration() + " seconds.");
					isActive = true;
				}
			}
		}

		if (queueControl1()) {
			point += 1;
			Random rand = new Random();
			int random = rand.nextInt(1000);
			if(random <= 1000) {
				Slow s = new Slow();
				
				System.out.println("dropped");
			}
		}

		if (queueControl2())
			point += 1;

		if (queueControl3())
			point += 1;

		if (queueControl4())
			point += 1;

		if (queueControl5())
			point += 1;
		
		if(time_passed - duration == temp) {
			temp = 0;
			duration = 0;
			isActive = false;
			enemydirX = 2;
			powerUp.setText("");
		}

		// checks if game is ended
		if (isEnded1()) {
			file = new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ate? : " + shot_wasted + "\nGe?en s?re : " + (time_passed / 1000.0)
					+ " saniye";
			int run = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

			if (run == JOptionPane.YES_OPTION) {
				Screen g1 = new Screen("BauSpace");
				g1.setVisible(true);
			}

			if (run == JOptionPane.NO_OPTION) {
				FileWriter fw;
				try {
					fileTree = new File("TreeEasy.dat");
					tree.insert(point, shot_wasted, time_passed / 1000);
					FileOutputStream fos = new FileOutputStream(fileTree);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();

					fw = new FileWriter(file);
					fw.write(point + "	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:" + point);
					fw.flush();
					fw.close();
					Veriler v = new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (run == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}

			/*
			 * try { File file = new
			 * File("C:\\Users\\kutay\\Desktop\\Eclipse\\High Scores\\scores.txt");
			 * FileWriter fw = new FileWriter(file); fw.write(point + "-" + shot_wasted +
			 * "-" + time_passed / 1000.0 + "\n"); fw.flush(); fw.close(); } catch
			 * (Exception ex) { System.err.println("exception occured"); }
			 */
		}

		if (isEnded2()) {
			file = new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ate? : " + shot_wasted + "\nGe?en s?re : " + (time_passed / 1000.0)
					+ " saniye";
			int run = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

			if (run == JOptionPane.YES_OPTION) {
				Screen g1 = new Screen("BauSpace");
				g1.setVisible(true);
			}

			if (run == JOptionPane.NO_OPTION) {
				FileWriter fw;
				try {
					fileTree = new File("TreeEasy.dat");
					tree.insert(point, shot_wasted, time_passed / 1000);
					FileOutputStream fos = new FileOutputStream(fileTree);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();

					fw = new FileWriter(file);
					fw.write(point + "	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:" + point);
					fw.flush();
					fw.close();
					Veriler v = new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (run == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}

		}

		if (isEnded3()) {
			file = new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ate? : " + shot_wasted + "\nGe?en s?re : " + (time_passed / 1000.0)
					+ " saniye";
			int run = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

			if (run == JOptionPane.YES_OPTION) {
				Screen g1 = new Screen("BauSpace");
				g1.setVisible(true);
			}

			if (run == JOptionPane.NO_OPTION) {
				FileWriter fw;
				try {
					fileTree = new File("TreeEasy.dat");
					tree.insert(point, shot_wasted, time_passed / 1000);
					FileOutputStream fos = new FileOutputStream(fileTree);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();

					fw = new FileWriter(file, true);
					fw.write(point + "	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:" + point);
					fw.flush();
					fw.close();
					Veriler v = new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (run == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}

		}

		if (isEnded4()) {
			file = new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ate? : " + shot_wasted + "\nGe?en s?re : " + (time_passed / 1000.0)
					+ " saniye";
			int run = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

			if (run == JOptionPane.YES_OPTION) {
				Screen g1 = new Screen("BauSpace");
				g1.setVisible(true);
			}

			if (run == JOptionPane.NO_OPTION) {
				FileWriter fw;
				try {
					fileTree = new File("TreeEasy.dat");
					tree.insert(point, shot_wasted, time_passed / 1000);
					FileOutputStream fos = new FileOutputStream(fileTree);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();

					fw = new FileWriter(file, true);
					fw.write(point + "	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:" + point);
					fw.flush();
					fw.close();
					Veriler v = new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (run == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}

		}

		if (isEnded5()) {
			file = new File("Point.txt");
			timer.stop();
			String message = "Kaybettin!\nHarcanan ate? : " + shot_wasted + "\nGe?en s?re : " + (time_passed / 1000.0)
					+ " saniye";
			int run = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

			if (run == JOptionPane.YES_OPTION) {
				Screen g1 = new Screen("BauSpace");
				g1.setVisible(true);
			}

			if (run == JOptionPane.NO_OPTION) {
				FileWriter fw;
				try {
					fileTree = new File("TreeEasy.dat");
					tree.insert(point, shot_wasted, time_passed / 1000);
					FileOutputStream fos = new FileOutputStream(fileTree);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(tree);
					oos.close();

					fw = new FileWriter(file);
					fw.write(point + "	            ");
					System.out.println("records are saved to " + file.getName() + " and point is:" + point);
					fw.flush();
					fw.close();
					Veriler v = new Veriler();
					v.setVisible(true);
					v.setSize(640, 240);
					v.setResizable(false);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (run == JOptionPane.CLOSED_OPTION) {
				System.exit(0);
			}

		}
	}

	public boolean isEnded1() {
		for (Enemy enemy : enemies1) {
			if (enemy.getX() == 120)
				return true;
		}
		return false;
	}

	public boolean isEnded2() {
		for (Enemy enemy : enemies2) {
			if (enemy.getX() == 120)
				return true;
		}
		return false;
	}

	public boolean isEnded3() {
		for (Enemy enemy : enemies3) {
			if (enemy.getX() == 120)
				return true;
		}
		return false;
	}

	public boolean isEnded4() {
		for (Enemy enemy : enemies4) {
			if (enemy.getX() == 120)
				return true;
		}
		return false;
	}

	public boolean isEnded5() {
		for (Enemy enemy : enemies5) {
			if (enemy.getX() == 120)
				return true;
		}
		return false;
	}

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (Shot shot : shots1) {
			shot.setX(shot.getX() + shotdirX);
		}

		for (Shot shot : shots2) {
			shot.setX(shot.getX() + shotdirX);
		}

		for (Shot shot : shots3) {
			shot.setX(shot.getX() + shotdirX);
		}

		for (Shot shot : shots4) {
			shot.setX(shot.getX() + shotdirX);
		}

		for (Shot shot : shots5) {
			shot.setX(shot.getX() + shotdirX);
		}

		for (Queue qu : queuearray) {
			int counter = qu.getCount();
			if (!qu.isEmpty())
				for (int i = qu.front; i < qu.getSize() - 1; i++) {
					if (counter != 0) {
						qu.queueArray[i].setX(qu.queueArray[i].getX() + shotdirX);
						counter--;
					}
				}
			if (!qu.isEmpty())
				for (int i = 0; i <= qu.rear; i++) {
					if (counter != 0) {
						qu.queueArray[i].setX(qu.queueArray[i].getX() + shotdirX);
						counter--;
					}
				}
		}

		for (Enemy enemy : enemies1) {
			if (enemy.isAlive())
				enemy.setX(enemy.getX() - enemydirX);
		}

		for (Enemy enemy : enemies2) {
			if (enemy.isAlive())
				enemy.setX(enemy.getX() - enemydirX);
		}

		for (Enemy enemy : enemies3) {
			if (enemy.isAlive())
				enemy.setX(enemy.getX() - enemydirX);
		}

		for (Enemy enemy : enemies4) {
			if (enemy.isAlive())
				enemy.setX(enemy.getX() - enemydirX);
		}

		for (Enemy enemy : enemies5) {
			if (enemy.isAlive())
				enemy.setX(enemy.getX() - enemydirX);
		}

		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_UP)
			if (shipY <= 0)
				shipY = 0;
			else
				shipY -= shipdirY;
		else if (c == KeyEvent.VK_DOWN)
			if (shipY >= 600)
				shipY = 600;
			else
				shipY += shipdirY;
		else if (c == KeyEvent.VK_RIGHT) {

		}

		else if (c == KeyEvent.VK_RIGHT) {

		}

		else if (c == KeyEvent.VK_CONTROL) {
			if (shipY == 0) {
				shots1.add(new Shot(110, shipY + 82));
				shot_wasted++;
			} else if (shipY == 150) {
				shots2.add(new Shot(110, shipY + 82));
				shot_wasted++;
			} else if (shipY == 300) {
				shots3.add(new Shot(110, shipY + 82));
				shot_wasted++;
			} else if (shipY == 450) {
				shots4.add(new Shot(110, shipY + 82));
				shot_wasted++;
			} else if (shipY == 600) {
				shots5.add(new Shot(110, shipY + 82));
				shot_wasted++;
			}
		}

		else if (c == KeyEvent.VK_Z) {
			if (shipY == 0) {
				q1.addQueue(new Shot(110, shipY + 82), 1);
			} else if (shipY == 150) {
				q2.addQueue(new Shot(110, shipY + 82), 2);
			} else if (shipY == 300) {
				q3.addQueue(new Shot(110, shipY + 82), 3);
			} else if (shipY == 450) {
				q4.addQueue(new Shot(110, shipY + 82), 4);
			} else if (shipY == 600) {
				q5.addQueue(new Shot(110, shipY + 82), 5);
			}
		}
	}
}
