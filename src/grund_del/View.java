package grund_del;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class View extends Application{
	private Scene game;
	private Model m;
	// Game board 2-d array for the labels.
	private Label[][] labels;
	private int initSize;
	private TextField sizePrompt;
	
	//Main method (launches program)
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			//Declares different UI objects used in the gameScene.
			GridPane grid2 = new GridPane();
			Scene sizePicker= new Scene(grid2, 700, 700);
			Label chooseSize = new Label("Choose the size of the game");
			Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
			Button go = new Button("start");
			
			//The textfield where a user enters the game size
			sizePrompt = new TextField();
			
			chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			
			//Sets the given elements in the to grid at the given location
			GridPane.setConstraints(chooseSize, 1, 2);
			GridPane.setConstraints(sizePrompt, 1, 3);
			GridPane.setConstraints(sizeInfo, 1, 4);
			GridPane.setConstraints(go, 2, 6);
			
			go.setOnMouseClicked(e-> gameSceneM());

			//Adds elements to grid pane 
			grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, go);
			grid2.setAlignment(Pos.TOP_CENTER);
			
			primaryStage.setScene(sizePicker);
			primaryStage.show();
}
	
	//Adds the labels to the gridpane
	private GridPane addLabels(GridPane gridPane, Label[][] tempLabels){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().add(tempLabels[i][j]);
					}
			}
		return gridPane;
	}
	
	/*
	 * This method constructs the GUI of the game scene
	 */
	private void gameSceneM(){
		
		Stage window = new Stage();
		GridPane gridPane = new GridPane();
		ScrollPane scroll1 = new ScrollPane(gridPane);
		GridPane mainGrid= new GridPane();
		initSize = Integer.parseInt(sizePrompt.getText());
		game = new Scene(mainGrid, 1000, 1000);
		
		//Makes an instance of model
		m = new Model();
		m.createLabels(initSize);
		labels = Model.getLabels();
		
		//Adds the generated labels to grid pane
		gridPane =addLabels(gridPane, labels);
		gridPane.setMaxSize(600, 600);
		gridPane.setAlignment(Pos.CENTER);
		
		scroll1.setFitToWidth(true);
	
		//Adds game and scroll pane to the main grid pane
		mainGrid.setAlignment(Pos.CENTER);
		mainGrid.add(gridPane, 1, 1);
		mainGrid.add(scroll1, 0, 1);
		
		addArrowKeyListener();
		
		//Sets the shown scene to the scene game and makes the stage window visible
		window.setScene(game);
		window.show();
		
	}
	
	/*
	 * This method adds the key listeners to the buttons WASD
	 */
	private void addArrowKeyListener(){
		game.setOnKeyPressed(e ->{
			if		(e.getCode() == KeyCode.W){m.keyMove("down");}
			else if (e.getCode() == KeyCode.S){m.keyMove("up");}
			else if (e.getCode() == KeyCode.A){m.keyMove("right");}
			else if (e.getCode() == KeyCode.D){m.keyMove("left");}
		}); 
	}
	
	
}