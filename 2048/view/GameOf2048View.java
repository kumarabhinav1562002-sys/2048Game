package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import controller.GameOf2048Controller;
import model.Board;
import model.GameOf2048;

public class GameOf2048View extends JFrame {

	private static final int TILE_WIDTH = 100;
	private static final int TILE_HEIGHT = 100;
	private static final int WINDOW_WIDTH = TILE_WIDTH * Board.SIZE + 50;
	private static final int WINDOW_HEIGHT = TILE_HEIGHT * Board.SIZE + 100;

	private static final Color COLOR_BLANK_TILE = new Color(197, 183, 170);
	private static final Color COLOR_2 = new Color(240, 240, 240);
	private static final Color COLOR_4 = new Color(237, 224, 200);
	private static final Color COLOR_8 = new Color(242, 177, 121);
	private static final Color COLOR_16 = new Color(245, 149, 99);
	private static final Color COLOR_32 = new Color(246, 124, 95);
	private static final Color COLOR_64 = new Color(246, 94, 59);
	private static final Color COLOR_128 = new Color(237, 207, 114);
	private static final Color COLOR_256 = new Color(237, 204, 97);
	private static final Color COLOR_512 = new Color(237, 200, 80);
	private static final Color COLOR_1024 = new Color(237, 197, 63);
	private static final Color COLOR_2048 = new Color(237, 194, 46);

	private class GameOf2048Tile extends JLabel {
		public GameOf2048Tile() {
			super("", SwingConstants.CENTER);
			setOpaque(true);
			setPreferredSize(new Dimension(TILE_WIDTH, TILE_HEIGHT));
			setBorder(BorderFactory.createLineBorder(new Color(147, 133, 120), 3));
			setBackground(COLOR_BLANK_TILE);
			setFont(new Font("Serif", Font.BOLD, 40));
		}

		public void setNumber(int n) {
			setText(n == 0 ? "" : String.valueOf(n));
			switch (n) {
				case 0 -> setBackground(COLOR_BLANK_TILE);
				case 2 -> setBackground(COLOR_2);
				case 4 -> setBackground(COLOR_4);
				case 8 -> setBackground(COLOR_8);
				case 16 -> setBackground(COLOR_16);
				case 32 -> setBackground(COLOR_32);
				case 64 -> setBackground(COLOR_64);
				case 128 -> setBackground(COLOR_128);
				case 256 -> setBackground(COLOR_256);
				case 512 -> setBackground(COLOR_512);
				case 1024 -> setBackground(COLOR_1024);
				case 2048 -> setBackground(COLOR_2048);
			}
		}
	}

	private JLabel labelScore;
	private GameOf2048Tile tiles[][];
	private GameOf2048Controller gameOf2048Controller;

	public GameOf2048View() {
		setup();
		setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		setTitle("Game of 2048");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void initController(GameOf2048Controller gameOf2048Controller) {
		this.gameOf2048Controller = gameOf2048Controller;
	}

	private void setup() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel topPanel = new JPanel(new FlowLayout());
		labelScore = new JLabel("Score: 0");
		topPanel.add(labelScore);

		JPanel centerPanel = new JPanel(new GridLayout(Board.SIZE, Board.SIZE));
		tiles = new GameOf2048Tile[Board.SIZE][Board.SIZE];
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				tiles[i][j] = new GameOf2048Tile();
				centerPanel.add(tiles[i][j]);
			}
		}

		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP)
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_UP);
				if (e.getKeyCode() == KeyEvent.VK_DOWN)
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_DOWN);
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_LEFT);
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					gameOf2048Controller.handleUserInput(GameOf2048.MOVE_RIGHT);
			}
		});

		setContentPane(mainPanel);
	}

	public void updateBoard(Board boardObject, int score) {
		int[][] board = boardObject.getBoard();
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				tiles[i][j].setNumber(board[i][j]);
			}
		}
		labelScore.setText("Score: " + score);
	}

	public void showGameResult(String message) {
		JOptionPane.showMessageDialog(this, message, "Game Over!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
}
