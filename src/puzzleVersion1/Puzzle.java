package puzzleVersion1;

import javafx.scene.control.Button;

public class Puzzle {
	
	Button[][] btns;
	int puzzleSize;
	
	//constructs a btn 2 dimensional array with the int size
	public Puzzle (int size){
		btns = new Button[size][size];
		puzzleSize = size;
		int count = 0;
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				btns[i][j] = new Button(Integer.toString(count));
				count++;
			}
		}
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
