package puzzleV2;

public class PuzzleController {
	PuzzleView curentView;
	PuzzleModel currentPuzzle;
	
	public PuzzleController(PuzzleView view, PuzzleModel puzzle){
		this.curentView = view;
		this.currentPuzzle = puzzle;
	}
}

