package puzzleV3;

import javafx.scene.control.Label;

public class Model {
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
		String amountOfCiffers = Integer.toString(size*size);
		double dSize = (amountOfCiffers.length()*5)+20.0;
		//initiates the array with labels with the text of the current label. starting with 1.
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				labels[i][j] = new Label(Integer.toString(count));
				labels[i][j].setMinSize(dSize, dSize);
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
		
		for (int i = 0; i<labels.length; i++){
			for (int j = 0; j<labels.length; j++){
				setMouseClickAction(i, j);
			}
		}
	}
	
	public String nextToZero(int x, int y){
		//check up.
		System.out.println("check: up");
		if (y > 0) {
			if (labels[y - 1][x].getText().equals("0")) {return "up";}}
		// check down
		System.out.println("check: down");
		if (y < (getSize() - 1)) {
			if (labels[y + 1][x].getText().equals("0")) {return "down";}}
		// check left
		System.out.println("check: left");
		if (x > 0) {
			if (labels[y][x - 1].getText().equals("0")) {return "left";}}
		// check right
		System.out.println("check: right");
		if (x < (getSize() - 1)) {
			if (labels[y][x + 1].getText().equals("0")) {return "right";}}
		//if not next to zero 
		return "not";
	}
	public void checkMove(int x, int y){
		System.out.println("x = " + x + " y = " + y);
		String var = nextToZero(x, y);
		System.out.println(var + " x=" + x + " y=" + y + " " + labels[y][x].getText());
		if (var == "up"){
			switchLabels(y-1, x, y, x);
		}else if (var == "down"){
			switchLabels( y+1, x, y, x);
		}else if (var == "left"){
			switchLabels( y, x, y, x-1);
		}else if (var == "right"){
			switchLabels( y, x, y, x+1);
		}else if (var == "not"){
			System.out.println("not a valid move");
		}
	}
	public void switchLabels(int i, int j, int h, int v){
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
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
	
	public void setMouseClickAction(int y, int x){
		labels[y][x].setOnMouseClicked(e -> {
			checkMove(x, y);
			if (winCheck()){
				
			}
		});
	}
	
}
