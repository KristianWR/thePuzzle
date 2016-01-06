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
	
	public void checkMove(String btnText){
		Button[][] movable = currentPuzzle.btns;
		int[] ij = findPos(btnText, movable);
		int i = ij[0];
		int j = ij[1];
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
	
	public int[] findPos(String btnText, Button[][] btns){
		int[] ij = new int[2];
		for (int i = 0; i < currentPuzzle.puzzleSize; i++){
			for (int j = 0; j < currentPuzzle.puzzleSize; j++){
				if (btns[i][j].getText().equals(btnText)){ 
					ij[0] = i;
					ij[1] = j;
				}
			}
		}
		return ij;
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
