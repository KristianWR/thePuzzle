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
	
	public void checkMove(String btnText, int i, int j){
		Button[][] movable = currentPuzzle.btns;
		
		//check up.
		if (i > 0){
			if (movable[i-1][j].getText().equals("0")){
				movable = switchBtns(movable, i, j, i-1, j);
			}
		}
		//check down
		if (i < (currentPuzzle.puzzleSize -1)){
			if (movable[i+1][j].getText().equals("0")){
				movable = switchBtns(movable, i+1, j, i, j);
			}
		}
		//check left
		if (j > 0){
			if (movable[i][j-1].getText().equals("0")){
				movable = switchBtns(movable, i, j, i, j-1);
			}
		}
		//check right
		if (j < (currentPuzzle.puzzleSize-1)){
			if (movable[i][j+1].getText().equals("0")){
				movable = switchBtns(movable, i, j, i, j+1);
			}
		}
		currentPuzzle.changeBtns(movable);
	}
	
	public Button[][] switchBtns(Button[][] btn, int i, int j, int k, int l){
		Button temp = btn[i][j];
		btn[i][j] = btn[k][l];
		btn[k][l] = temp;
		return btn;
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
