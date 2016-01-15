package puzzleV3;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AboutScene {

	Stage aboutStage;
	Scene aboutScene;
	Button back;
	
	//Constructor
	public AboutScene (Stage window) {
		this.aboutStage = window;
		back = new Button();		
	}
	
	//This method defines all the GUI elements that makes up the aboutScene, e.g. layouts, labels and buttons.
	public void aboutSceneM(){
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    Button back = new Button();
			back.setMaxWidth(40);
			back.getStyleClass().add("button-back");
			
			//back.setOnAction(e -> mainMenuM());
			
			back.setAlignment(Pos.TOP_LEFT);
	    
	    Label dev = new Label("The game developers:");
	    dev.getStyleClass().add("label-fed");
	    Label tekst = new Label("Kristian Wolthers Rasmussen \nPelle Rubin Galløe \nRasmus Suonperä Liebst "
	    		+ "\nJia Johnny Ye");
	    tekst.getStyleClass().add("label-about");
	    
	    grid.add(dev, 0, 0);
	    grid.add(tekst, 0, 1);
	    
	    border.setTop(back);
	    border.setCenter(grid);
		
	    aboutScene = new Scene(border, 750, 750);
		aboutStage.setScene(aboutScene);
		//Fetches the .css-file that styles the GUI elements
		aboutScene.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
}
