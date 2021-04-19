package EasyMode;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;


public class Veriler extends JFrame {
	private JTextField textField;
	public Tree tree;
	public EasyMode.Tree treeEasy;
	public Veriler() {
		tree=new Tree();
		getContentPane().setLayout(null);
		JButton btnNewButton = new JButton("ScoreBoard");
		setBackground(Color.RED);
		btnNewButton.setBounds(38, 147, 89, 23);
		getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(38, 14, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File file=new File("E:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\Point.txt");
				File fileTree=new File("E:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\TreeEasy.dat");
				Scanner sc;
				FileWriter fw;
				
					try {
						fw = new FileWriter(file,true);
						fw.write(textField.getText()+ " \n");
						fw.flush();
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					FileInputStream fis;
					try {
						fis = new FileInputStream(fileTree);
						ObjectInputStream ois=new ObjectInputStream(fis);
						tree=(Tree)ois.readObject();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
				
			
			}
		});
		btnSave.setBounds(38, 79, 89, 23);
		getContentPane().add(btnSave);
		JTextArea txtrPoint = new JTextArea();
		txtrPoint.setBackground(Color.ORANGE);
		txtrPoint.setBounds(193, 35, 195, 215);
		getContentPane().add(txtrPoint);
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setForeground(Color.RED);
		lblPoint.setBackground(Color.GREEN);
		lblPoint.setBounds(193, 17, 46, 14);
		getContentPane().add(lblPoint);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(Color.RED);
		lblNickname.setBackground(Color.ORANGE);
		lblNickname.setBounds(320, 17, 68, 14);
		getContentPane().add(lblNickname);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = "";
				File file=new File("E:\\Users\\Sercan\\eclipse-workspace\\BauSpace2\\Point.txt");
				Scanner sc;
				try {
					sc = new Scanner(file);
					while (sc.hasNext()) {
						s += sc.nextLine() + "\n";
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			System.out.println(s);
				txtrPoint.setText(s);
				
				System.out.println();
				tree.print(tree.getRoot());
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
