import java.io.Serializable;

public class Tree implements Serializable {

	private Node root;
	private int size;
	
	public Tree() {
		root = null;
		size = 0;
	}
	
	private Node createNewNode(int score, int shot, double time) {
		return new Node(score, shot, time);
	}
	
	public int getSize() {
		return size;
	}
	
	public Node getRoot() {
		return root;
	}
	
	public boolean insert(int score, int shot, double time) {
		if(root == null)
			root = createNewNode(score, shot, time);
		else {
			Node current = root;
			Node parent = null;
			while(current != null) {
				if(score < current.getScore()) {
					parent = current;
					current = current.getLeft();
				} else if(score > current.getScore()) {
					parent = current;
					current = current.getRight();
				} else
					return false;
			}
			if(score < parent.getScore()) 
				parent.setLeft(createNewNode(score, shot, time));
			else 
				parent.setRight(createNewNode(score, shot, time));
		}
		size++;
		return true;
	}
	
	public int max() {
		int max = -1;
		Node current = root;
		while(current != null) {
			max = current.getScore();
			current = current.getRight();
		}
		return max;
	}
	
	public int min() {
		int min = 100;
		Node current = root;
		while(current != null) {
			min = current.getScore();
			current = current.getLeft();
		}
		return min;
	}
	
	public boolean delete(int e) {
		Node current = root;
		Node parent = null;
		while(current.getScore() != e) {
			if(e < current.getScore()) {
				parent = current;
				current = current.getLeft();
			} else if(e > current.getScore()) {
				parent = current;
				current = current.getRight();
			} else
				return false;
		}
		
		if(current.getLeft() == null) {
			if(e < parent.getScore())
				parent.setLeft(current.getRight());
			else
				parent.setRight(current.getRight());
		} else {
			Node parentOfRightMost = current;
			Node rightMost = current.getLeft();
			while(rightMost.getRight() != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.getRight();
			}
			current.setScore(rightMost.getScore());
			if(parentOfRightMost.getRight() == rightMost) 
				parentOfRightMost.setRight(rightMost.getLeft());
			else
				parentOfRightMost.setLeft(rightMost.getLeft());
		}
		size--;
		return true;
	}
	
	public boolean search(int e) {
		Node current = root;
		while(current != null) {
			if(e < current.getScore()) 
				current = current.getLeft();
			else if(e > current.getScore())
				current = current.getRight();
			else
				return true;
		}
		return false;
	}
	
	public void inOrder(Node root) {
		if(root == null)
			return;
		inOrder(root.getLeft());
		System.out.print(root.getScore() + " ");
		inOrder(root.getRight());
	}
	
	public void print(Node root)
	{
		if(root==null)
		{
			return;
		}
		print(root.getRight());
		System.out.print("Point is:"+root.getScore()+" Wasted shot:"+root.getShot()+" Time:"+root.getTime()+"\n");
		print(root.getLeft());
	}
	
	
	public void preOrder(Node root) {
		if(root == null)
			 return;
		System.out.print(root.getScore() + " ");
		preOrder(root.getLeft());
		preOrder(root.getRight());
	}
	
	public void postOrder(Node root) {
		if(root == null)
			 return;
		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.println(root.getScore() + " ");
	}
}