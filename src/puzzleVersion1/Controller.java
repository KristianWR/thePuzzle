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
		System.out.println("im in checkMove");
		int[] ij = findPos(btnText, movable);
		System.out.println("im out of findPos");
		int i = ij[0];
		int j = ij[1];
		System.out.println("i is: " + i);
		//check up.
		if (i > 0){
			System.out.println("im in up first degree");
			if (movable[i-1][j].getText().equals("0")){
				movable = switchBtns(movable, i, j, i-1, j);
			}
		}
		System.out.println("im out of up first degree" + i);
		//check down
		if (i < (currentPuzzle.getSize() -1)){
			System.out.println("im in down degree");
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
		System.out.println("I got btnID: " + btnText);
		int[] ij = new int[2];
		System.out.println("im in findPos");
		for (int i = 0; i < currentPuzzle.puzzleSize; i++){
			for (int j = 0; j < currentPuzzle.puzzleSize; j++){
				if (btns[i][j].getText() == btnText){ 
					ij[0] = i;
					ij[1] = j;
					System.out.println("Im setting i = " + i + " and j = " + j);
				}
			}
		}
		return ij;
	}
	
	public Button[][] switchBtns(Button[][] btn, int i, int j, int k, int l){
		System.out.println("im in switchBtns");
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
