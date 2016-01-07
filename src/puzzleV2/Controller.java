package puzzleV2;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
	Stage curentView;
	Puzzle currentPuzzle;
	
	public Controller(Stage view, Puzzle puzzle){
		this.curentView = view;
		this.currentPuzzle = puzzle;
	}
	
	
	//edit in the model
	public Label[][] switchLabels(Label[][] labels, int i, int j, int k, int l){
			Label temp = labels[i][j];
			labels[i][j] = labels[k][l];
			labels[k][l] = temp;
			return labels;
	}
	
	//Checking methods for different things.
	public void nextToZero(){
		
	}
	public void findPosInDimensions(){
		
	}
	public void winCheck(){
		
	}
}

