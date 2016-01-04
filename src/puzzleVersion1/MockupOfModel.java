package puzzleVersion1;

import java.awt.Point;
import java.util.Scanner;

public class MockupOfModel {
	public static boolean won = false;
	public static Point blank = new Point(2, -2);
	public static int[] numb = {2, 3, 1, 4, 5, 6, 7, 8, 0};
	public static Point[] numbPos = {  new Point(0, 0), new Point(1, 0), new Point(2, 0), 
									new Point(0, -1), new Point(1, -1), new Point(2, -1),
									new Point(0, -2), new Point(1, -2), new Point(2, -2)
									};
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		while(!won){
			drawBoard();
			int move = input.nextInt();
			System.out.println(doMove(move));
			//isWon();
		}
		
		input.close();
	}
	
	public static void drawBoard(){
		System.out.println("*******");
		System.out.println("*" + numb[0] + "*" + numb[1] + "*" + numb[2] + "*");
		System.out.println("*" + numb[3] + "*" + numb[4] + "*" + numb[5] + "*");
		System.out.println("*" + numb[6] + "*" + numb[7] + "*" + numb[8] + "*");
		System.out.println("*******");
	}
	
	public static boolean doMove(int move){
		for (int i = 0; i < 9; i++){
			if (numb[i] == move){
				String theMove = validMove(i);
				if(theMove.equals("left")){
					aMove(i, i-1, move);
				}else if(theMove.equals("right")){
					aMove(i, i+1, move);
				}else if(theMove.equals("down")){
					aMove(i, i+3, move);
				}else if(theMove.equals("up")){
					aMove(i, i-3, move);
				}else if(theMove.equals("none")){
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public static String validMove(int pos){
		Point tempPos = new Point(numbPos[pos].x, numbPos[pos].y);
		//point to left.
		tempPos.setLocation(numbPos[pos].x - 1, numbPos[pos].y);
		if (blank.equals(tempPos)){return "left";}
		
		//point to right
		tempPos.setLocation(numbPos[pos].x + 1, numbPos[pos].y);
		if (blank.equals(tempPos)){return "right";}
		
		//point at down
		tempPos.setLocation(numbPos[pos].x, numbPos[pos].y - 1);
		if (blank.equals(tempPos)){return "down";}
		
		//point at up
		tempPos.setLocation(numbPos[pos].x, numbPos[pos].y +1);
		if (blank.equals(tempPos)){return "up";}
		return "none";
	}
	
	public static void aMove(int i, int b, int move){
		numb[i] = 0;
		numb[b] = move;
		blank.setLocation(numbPos[i]);
	}
}
