package puzzleV3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AboutScene {

	private Stage aboutStage;
	private Scene aboutScene;
	public Button back;
	
	//Constructor
	public AboutScene (Stage window) {
		this.aboutStage = window;
		back = new Button();		
	}
	
	//This method defines all the GUI elements that makes up the aboutScene, e.g. layouts, labels and buttons.
	public void aboutSceneM(){
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		Label dev = new Label("The game developers:");
		Label tekst = new Label("Kristian Wolthers Rasmussen (s154121) \nPelle Rubin Galloee (s153742) \nRasmus Liebst (s144483) "
	    		+ "\nJia Johnny Ye (s154074)");
		
		//Sets the horizontal and vertical gap between grids and sets the position of the grid
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    // Adds the id references to the labels. This id is called in the css file (screen2.css)
	    dev.getStyleClass().add("label-fed");
	    tekst.getStyleClass().add("label-about");
	    
	    //Adds elements to grid with the given location
	    grid.add(dev, 0, 0);
	    grid.add(tekst, 0, 1);
	    
	    // Sets the style, max size and position of the back button
		back.setMaxWidth(40);
		back.getStyleClass().add("button-back");
		back.setAlignment(Pos.TOP_LEFT);
	    
	    border.setTop(back);
	    border.setCenter(grid);
	    border.setPadding(new Insets(15,15,15,15));
		
	    aboutScene = new Scene(border, 750, 750);
		aboutStage.setScene(aboutScene);
		
		//Fetches the .css-file that styles the GUI elements from the same place where View class is
		aboutScene.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	
}