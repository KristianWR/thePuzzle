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
		if (valid == "left"){		
			
		}//else if ()
		
		//change [i][j] with [j][i]
		
		currentPuzzle.changeBtns(movable);
	}
	
	public boolean winCheck(){
		boolean won = true;
		int size = currentPuzzle.getSize();
		Button[][] theBtns = currentPuzzle.getButtons();
		int count = 1;
		while (((size*size) >= count) && won){
			for (int i = 0; i<size; i++){
				if (!(theBtns[i][count-1].getText() == Integer.toString(count))){
					won = false;
				}
			}
			count++;
		}
		return won;
	}
		
}
