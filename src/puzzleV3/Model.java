package puzzleV3;

import java.awt.Point;
import java.util.Random;
import javafx.scene.control.Label;

public class Model {
	Label[][] labels;
	int puzzleSize;
	Label isPlaying = new Label("you are still playing huh?");
	Label moveCount = new Label("0");
	Point zeroPos;
	public void changeLabels(Label[][] newLabels){
		labels = newLabels;
	}
	public  Label[][] getLabels(){
		return labels;
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
		//System.out.println("check: up");
		if (y > 0) {
			if (labels[y - 1][x].getText().equals("0")) {return "up";}}
		// check down
		//System.out.println("check: down");
		if (y < (puzzleSize - 1)) {
			if (labels[y + 1][x].getText().equals("0")) {return "down";}}
		// check left
		//System.out.println("check: left");
		if (x > 0) {
			if (labels[y][x - 1].getText().equals("0")) {return "left";}}
		// check right
		//System.out.println("check: right");
		if (x < (puzzleSize - 1)) {
			if (labels[y][x + 1].getText().equals("0")) {return "right";}}
		//if not next to zero 
		return "not";
	}
	public void checkMove(int x, int y){
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
	
	public void randomMove(){
		Random rndmNumbGenerator = new Random();
		for (int i = puzzleSize * 10; i>0; i--){
			int randNumb = rndmNumbGenerator.nextInt(4);
			int x = zeroPos.x;
			int y = zeroPos.y;
			if (randNumb == 0){
				checkMove(x, y-1);
			}else if (randNumb == 1){
				checkMove(x, y+1);
			}else if(randNumb == 2){
				checkMove(x-1, y);
			}else {	
				checkMove(x+1, y);
			}
		}
		moveCount.setText("0");
	}
	
	public void switchLabels(int i, int j, int h, int v){
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
		zeroPos.setLocation(v, h);
		int moves = Integer.parseInt(moveCount.getText())+1;
		moveCount.setText(Integer.toString(moves));
	}
	
	public boolean winCheck(){
		boolean won = true;
		if (labels[puzzleSize-1][puzzleSize-1].getText().equals("0")){
			int count = 1;
			for (int i = 0; i<puzzleSize; i++){
				for (int j = 0; j <puzzleSize; j++){
					String labelText = labels[i][j].getText();
					if (count == puzzleSize*puzzleSize){
						System.out.println("at the end");
					}else if (!labelText.equals(Integer.toString(count))){
						won = false;
						System.out.println("false: count=" + count + " and lblText: " + labelText);
					}
					count++;
				}
			}
		}else {won = false;}
		
		return won;
	}
	
	public void setMouseClickAction(int y, int x){
		labels[y][x].setOnMouseClicked(e -> {
			checkMove(x, y);
			if (winCheck()){
				isPlaying.setText("You've won");
			}
		});
	}
	
}
