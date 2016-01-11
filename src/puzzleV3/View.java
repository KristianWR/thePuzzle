package puzzleV3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application{

	Stage window;
	Scene mainMenu;
	Scene sizePicker;
	Scene game;
	GridPane gridPane;
	Label[][] labels;
	Controller kontrol;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		////////////// start skærm ////////////////////
		
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);

		mainMenu = new Scene(grid, 1000, 500);
	    
		//titel label
		Label titel = new Label("15-Puzzle");
		GridPane.setConstraints(titel, 0, 2);

	    Button btnplay = new Button("Play");
		GridPane.setConstraints(btnplay, 0, 5);
		
	    Button btnsetting = new Button("Setting");
		GridPane.setConstraints(btnsetting, 0, 6);
		
		Button btnabout = new Button("About");
		GridPane.setConstraints(btnabout, 0, 7);
	    
	    Button btnexit = new Button("Exit");
		GridPane.setConstraints(btnexit, 0, 8);
		
		btnplay.setOnAction(e -> window.setScene(sizePicker));
		
		btnexit.setOnAction(e -> {Platform.exit();
		});
	    
	    btnplay.setMaxWidth(Double.MAX_VALUE);
	    btnsetting.setMaxWidth(Double.MAX_VALUE);
	    btnabout.setMaxWidth(Double.MAX_VALUE);
	    btnexit.setMaxWidth(Double.MAX_VALUE);
	    
	    
	    grid.getChildren().addAll(titel, btnplay, btnsetting, btnabout, btnexit);	

		mainMenu.getStylesheets().add("viper.css");
		
		////// Size scene ///////////
		
		GridPane grid2 = new GridPane();
	    grid2.setHgap(10);
	    grid2.setVgap(20);
	    grid2.setAlignment(Pos.TOP_CENTER);
	    
	    BorderPane borderP = new BorderPane();

		sizePicker = new Scene(borderP, 1000, 500);
	    
		Label chooseSize = new Label("Choose the size of the game");
		GridPane.setConstraints(chooseSize, 5, 2);
		
		TextField sizePrompt = new TextField();
		sizePrompt.setPromptText("E.g. 3");
		GridPane.setConstraints(sizePrompt, 5, 3);
		
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		GridPane.setConstraints(sizeInfo, 5, 4);
		
		Button go = new Button("Start");
		GridPane.setConstraints(go, 6, 3);
		
		Button goBack = new Button("Back");
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setOnAction(e -> window.setScene(mainMenu));
	    
	    grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, go, goBack);
		borderP.setTop(goBack);
		borderP.setCenter(grid2);
	    
		////// GAME //////////
		
		Model temp = new Model();
		kontrol = new Controller(temp);
		kontrol.theModel.createLabels(3);
		labels = kontrol.theModel.getLabels();
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		    	
		gridPane= addLabels(gridPane, labels);
		
		game = new Scene(gridPane, 400, 400);
		
		window = primaryStage;
		window.setTitle("15-Puzzle");
		
		window.setScene(mainMenu);
		window.show();
		
	}
	
	public GridPane addLabels(GridPane gridPane, Label[][] tempLabels){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().add(tempLabels[i][j]);
			}
		}
		return gridPane;
	}
	
	public GridPane removeLabels(Label[][] tempLabels, GridPane gridPane){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().remove(tempLabels[i][j]);
			}
		}
		return gridPane;
	}

}
