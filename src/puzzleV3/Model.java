package puzzleV3;

import java.awt.Point;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Model {
	private Label[][] labels;
	public int puzzleSize;
	public Label moveCount = new Label("0");
	
	//Only used for communicating to the view that the player has won or lost -
	//by the use of onChangeListener.
	public Label isPlaying = new Label("yes"); 
	
	//A point that keeps track of the label with the text "0". Used in our randomMove().
	private Point zeroPos = new Point(0,0);
	
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
		//Initializes the label array with the correct dimensions.
		isPlaying.setText("yes");
		labels = new Label[size][size];
		puzzleSize = size;
	
		//Used to define the size of the labels. 
		String amountOfDigits = Integer.toString(size*size);
		double dSize = setLabelSideLength(amountOfDigits);
		Font labelFont = new Font(dSize-20);
		
		//Sets properties for each label in labels[][] with the text of the    
		//current count, starting with 1.
		int count = 1;
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				labels[i][j] = new Label(Integer.toString(count));
				labels[i][j].setMinSize(dSize, dSize);
				labels[i][j].setAlignment(Pos.CENTER);
				//If the game board is 10 or less wide, the font scales with the labelSize.
				if (size <= 10){
					labels[i][j].setFont(labelFont);
				}
				count++;
			}
		}
		
		//Sets the bottom right labels' text to "0" and adds the css style which makes it invisible.
		labels[size-1][size-1].setText("0");
		labels[size-1][size-1].getStyleClass().add("label-zero");
		
		//Sets the first 3 labels in sequence 2, 3, 1
		Label holder1 = labels[0][0];
		Label holder2 = labels[0][1];
		labels[0][0] = labels[0][2];	labels[0][2] = holder1;
		labels[0][1] = labels[0][0];	labels[0][0] = holder2;
		
		//Sets the mouseClickAction for every label in our labels array (called labels)
		for (int i = 0; i<labels.length; i++){
			for (int j = 0; j<labels.length; j++){
				setMouseClickAction(i, j);
			}
		}
		//Initiates the point variable that is essential in our randomMove() method. Explained in the method.
		zeroPos.setLocation(size-1, size-1);
		moveCount.setText("0");
	}
	
	/*
	 * This method is called when a key is pressed. opposite key. e.g up becomes down.
	 * it get's the x, y of the label with text "0" and moves the label in the direction.
	 */
	public void keyMove(String command){
		int x = zeroPos.x;
		int y = zeroPos.y;
		if 		(command.equals("up")    && y>0){checkMove(x, y-1);}
		else if (command.equals("down")  && y<puzzleSize-1){checkMove(x, y+1);}
		else if (command.equals("left")  && x>0){checkMove(x-1, y);}
		else if (command.equals("right") && x<puzzleSize-1){checkMove(x+1,y);}
		if (winCheck()){
			isPlaying.setText("no");
		}
	}
	/*
	 * Checks the labels in each of the 4 directions from the labels' 
	 * x,y coordinates sent along. It only checks inside our labels[][].
	 * It returns a string with the direction of which the label with "0" is.
	 */
	public String nextToZero(int x, int y){
		//Check up if there is a label "above" the current label in our labels[][]
		if (y > 0) {
			if (labels[y - 1][x].getText().equals("0")) {return "up";}}
		// Check down if there is a label "below" the current label in our labels[][]
		if (y < (puzzleSize - 1)) {
			if (labels[y + 1][x].getText().equals("0")) {return "down";}}
		// Check left if there is a label to the "left of" the current label in our labels[][]
		if (x > 0) {
			if (labels[y][x - 1].getText().equals("0")) {return "left";}}
		// Check right if there is a label to the "right of" the current label in our labels[][]
		if (x < (puzzleSize - 1)) {
			if (labels[y][x + 1].getText().equals("0")) {return "right";}}
		//If not next to zero 
		return "not";
	}
	
	/*
	 * Switches the text of 2 labels. updates the position of the "0". 
	 * It switches the cssStyle that "hides" the label with the text "0".
	 * Lastly it updates both the moves integer and movesCount label.
	 */
	public void switchLabels(int i, int j, int h, int v){
		//Switches the text on the two labels 
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
		//Updates the zerPos point
		zeroPos.setLocation(v, h);
		
		/*
		 * Updates which label to have the "label-zero" css style. 
		 * Functionally it makes the label with the text "0" become invisible.
		 */
		labels[h][v].getStyleClass().add("label-zero");
	    labels[i][j].getStyleClass().remove("label-zero");
	    labels[i][j].getStyleClass().add("label");

	    //Increments moves and updates moveCount.
		int moves = Integer.parseInt(moveCount.getText())+1;
		moveCount.setText(Integer.toString(moves));
	}
	
	/* This method essentially joins the nextToZero method and the switchlabels method. 
	 * First it identifies where the zero-tile is situated compared to the tile the user 
	 * wish to move, and then calls the switchLabels method with the correct argument 
	 */
	public void checkMove(int x, int y){
		String var = nextToZero(x, y);                        

		//Runs through every possible outcome of nextToZero()
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
	 * Runs through every label and checks if their text (which is a number converted to string)
	 * matches that of the count.
	 * It checks if the labels are in "order" from 1 to puzzleSize-1. If just one
	 * of the label are in the wrong place winCheck returns false.
	 */
	public boolean winCheck(){
		/* The basic assumption is that the game is won. We then run through every
		 * possibility that could falsify that assumption. */
		boolean won = true;
		//If the last label = 0 - it's in the correct position.
		if (labels[puzzleSize-1][puzzleSize-1].getText().equals("0")){
			int count = 1;
			//For each label in labels[][]
			for (Label[] l : labels){
				for (Label tmpLabel : l){
					String labelText = tmpLabel.getText();
					
					//If we are looking at the last Label we do not want to do anything since we already checked.
					if (count == puzzleSize*puzzleSize){System.out.println("at the end");}

					//If the Label dosen't matches that of the number of labels we've look through
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
	 * Sets the action to perform when a label is clicked to
	 * check if it can be moved. If so it moves it, and after every move, 
	 * the winCheck() method is called to check if the player has won.
	 */
	public void setMouseClickAction(int y, int x){
		labels[y][x].setOnMouseClicked(e -> {
			checkMove(x, y);
			if (winCheck()){
				isPlaying.setText("no");
			}
		});
	}
	
	/*
	 * A method that calculates the double that defines the label and font size in createLabels.
	 * If puzzleSize <= 10 we want the double to be a puzzleSize'th of the screen. Else we want
	 * to calculate a size based on how many digits the biggest number is. 
	 */
	public double setLabelSideLength(String digits){
		double screenLength = 400;
		if (puzzleSize <= 10){
			return screenLength/puzzleSize;
		}else {return (digits.length()*5)+20;}
	}
	
	/*
	 * Makes puzzleSize*20 random moves. e.g if puzzleSize is 3, it will execute 60 random moves. 
	 */
	public void randomMove(){
		Random rndmNumbGenerator = new Random();
		//We use the position of the "0" to make random moves. We update them throughout the method.
		int x = zeroPos.x;
		int y = zeroPos.y;
		
		//Number of random moves = puzzleSize*20
		for (int i = puzzleSize * 20; i>0; i--){
			int randNumb = rndmNumbGenerator.nextInt(4);
			/*
			 * Each if statement uses the randNumb and the condition if that moves is 'legal'
			 * if we proceed inside a statement we make the move and update x, y that are the
			 * parameters that keeps track of where a move is "legal".
			 */
			if (randNumb == 0 && y!=0 && y<puzzleSize){
				//Moves up
				checkMove(x, y-1);
				y -= 1;
			}else if (randNumb == 1 && y<(puzzleSize-1) && y>=0){
				//Moves down
				checkMove(x, y+1);
				y += 1;
			}else if(randNumb == 2 && x !=0 && x<(puzzleSize)){
				//Moves left
				checkMove(x-1, y);
				x -= 1;
			}else if(x<(puzzleSize-1) && x>=0){	
				//Moves right
				checkMove(x+1, y);
				x += 1;
			}
		}
		// Reset the moveCount to 0. The random moves counts as regular player moves but should not. 
		moveCount.setText("0");
	}
}

