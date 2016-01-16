package puzzleV3;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuScene {
	
	private Stage mainStage;
	private Scene mainMenu;
	private Button btnPlay;
	private Button btnHow;
	private Button btnExit;
	private Button btnAbout;

	public MainMenuScene(Stage window){
		btnPlay = new Button("Play");
		btnHow = new Button("How to play");
		btnAbout = new Button("About");
		this.mainStage = window;

	}
	
	public void mainMenuM(){
		
		GridPane grid = new GridPane();
		Label titel = new Label("15-Puzzle");
		btnExit = new Button("Exit");
		
		//Sets the horizontal and vertical gap between grids and sets the position of the grid
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    //Sets the given elements in the to grid at the given location
		GridPane.setConstraints(titel, 0, 2);
		GridPane.setConstraints(btnPlay, 0, 5);
		GridPane.setConstraints(btnHow, 0, 6);
		GridPane.setConstraints(btnAbout, 0, 7);
		GridPane.setConstraints(btnExit, 0, 8);
		
		//The Exit button closes the program
		btnExit.setOnAction(e -> Platform.exit());
	    
		//Sets all the buttons to same width and height
	    btnPlay.setMaxWidth(Double.MAX_VALUE);
	    btnHow.setMaxWidth(Double.MAX_VALUE);
	    btnAbout.setMaxWidth(Double.MAX_VALUE);
	    btnExit.setMaxWidth(Double.MAX_VALUE);
	    
	    //Adds all the elements to the grid layout
	    grid.getChildren().addAll(titel, btnPlay, btnHow, btnAbout, btnExit);	
	    
	    mainMenu = new Scene(grid, 700, 700);
	    mainStage.setScene(mainMenu);
	    
		//Fetches the .css-file that styles the GUI elements from the same place where view class is
	    mainMenu.getStylesheets().add(Controller.class.getResource("screen1.css").toExternalForm());
	}
	
	// Getter methods
	public Scene getMainMenu() {
		return mainMenu;
	}
	
	public Button getBtnPlay() {
		return btnPlay;
	}
	
	public Button getBtnHow() {
		return btnHow;
	}
	
	public Button getBtnExit() {
		return btnExit;
	}
	
	public Button getBtnAbout() {
		return btnAbout;
	}
}
