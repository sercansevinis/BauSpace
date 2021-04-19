package EasyMode;
import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Screen extends JFrame {
	
	public Screen(String title) throws HeadlessException {
		super(title);
		setResizable(false);
		setFocusable(false);

		setSize(1800, 800);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Game game = new Game();

		game.requestFocus();

		game.addKeyListener(game);

		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);

		add(game);
		
		setVisible(true);
	}

	public static void main(String[] args) {

		new Screen("BauSpace Easy Mode").setVisible(true);
		
	
	}
}
