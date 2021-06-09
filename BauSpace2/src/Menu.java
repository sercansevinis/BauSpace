import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import EasyMode.*;


import javax.swing.JTextArea;

public class Menu extends JFrame {
	static Menu m=new Menu();
	public Tree tree;
	public EasyMode.Tree treeeasy;
	String k="";
	public Menu() {
		getContentPane().setLayout(null);
		setSize(461,300);
		JButton btnEasy = new JButton("Easy ");
		btnEasy.setBackground(Color.GREEN);
		btnEasy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Screen g1=new Screen("BauSpace Easy Mode");
				g1.setVisible(true);
				m.setVisible(false);;
			}
		});
		btnEasy.setBounds(90, 74, 122, 23);
		getContentPane().add(btnEasy);
		
		JButton btnHard = new JButton("Hard");
		btnHard.setBackground(Color.RED);
		btnHard.setBounds(90, 123, 122, 23);
		btnHard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameScreen g1=new GameScreen("BauSpace Hard Mode");
				g1.setVisible(true);
				m.setVisible(false);;
				
			}
		});
		getContentPane().add(btnHard);
		
		JLabel lblWelcomeToBauspace = new JLabel("Welcome To BauSpace");
		lblWelcomeToBauspace.setBounds(109, 11, 207, 23);
		lblWelcomeToBauspace.setBackground(Color.RED);
		lblWelcomeToBauspace.setForeground(Color.BLUE);
		getContentPane().add(lblWelcomeToBauspace);
		
		

		JTextArea txtPoint = new JTextArea();
		txtPoint.setBackground(Color.YELLOW);
		txtPoint.setBounds(273, 38, 130, 189);
		getContentPane().add(txtPoint);
		txtPoint.setForeground(Color.BLUE);
		
		
		JButton btnScore = new JButton("Hard Scores");
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				k="";
				String s = "";
				File file=new File("F:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\Point.txt");
				File fileTree=new File("F:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\Tree.dat");
				tree=new Tree();
				
				FileInputStream fis;
				try {
					fis = new FileInputStream(fileTree);
					ObjectInputStream ois=new ObjectInputStream(fis);
					tree=(Tree)ois.readObject();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			System.out.println();
			print(tree.getRoot());
			tree.print(tree.getRoot());
				txtPoint.setText(k);
				System.out.println();
			}
		});
		btnScore.setBounds(90, 175, 122, 23);
		getContentPane().add(btnScore);
		
		JLabel lblPointshottime = new JLabel("       Point--Shot--Time");
		lblPointshottime.setBounds(260, 15, 164, 14);
		getContentPane().add(lblPointshottime);
		
		JButton btnS = new JButton("Easy Scores");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k="";
				String s = "";
				File file=new File("F:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\Point.txt");
				File fileTree=new File("F:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\TreeEasy.dat");
				treeeasy=new EasyMode.Tree();
				
				FileInputStream fis;
				try {
					fis = new FileInputStream(fileTree);
					ObjectInputStream ois=new ObjectInputStream(fis);
					treeeasy=(EasyMode.Tree)ois.readObject();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
							
				System.out.println();
				print2(treeeasy.getRoot());
				treeeasy.print(treeeasy.getRoot());
					txtPoint.setText(k);
					System.out.println();
				
			
			}
		});
		btnS.setBounds(90, 227, 122, 23);
		getContentPane().add(btnS);
		
	}
	public void print(Node root)
	{
		if(root==null)
		{
			return;
		}
		print(root.getRight());
		k+=root.getScore()+" - "+root.getShot()+" - "+root.getTime()+"\n";
		print(root.getLeft());
	}
	public void print2(EasyMode.Node node)
	{
		if(node==null)
		{
			return;
		}
		print2(node.getRight());
		k+=node.getScore()+" "+node.getShot()+" "+node.getTime()+"\n";
		print2(node.getLeft());
	}

	public static void main(String[] args) {
		m.setVisible(true);
	}
}
