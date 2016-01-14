package puzzleV3;

import java.awt.Point;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Model {
	Label[][] labels;
	int puzzleSize;
	Label moveCount = new Label("0");
	
	//only used for communicating to the view that a special change occurs.
	Label isPlaying = new Label("you are still playing huh?"); 
	
	//a point that keeps track of the label with the text "0". used in our randomMove().
	Point zeroPos = new Point(0,0);
	
	public  Label[][] getLabels(){
		return labels;
	}
	
	/*
	 *Creates the 2 dimensional labelArray that represents the game board.
	 *It scales the label size to be optimal with our scene. It changes the 
	 *first 3 labels to be in order 2, 3, 1 and 0 to be the in the bottom
	 *right corner. and "hides" the label with text "0".  
	 */
	public void createLabels(int size){
		//initializes the label array with the correct dimensions.
		labels = new Label[size][size];
		puzzleSize = size;
	
		//used to define the size of the labels. 
		String amountOfDigits = Integer.toString(size*size);
		double dSize = setLabelSideLength(amountOfDigits);
		Font labelFont = new Font(dSize-20);
		
		//sets properties for each label in labels[][] with the text of the
		//current count, starting with 1.
		int count = 1;
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				labels[i][j] = new Label(Integer.toString(count));
				labels[i][j].setMinSize(dSize, dSize);
				labels[i][j].setAlignment(Pos.CENTER);
				//if the game board is 10 or less wide, the font "follow" the labelSize.
				if (size <= 10){
					labels[i][j].setFont(labelFont);
				}
				count++;
			}
		}
		
		//sets the bottom right labels' text to "0" and adds the css style
		labels[size-1][size-1].setText("0");
		labels[size-1][size-1].getStyleClass().add("label-zero");
		
		//sets the first 3 labels in sequence 2, 3, 1
		Label holder1 = labels[0][0];
		Label holder2 = labels[0][1];
		labels[0][0] = labels[0][2];	labels[0][2] = holder1;
		labels[0][1] = labels[0][0];	labels[0][0] = holder2;
		
		//sets the mouseClickAction for every label in labels
		for (int i = 0; i<labels.length; i++){
			for (int j = 0; j<labels.length; j++){
				setMouseClickAction(i, j);
			}
		}
		//important variable for the randomMove() method.
		zeroPos.setLocation(size-1, size-1);
	}
	
	/*
	 * checks if the label at the given x,y in labels is next to zero.
	 * it returns where the zero is relative to the given values.
	 */
	public void checkMove(int x, int y){
		String var = nextToZero(x, y);

		//runs through every possible outcome of nextToZero()
		// and switch labels accordingly.
		if (var == "up"){
			switchLabels(y-1, x, y, x);
		}else if (var == "down"){
			switchLabels( y+1, x, y, x);
		}else if (var == "left"){
			switchLabels( y, x-1, y, x);
		}else if (var == "right"){
			switchLabels( y, x+1, y, x);
		}else if (var == "not"){
			System.out.println("not a valid move");
		}
	}
	
	/*
	 * Checks the labels in each of the 4 directions from the labels' 
	 * x,y coordinates sent along. It only checks inside our labels[][].
	 * it returns a string with the direction of which the label with "0" is.
	 */
	public String nextToZero(int x, int y){
		//check up if there is a label "above" the current label in our labels[][]
		if (y > 0) {
			if (labels[y - 1][x].getText().equals("0")) {return "up";}}
		// check down if there is a label "below" the current label in our labels[][]
		if (y < (puzzleSize - 1)) {
			if (labels[y + 1][x].getText().equals("0")) {return "down";}}
		// check left if there is a label to the "left of" the current label in our labels[][]
		if (x > 0) {
			if (labels[y][x - 1].getText().equals("0")) {return "left";}}
		// check right if there is a label to the "right of" the current label in our labels[][]
		if (x < (puzzleSize - 1)) {
			if (labels[y][x + 1].getText().equals("0")) {return "right";}}
		//if not next to zero 
		return "not";
	}
	
	/*
	 * Switches the text of 2 labels. updates the position of the "0". 
	 * It switches the cssStyle that "hides" the label with the text "0".
	 * Lastly it updates both the moves integer and movesCount label.
	 */
	public void switchLabels(int i, int j, int h, int v){
		//switches the text on the two labels 
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
		//updates the zerPos point
		zeroPos.setLocation(v, h);
		
		/*
		 * updates which label to have the "label-zero" css style. 
		 * functionally it makes the label with the text "0" become invisible.
		 */
		labels[h][v].getStyleClass().add("label-zero");
	    labels[i][j].getStyleClass().remove("label-zero");
	    labels[i][j].getStyleClass().add("label");

	    //increments moves and updates moveCount.
		int moves = Integer.parseInt(moveCount.getText())+1;
		moveCount.setText(Integer.toString(moves));
	}

	/*
	 * runs through ever label and checks if their text matches that of the count.
	 * it checks if the labels are in "order" from 1 to puzzleSize-1. if just one
	 * of the label are in the wrong place. the winCheck returns false.
	 */
	public boolean winCheck(){
		/* the base assumption is that the game is won. we then run through every
		 * possibility that could falsify that assumption. */
		boolean won = true;
		//if the last label = 0 - it's in the correct position.
		if (labels[puzzleSize-1][puzzleSize-1].getText().equals("0")){
			int count = 1;
			//for each label in labels[][]
			for (Label[] l : labels){
				for (Label tmpLabel : l){
					String labelText = tmpLabel.getText();
					
					//if we are looking at the last Label we don't want to do anything since we already checked.
					if (count == puzzleSize*puzzleSize){System.out.println("at the end");}

					//if the Label dosen't matches that of the number of labels we've look through
					//the game is not won.
					else if (!labelText.equals(Integer.toString(count))){
						won = false;
						System.out.println("false: count=" + count + " and lblText: " + labelText);
					}
					count++;
				}
			}
		}else {won = false;}
		return won;
	}

	/*
	 * sets the action to perform when a label is clicked to
	 * check if it can be moved. if so it moves it. and after every move, 
	 * the winCheck() method is called to check if the player has won.
	 */
	public void setMouseClickAction(int y, int x){
		labels[y][x].setOnMouseClicked(e -> {
			checkMove(x, y);
			if (winCheck()){
				isPlaying.setText("You've won");
			}
		});
	}
	
	/*
	 * a method that calculates the double that defines the label and font size in createLabels.
	 * if puzzleSize <= 10 we want the double to be a puzzleSize'th of the screen. else we want
	 * to calculate a size based on how many digits the biggest number is. 
	 */
	public double setLabelSideLength(String digits){
		double screenLength = 400;
		if (puzzleSize <= 10){
			return screenLength/puzzleSize;
		}else {return (digits.length()*5)+20;}
	}
	
	/*
	 * makes puzzleSize*20 random moves. e.g if puzzleSize is 3, it'll do 60 random moves. 
	 */
	public void randomMove(){
		Random rndmNumbGenerator = new Random();
		//we use the position of the "0" to make random moves. we update them throughout the method.
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
}

