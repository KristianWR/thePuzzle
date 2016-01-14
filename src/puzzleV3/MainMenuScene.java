package puzzleV3;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainMenuScene {
	
	Stage mainStage;
	Scene mainMenu;
	Button btnPlay;
	Button btnHow;
	Button btnExit;
	Button btnAbout;
	
	public MainMenuScene(Stage window){
		btnPlay = new Button("Play");
		btnHow = new Button("How to play");
		btnAbout = new Button("About");
		this.mainStage = window;

	}
	
	public void mainMenuM(){
		
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
		Label titel = new Label("15-Puzzle");
		GridPane.setConstraints(titel, 0, 2);

	    
		GridPane.setConstraints(btnPlay, 0, 5);
		
	    
		GridPane.setConstraints(btnHow, 0, 6);
		
		
		GridPane.setConstraints(btnAbout, 0, 7);
	    
		btnExit = new Button("Exit");
		GridPane.setConstraints(btnExit, 0, 8);
		
/*		btnPlay.setOnAction(e -> sizePickerM());
		
		btnHow.setOnAction(e -> howscreen());
		
		btnAbout.setOnAction(e -> aboutscreen());
		
		*/
		
		btnExit.setOnAction(e -> {Platform.exit();
		});
	    
	    btnPlay.setMaxWidth(Double.MAX_VALUE);
	    btnHow.setMaxWidth(Double.MAX_VALUE);
	    btnAbout.setMaxWidth(Double.MAX_VALUE);
	    btnExit.setMaxWidth(Double.MAX_VALUE);
	    
	    
	    grid.getChildren().addAll(titel, btnPlay, btnHow, btnAbout, btnExit);	
	    
	    mainMenu = new Scene(grid, 700, 700);
	    mainStage.setScene(mainMenu);
	    mainMenu.getStylesheets().add(View.class.getResource("screen1.css").toExternalForm());
	}
	
	public Button getBtnPlay(){
		return btnPlay;
	}
	
	/*
	public Button getBtnHow(){
		return btnHow;
	}
	*/
	public Button getBtnAbout(){
		return btnAbout;
	}
	
	public Scene getMainScene(){
		return mainMenu;
	}
}
