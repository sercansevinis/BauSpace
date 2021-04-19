package EasyMode;
public class Shot {

	private int x;
	private int y;
	private Shot link;
	public boolean isEnable = true;

	public Shot() {
		link = null;
	}
	
	public Shot(int x, int y) {
		this.x = x;
		this.y = y;
		link = null;
	}
	
	public Shot(int x, int y, Shot link) {
		this.x = x;
		this.y = y;
		this.link = link;
	}
	
	public Shot getLink() {
		return link;
	}

	public void setLink(Shot link) {
		this.link = link;
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
}
