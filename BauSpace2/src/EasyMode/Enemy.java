package EasyMode;
public class Enemy {
	
	private int x;
	private int y;
	public int path;
	private int dirY;
	private boolean isAlive;

	public Enemy() {
		
		x = 70;
		y = 0;
		path = 1;
		dirY = 1;
	}
	
	public Enemy(int path) {
		this.path = path;
	}
	
	public Enemy(int path, int x) {
		
		this.x = x;
		this.path = path;
		isAlive = true;
	}
	
/*
	public Enemy(int x, int y) {
		
		this.x = x;
		this.y = y;
		path = 1;
		dirY = 1;

	}
*/
	public Enemy(int x, int y, int path) {
		
		this.x = x;
		this.y = y;
		this.path = path;
	}
	
	public Enemy(int x, int y, int path, int dirY) {
		
		this.x = x;
		this.y = y;
		this.path = path;
		this.dirY = dirY;
	}
	
	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
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

	public int getPath() {
		return path;
	}

	public void setPath(int path) {
		this.path = path;
	}

	public int getDirY() {
		return dirY;
	}

	public void setDirY(int dirY) {
		this.dirY = dirY;
	}
}
