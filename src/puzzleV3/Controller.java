package puzzleV3;

public class Controller {
	Model theModel;
	public Controller(Model currentModel){
		theModel = currentModel;
	}
	
	public void mouseClick(double x, double y){
		theModel.checkMove((int) x, (int) y);
	}
	
}
