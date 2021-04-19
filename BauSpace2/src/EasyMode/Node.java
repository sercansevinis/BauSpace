package EasyMode;
import java.io.Serializable;

public class Node implements Serializable {

	private int score, shot;
	private double time;
	private Node left;
	private Node right;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node(int score) {
		this.score = score;
		left = null;
		right = null;
	}
	
	public Node(int score, int shot, double time) {
		this.score = score;
		this.shot = shot;
		this.time = time;
		left = null;
		right = null;
	}
	
	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int element) {
		this.score = element;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
