package puzzleV2;

public class PuzzleController {
	PuzzleView curentView;
	PuzzleModel currentPuzzle;
	
	public PuzzleController(PuzzleView view, PuzzleModel puzzle){
		this.curentView = view;
		this.currentPuzzle = puzzle;
		puzzle.createLabels(3);
		view.gridPane = view.removeLabels(view.gridPane, view.tempLabels);
		view.gridPane = view.addLabels(view.gridPane, puzzle.getLabels());
		view.updateWindow();
	}
}

