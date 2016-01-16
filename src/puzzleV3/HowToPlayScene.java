package puzzleV3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HowToPlayScene {
	
	private Stage howStage;
	private Scene howScene;
	private Button back;
	
	//Constructor
	public HowToPlayScene(Stage window){
		this.howStage= window;
		back = new Button();
	}
	
	//This method defines all the GUI elements that makes up the howScene, e.g. layouts, labels and buttons.
	public void howSceneM(){
		BorderPane border=new BorderPane();
		GridPane grid = new GridPane();
	    Label tut = new Label("Tutorial:");
	    Label htp = new Label(" You control the tiles either with the mouse or with the WASD keys. \n " +
	    		" If you enable the timer, you will lose when the time runs out");
	    Label win = new Label("Win condition:");
	    Label tekst = new Label(" You win the game when the tiles \n are arranged" +
	    						" in ascending order \n with the empty tile in the lower right corner");
	    
	    //Sets the horizontal and vertical gap between grids and sets the position of the grid
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    // Adds the id references to the labels. This id is called in the css file (screen2.css)
	    tut.getStyleClass().add("label-fed");
	    htp.getStyleClass().add("label-about");
	    win.getStyleClass().add("label-fed");
	    tekst.getStyleClass().add("label-about");
	    
	    //Adds elements to grid with the given location
	    grid.add(tut, 0, 0);
	    grid.add(htp, 0, 1);
	    grid.add(win, 0, 2);
	    grid.add(tekst, 0, 3);
	    
	    // Sets the style, max size and position of the back button
		back.setMaxWidth(40);
		back.getStyleClass().add("button-back");
		back.setAlignment(Pos.TOP_LEFT);
	
		//Adds elements to border and afterwards border to the grid
		border.setTop(back);
		border.setCenter(grid);
		border.setPadding(new Insets(15,15,15,15));
	    howScene = new Scene(border, 750, 750);
		howStage.setScene(howScene);
		
		//Fetches the .css-file that styles the GUI elements from the same place where Controller class is
		howScene.getStylesheets().add(Controller.class.getResource("screen2.css").toExternalForm());
	}
	
	// Getter method
	public Button getBack() {
		return back;
	}
}
