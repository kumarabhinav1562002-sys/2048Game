package controller;

import model.GameOf2048;
import view.GameOf2048View;

public class GameOf2048Controller {

	private GameOf2048 gameOf2048;
	private GameOf2048View gameOf2048View;

	public GameOf2048Controller() {
	}

	public void initModel(GameOf2048 gameOf2048) {
		this.gameOf2048 = gameOf2048;
	}

	public void initView(GameOf2048View gameOf2048View) {
		this.gameOf2048View = gameOf2048View;
		gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
	}

	public void handleUserInput(char move) {
		boolean moveMade = gameOf2048.processMove(move);

		if (gameOf2048.isGameOver()) {
			gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
			if (gameOf2048.gameWon()) {
				gameOf2048View.showGameResult("You won!");
			} else {
				gameOf2048View.showGameResult("You lost!");
			}
		} else {
			if (moveMade) {
				gameOf2048.getBoard().addRandomDigit();
				gameOf2048View.updateBoard(gameOf2048.getBoard(), gameOf2048.getScore());
			}
		}
	}
}
