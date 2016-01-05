package puzzleVersion1;

import javafx.scene.control.Button;

public class Test {
		public static Controller kontrol;
	public static void main(String[] args){
		
		kontrol = new Controller(3);
		printPuzzle();
	}
	
	public static void printPuzzle(){
		Button[][] btns = kontrol.currentPuzzle.getButtons();
		System.out.println("*******");
		System.out.println("*" + btns[0][0].getText() + "*" + btns[0][1].getText() + "*" + btns[0][2].getText() + "*");
		System.out.println("*" + btns[1][0].getText() + "*" + btns[1][1].getText() + "*" + btns[1][2].getText() + "*");
		System.out.println("*" + btns[2][0].getText() + "*" + btns[2][1].getText() + "*" + btns[2][2].getText() + "*");
		System.out.println("*******");
	}
}
