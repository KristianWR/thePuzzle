package puzzleV3;

public class Controller {
	private Model theModel;
	public Controller(Model currentModel){
		theModel = currentModel;
	}
	
	public void mouseClick(int x, int y){
		theModel.checkMove(x, y);
	}
	
}
