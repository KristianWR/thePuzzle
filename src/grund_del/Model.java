package grund_del;

import java.awt.Point;

import javafx.geometry.Pos;
import javafx.scene.control.Label;

public class Model {
	
    private static Label[][] labels;
	private int puzzleSize;
	
	//A point that keeps track of the label with the text "0". used in our randomMove().
	private Point zeroPos = new Point(0,0);
	
	
	/*
	 *Creates the 2 dimensional labelArray that represents the game board.
	 *It scales the label size to be optimal with our scene. It changes the 
	 *first 3 labels to be in order 2, 3, 1 and 0 to be the in the bottom
	 *right corner. and "hides" the label with text "0".  
	 */
	public void createLabels(int size){
		//Initializes the label array with the correct dimensions.
		labels = new Label[size][size];
		puzzleSize = size;
	
		//Used to define the size of the labels. 
		String amountOfDigits = Integer.toString(size*size);
		double dSize = (amountOfDigits.length()*5)+20;
		
		//Sets properties for each label in labels[][] with the text of the
		//Current count, starting with 1.
		int count = 1;
		for (int i = 0; i<size; i++){
			for (int j = 0; j<size; j++){
				labels[i][j] = new Label(Integer.toString(count));
				labels[i][j].setMinSize(dSize, dSize);
				labels[i][j].setAlignment(Pos.CENTER);
				count++;
			}
		}
		
		//Sets the bottom right labels' text to "0" and adds the css style
		labels[size-1][size-1].setText("0");
		labels[size-1][size-1].setVisible(false);

		
		//Sets the first 3 labels in sequence 2, 3, 1
		Label holder1 = labels[0][0];
		Label holder2 = labels[0][1];
		labels[0][0] = labels[0][2];	labels[0][2] = holder1;
		labels[0][1] = labels[0][0];	labels[0][0] = holder2;
		
		//Important variable for the randomMove() method.
		zeroPos.setLocation(size-1, size-1);
	}
	
	/*
	 * Checks if the label at the given x,y in labels is next to zero.
	 * It returns where the zero is relative to the given values.
	 */
	private void checkMove(int x, int y){
		String var = nextToZero(x, y);

		//Runs through every possible outcome of nextToZero()
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
	}
	/*
	 * Checks the labels in each of the 4 directions from the labels' 
	 * x,y coordinates sent along. It only checks inside our labels[][].
	 * It returns a string with the direction of which the label with "0" is.
	 */
	private String nextToZero(int x, int y){
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
	private void switchLabels(int i, int j, int h, int v){
		//Switches the text on the two labels 
		String temp = labels[i][j].getText();
		labels[i][j].setText(labels[h][v].getText());
		labels[h][v].setText(temp);
		//Updates the zerPos point
		zeroPos.setLocation(v, h);
		
		/*
		 * Functionally it makes the label with the text "0" become invisible and updates visibility.
		 */
		labels[h][v].setVisible(false);
	    labels[i][j].setVisible(true);
	}
	
	// Getter method
	public static  Label[][] getLabels(){
		return labels;
	}
	
}
	

