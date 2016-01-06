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
		System.out.println("i is: " + i);
		String var = nextToZero(movable, i, j);
		if (var == "up"){
			movable = switchBtns(movable, i-1, j, i, j);
		}else if (var == "down"){
			movable = switchBtns(movable, i+1, j, i, j);
		}else if (var == "left"){
			movable = switchBtns(movable, i, j, i, j-1);
		}else if (var == "right"){
			movable = switchBtns(movable, i, j, i, j+1);
		}
		currentPuzzle.changeBtns(movable);
	}
	
	public int[] findPos(String btnText, Button[][] btns){
		int[] ij = new int[2];
		for (int i = 0; i < currentPuzzle.puzzleSize; i++){
			for (int j = 0; j < currentPuzzle.puzzleSize; j++){
				if (btns[i][j].getText() == btnText){ 
					ij[0] = i;
					ij[1] = j;
				}
			}
		}
		return ij;
	}
	
	public String nextToZero(Button[][] movable, int i, int j){
		String relative = "";
		//check up.
		if (i > 0) {
			if (movable[i - 1][j].getText().equals("0")) {relative = "up";}}
		// check down
		if (i < (currentPuzzle.getSize() - 1)) {
			if (movable[i + 1][j].getText().equals("0")) {relative = "down";}}
		// check left
		if (j > 0) {
			if (movable[i][j - 1].getText().equals("0")) {relative = "left";}}
		// check right
		if (j < (currentPuzzle.puzzleSize - 1)) {
			if (movable[i][j + 1].getText().equals("0")) {relative = "right";}}
		return relative;
	}
	
	public boolean nextToZeroBool(Button[][] movable, int i, int j){
		Boolean bool = false;
		String var = nextToZero(movable, i, j);
		if ((var=="up")||(var=="down")||(var=="left")||(var=="right")){
			bool = true;
		}
		return bool;
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
