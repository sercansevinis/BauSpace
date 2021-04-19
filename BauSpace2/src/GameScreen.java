import java.awt.Graphics;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
public class GameScreen extends JFrame{
	public GameScreen(String title) throws HeadlessException {
		super(title);
		
		setResizable(false);
		setFocusable(false);
		setSize(800, 1600);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Game game = new Game();
		game.DrawLine();
		add(game.pnl);
		
		game.requestFocus();

		game.addKeyListener(game);

		game.setFocusable(true);
		game.setFocusTraversalKeysEnabled(false);

		add(game);
		setVisible(true);
		
	}
	public static void main(String[] args) {
	new GameScreen("BauSpace").setVisible(true);

	}

}
