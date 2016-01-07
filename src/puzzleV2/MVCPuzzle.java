package puzzleV2;

public class MVCPuzzle {

	public static void main(String[] args) {
    	new Thread() {
    		public void run(){
    			javafx.application.Application.launch(PuzzleView.class, args);
    		}
    	}.start();
		
		PuzzleModel theModel = new PuzzleModel();
		PuzzleView theView = new PuzzleView();
		PuzzleController theController = new PuzzleController(theView, theModel);
	
	}

}
