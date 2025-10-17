package model;
import java.util.Random;

public class Board {

	public static final int SIZE = 4;
	public static final Random RANDOM = new Random();
	public static final int WINNING_TILE = 2048;
	public static final int EMPTY_TILE = 0;

	private int board[][];

	public Board() {
		board = new int[SIZE][SIZE];
		addRandomDigit(2);
		addRandomDigit(4);
	}

	public void setBoard(int board[][]) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				this.board[i][j] = board[i][j];
			}
		}
	}

	public int[][] getBoard() {
		int[][] boardCopy = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				boardCopy[i][j] = this.board[i][j];
			}
		}
		return boardCopy;
	}

	public boolean searchOnBoard(int x) {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] == x) {
					return true;
				}
			}
		}
		return false;
	}

	public void addRandomDigit() {
		if (RANDOM.nextInt() % 2 == 0) {
			addRandomDigit(2);
		} else {
			addRandomDigit(4);
		}
	}

	private void addRandomDigit(int digit) {
		int i = RANDOM.nextInt(SIZE);
		int j = RANDOM.nextInt(SIZE);
		while (board[i][j] != 0) {
			i = RANDOM.nextInt(SIZE);
			j = RANDOM.nextInt(SIZE);
		}
		board[i][j] = digit;
	}
}
