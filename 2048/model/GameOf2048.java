package model;

public class GameOf2048 {

	public static final char MOVE_LEFT = 'A';
	public static final char MOVE_RIGHT = 'D';
	public static final char MOVE_UP = 'W';
	public static final char MOVE_DOWN = 'S';

	private Board board;
	private int score;

	public GameOf2048() {
		board = new Board();
		score = 0;
	}

	public Board getBoard() {
		return board;
	}

	public int getScore() {
		return score;
	}

	public boolean gameWon() {
		return board.searchOnBoard(Board.WINNING_TILE);
	}

	public boolean isGameOver() {
		if (gameWon()) {
			return true;
		}
		if (board.searchOnBoard(Board.EMPTY_TILE)) {
			return false;
		}
		return !userCanMakeAMove();
	}

	public boolean userCanMakeAMove() {
		int board[][] = this.board.getBoard();
		for (int i = 0; i < (Board.SIZE - 1); i++) {
			for (int j = 0; j < (Board.SIZE - 1); j++) {
				if (board[i][j] == board[i][j + 1] ||
						board[i][j] == board[i + 1][j]) {
					return true;
				}
			}
		}
		for (int j = 0; j < (Board.SIZE - 1); j++) {
			if (board[(Board.SIZE - 1)][j] == board[(Board.SIZE - 1)][j + 1]) {
				return true;
			}
		}
		for (int i = 0; i < (Board.SIZE - 1); i++) {
			if (board[i][(Board.SIZE - 1)] == board[i + 1][(Board.SIZE - 1)]) {
				return true;
			}
		}
		return false;
	}

	public int[] processLeftMove(int row[]) {
		int newRow[] = new int[Board.SIZE];
		int j = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			if (row[i] != 0) {
				newRow[j++] = row[i];
			}
		}
		for (int i = 0; i < (Board.SIZE - 1); i++) {
			if (newRow[i] != 0 && newRow[i] == newRow[i + 1]) {
				newRow[i] = 2 * newRow[i];
				score += newRow[i];
				for (j = i + 1; j < (Board.SIZE - 1); j++) {
					newRow[j] = newRow[j + 1];
				}
				newRow[(Board.SIZE - 1)] = 0;
			}
		}
		return newRow;
	}

	public int[] reverseArray(int arr[]) {
		int[] reverseArr = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			reverseArr[i] = arr[arr.length - i - 1];
		}
		return reverseArr;
	}

	public int[] processRightMove(int row[]) {
		int newRow[] = new int[Board.SIZE];
		int j = 0;
		for (int i = 0; i < Board.SIZE; i++) {
			if (row[i] != 0) {
				newRow[j++] = row[i];
			}
		}
		newRow = reverseArray(newRow);
		newRow = processLeftMove(newRow);
		return reverseArray(newRow);
	}

	private boolean checkMoveMade(int[][] oldBoard, int[][] newBoard) {
		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				if (oldBoard[i][j] != newBoard[i][j]) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean processMove(char move) {
		int[][] board = this.board.getBoard();
		switch (move) {
			case MOVE_LEFT:
				for (int i = 0; i < Board.SIZE; i++) {
					int newRow[] = processLeftMove(board[i]);
					for (int j = 0; j < Board.SIZE; j++) {
						board[i][j] = newRow[j];
					}
				}
				break;
			case MOVE_RIGHT:
				for (int i = 0; i < Board.SIZE; i++) {
					int newRow[] = processRightMove(board[i]);
					for (int j = 0; j < Board.SIZE; j++) {
						board[i][j] = newRow[j];
					}
				}
				break;
			case MOVE_UP:
				for (int j = 0; j < Board.SIZE; j++) {
					int row[] = new int[Board.SIZE];
					for (int i = 0; i < Board.SIZE; i++) {
						row[i] = board[i][j];
					}
					int newRow[] = processLeftMove(row);
					for (int i = 0; i < Board.SIZE; i++) {
						board[i][j] = newRow[i];
					}
				}
				break;
			case MOVE_DOWN:
				for (int j = 0; j < Board.SIZE; j++) {
					int row[] = new int[Board.SIZE];
					for (int i = 0; i < Board.SIZE; i++) {
						row[i] = board[i][j];
					}
					int newRow[] = processRightMove(row);
					for (int i = 0; i < Board.SIZE; i++) {
						board[i][j] = newRow[i];
					}
				}
				break;
		}
		boolean moveMade = checkMoveMade(this.board.getBoard(), board);
		this.board.setBoard(board);
		return moveMade;
	}
}
