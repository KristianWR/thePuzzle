package puzzleV3;

import java.awt.Point;
import java.util.Random;
import javafx.scene.control.Label;

public class Model {
	Label[][] labels;
	int puzzleSize;
	Label isPlaying = new Label("you are still playing huh?");
	Label moveCount = new Label("0");
	Point zeroPos = new Point(0,0);
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
		zeroPos.setLocation(size-1, size-1);
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
		//for debug
		System.out.println(var + " x=" + x + " y=" + y + " " + labels[y][x].getText());
		//runs through every possibility of outcome in switchLabels and switch labels accordingly 
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
		int x = zeroPos.x;
		int y = zeroPos.y;
		//Number of random moves = puzzleSize*20
		for (int i = puzzleSize * 20; i>0; i--){
			int randNumb = rndmNumbGenerator.nextInt(4);
			/*
			 * Each if statement uses the randNumb and the condition if that moves is 'legal'
			 * if we proceed inside a statement we make the move and update x, y that are the
			 * parameters that keeps count of where a move is "legal" so to speak.
			 */
			if (randNumb == 0 && y!=0 && y<puzzleSize){
				//moves up
				checkMove(x, y-1);
				y -= 1;
			}else if (randNumb == 1 && y<(puzzleSize-1) && y>=0){
				//moves down
				checkMove(x, y+1);
				y += 1;
			}else if(randNumb == 2 && x !=0 && x<(puzzleSize)){
				//moves left
				checkMove(x-1, y);
				x -= 1;
			}else if(x<(puzzleSize-1) && x>=0){	
				//moves right
				checkMove(x+1, y);
				x += 1;
			}
		}
		// reset the moveCount to 0 as the random moves counts as player moves but aren't.
		moveCount.setText("0");
	}
	
	public void switchLabels(int i, int j, int h, int v){
		//switches the text on the two labels 
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
		//updates the zerPos point
		zeroPos.setLocation(v, h);
		//increments the moveCount.
		int moves = Integer.parseInt(moveCount.getText())+1;
		moveCount.setText(Integer.toString(moves));
	}
	
	public boolean winCheck(){
		/*
		 * the base assumption is that the game is won. we then run through every
		 * possibility that could falsify that assumption. 
		 */
		boolean won = true;
		//if the last label = 0 - it's in the correct position.
		if (labels[puzzleSize-1][puzzleSize-1].getText().equals("0")){
			int count = 1;
			//for each label in labels[][] (excluding the very last ("0))
			for (Label[] l : labels){
				for (Label tmpLabel : l){
					String labelText = tmpLabel.getText();
					//if we are looking at the last Label we don't want to do anything.
					if (count == puzzleSize*puzzleSize){
						System.out.println("at the end");
					//if the Label dosen't matches that of the number of labels we've look through
					//the game is not won.
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
