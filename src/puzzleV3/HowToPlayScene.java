package puzzleV3;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HowToPlayScene {
	
	Stage howStage;
	Scene howScene;
	Button back;
	
	//Constructor
	public HowToPlayScene(Stage window){
		this.howStage= window;
		back = new Button();
	}
	
	//This method defines all the GUI elements that makes up the howScene, e.g. layouts, labels and buttons.
	public void howSceneM(){
		BorderPane border=new BorderPane();
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    Label tut= new Label("Tutorial:");
	    tut.getStyleClass().add("label-fed");
	    Label htp= new Label(" You control the tiles either with the arrow keys or the mouse. \n " +
	    " If you enable the timer, you will lose when the time runs out");
	    htp.getStyleClass().add("label-about");
	    
	    Label win= new Label("Win condition:");
	    win.getStyleClass().add("label-fed");
	    Label tekst=new Label(" You win the game when the tiles \n are arranged"
	    		+ " in ascending order \n with the empty tile in the lower right corner");
	    tekst.getStyleClass().add("label-about");
	    
	    grid.add(tut, 0, 0);
	    grid.add(htp, 0, 1);
	    grid.add(win, 0, 2);
	    grid.add(tekst, 0, 3);
	    
		back.setMaxWidth(40);
		back.getStyleClass().add("button-back");
		back.setAlignment(Pos.TOP_LEFT);
	
		border.setTop(back);
		border.setCenter(grid);
	    howScene = new Scene(border, 750, 750);
		howStage.setScene(howScene);
		howScene.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
}
