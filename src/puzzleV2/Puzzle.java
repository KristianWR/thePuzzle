package puzzleV2;

import javafx.scene.control.Label;

public class Puzzle {
	Label[][] labels;
	int puzzleSize;
	
	public Puzzle(int size){
		labels = new Label[size][size];
		puzzleSize = size;
		int count = 1;
		//intiates the array with labels with the text of the current label. starting with 1.
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
	
	public void changeLabels(Label[][] newLabels){
		labels = newLabels;
	}
	public  Label[][] getLabels(){
		return labels;
	}
	public int getSize(){
		return puzzleSize;
	}
}
