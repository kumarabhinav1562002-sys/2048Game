import controller.GameOf2048Controller;
import model.GameOf2048;
import view.GameOf2048View;




public class Main {
	public static void main(String[] args) {

		GameOf2048 gameOf2048 = new GameOf2048();
		

		GameOf2048View gameOf2048View = new GameOf2048View();
		

		GameOf2048Controller gameOf2048Controller = new GameOf2048Controller();
		gameOf2048Controller.initModel(gameOf2048);
		gameOf2048Controller.initView(gameOf2048View);
		

		gameOf2048View.initController(gameOf2048Controller);
	}
}
