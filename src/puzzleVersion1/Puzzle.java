package puzzleVersion1;

import javafx.scene.control.Button;

public class Puzzle {
	
	Button[][] btns;
	int puzzleSize;
	
	//constructs a btn 2 dimensional array with the int size
	public Puzzle (int size){
		btns = new Button[size][size];
		puzzleSize = size;
		int count = 1;
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				btns[i][j] = new Button(Integer.toString(count));
				count++;
			}
		}
		//sets the final button text to "0"
		btns[size-1][size-1].setText("0");
		
		//sets the first 3 buttons in sequence 2, 3, 1
		Button holder1 = btns[0][0];
		Button holder2 = btns[0][1];
		btns[0][0] = btns[0][2];	btns[0][2] = holder1;
		btns[0][1] = btns[0][0];	btns[0][0] = holder2; 
	}
	
	public Button[][] getButtons(){
		return btns;
	}
	public void changeBtns(Button[][] changed){
		btns = changed;
	}
	
	public int getSize(){
		return puzzleSize;
	}
	
	
}
