package puzzleV2;

import javafx.scene.control.Label;

public class PuzzleModel {
	Label[][] labels;
	int puzzleSize;
	
	public void changeLabels(Label[][] newLabels){
		labels = newLabels;
	}
	public  Label[][] getLabels(){
		return labels;
	}
	public int getSize(){
		return puzzleSize;
	}
	
	public void createLabels(int size){
		labels = new Label[size][size];
		puzzleSize = size;
		int count = 1;
		//initiates the array with labels with the text of the current label. starting with 1.
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				labels[i][j] = new Label(Integer.toString(count));
				count++;
			}
		}
		//sets the final button text to "0"
		labels[size-1][size-1].setText("0");
		
		//sets the first 3 buttons in sequence 2, 3, 1
		Label holder1 = labels[0][0];
		Label holder2 = labels[0][1];
		labels[0][0] = labels[0][2];	labels[0][2] = holder1;
		labels[0][1] = labels[0][0];	labels[0][0] = holder2; 
	}
	
	public String nextToZero(int i, int j){
		String relative = "";
		//check up.
		if (i > 0) {
			if (labels[i - 1][j].getText().equals("0")) {relative = "up";}}
		// check down
		if (i < (getSize() - 1)) {
			if (labels[i + 1][j].getText().equals("0")) {relative = "down";}}
		// check left
		if (j > 0) {
			if (labels[i][j - 1].getText().equals("0")) {relative = "left";}}
		// check right
		if (j < (getSize() - 1)) {
			if (labels[i][j + 1].getText().equals("0")) {relative = "right";}}
		//if not next to zero 
		else {relative = "not";}
		return relative;
	}
	public void checkMove(int x, int y){
		String var = nextToZero(x, y);
		if (var == "up"){
			switchLabels(x-1, y, x, y);
		}else if (var == "down"){
			switchLabels( x+1, y, x, y);
		}else if (var == "left"){
			switchLabels( x, y, x, y-1);
		}else if (var == "right"){
			switchLabels( x, y, x, y+1);
		}else if (var == "not"){
			System.out.println("not a valid move");
		}
	}
	public void switchLabels(int i, int j, int k, int l){
		Label temp = labels[i][j];
		labels[i][j] = labels[k][l];
		labels[k][l] = temp;
	}
	
	public boolean winCheck(){
		boolean won = true;
		int size = getSize();
		int count = 1;
		//checks each label in the dimensional array if it's in order. excluding the last, since it has
		//to be 0.
		while (((size*size) > count) && won){
			for (int i = 0; i<size; i++){
				if (!(labels[i][count-1].getText() == Integer.toString(count))){
					won = false;
				}
			}
			count++;
		}
		return won;
	}
}
