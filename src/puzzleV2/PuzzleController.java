package puzzleV2;

import javafx.event.ActionEvent;

public class PuzzleController {
	PuzzleView curentView;
	PuzzleModel currentPuzzle;
	
	public PuzzleController(PuzzleView view, PuzzleModel puzzle){
		this.curentView = view;
		this.currentPuzzle = puzzle;
	}
	
	public void playListener(ActionEvent e){
		currentPuzzle.createLabels(3);
		
		curentView.window.setScene(curentView.scene);
	}
}

