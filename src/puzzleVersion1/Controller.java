package puzzleVersion1;

import javafx.scene.control.Button;

public class Controller {
	public Puzzle currentPuzzle;
	
	public Controller(int size){
		currentPuzzle = new Puzzle(size);
	}
	
	public String validMove(String btnID){
		return "hej";
	}
	
	public void checkMove(int hej){
		Button[][] movable = currentPuzzle.getButtons();
		
		String valid = validMove("hej");
		if (String == "left"){		
			
		}//else if ()
		
		//change [i][j] with [j][i]
		
		currentPuzzle.changeBtns(movable);
	}		
		
}
