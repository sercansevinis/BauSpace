
public class Enemy
{
private int x;
private int y=0;
private int path;
private int dirY;
public int health;
private boolean active=true;
private int enemyType;

public Enemy()
{
	x=75;
	y=10;
	path=1;
	dirY=5;
	health=1;
	enemyType=0;
}
public Enemy(int path,int dirY)
{
	enemyType=0;
	this.path=path;
	this.dirY=dirY;
	if(path==1)
		x=75;
	else if(path==2)
		x=275;
	else if(path==3)
		x=475;
	else if(path==4)
		x=675;
		
}
public Enemy(int path,int dirY,int health,int enemyType)
{
	this.path=path;
	this.dirY=dirY;
	this.health=health;
	this.enemyType=enemyType;
	if(path==1)
		x=75;
	else if(path==2)
		x=275;
	else if(path==3)
		x=475;
	else if(path==4)
		x=675;
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
public boolean getActive() {
	return active;
}
public void setActive(boolean active) {
	this.active = active;
}
public int getEnemyType() {
	return enemyType;
}
public void setEnemyType(int enemyType) {
	this.enemyType = enemyType;
}

}
