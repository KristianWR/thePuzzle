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
	Scene game;
	model m;
	Label[][] labels;
	int initSize;
	TextField sizePrompt;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		//Creates gridpane
				GridPane grid2 = new GridPane();
				grid2.setAlignment(Pos.TOP_CENTER);
				
				Scene sizePicker= new Scene(grid2, 700, 700);

				Label chooseSize = new Label("Choose the size of the game");
				chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
				
				//Adds choosesize with location on grid
				GridPane.setConstraints(chooseSize, 1, 2);
				
				sizePrompt = new TextField();
				
				//Adds sizeprompt with location on grid
				GridPane.setConstraints(sizePrompt, 1, 3);
				
				Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
				
				//Adds sizeInfo with location on grid
				GridPane.setConstraints(sizeInfo, 1, 4);
				
				//Creates buttons with location on grid
				Button go=new Button("start");
				GridPane.setConstraints(go, 2, 6);
				
				go.setOnMouseClicked(e->{
					
					//initSize = Integer.parseInt(sizePrompt.getText());
					gameSceneM();					

					
				});

				//Adds elements to grid
				grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, go);
				primaryStage.setScene(sizePicker);
				primaryStage.show();
}
	
	//Creates all the labels on gridpane
	public GridPane addLabels(GridPane gridPane, Label[][] tempLabels){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().add(tempLabels[i][j]);
					}
			}
		return gridPane;
	}
		
	public void gameSceneM(){
		
		//Makes an instance of model
		Stage window = new Stage();
		initSize = Integer.parseInt(sizePrompt.getText());
		m= new model();
		m.createLabels(initSize);
		labels= model.getLabels();
		
		//Adds the generated labels to gridpane
		GridPane gridPane = new GridPane();
		gridPane =addLabels(gridPane, labels);
		gridPane.setMaxSize(600, 600);
		gridPane.setAlignment(Pos.CENTER);
		
		//// SCROLLPANE --- The layout that has the scroll feature for the game layout ///
		
		ScrollPane scroll1 = new ScrollPane(gridPane);
		scroll1.setFitToWidth(true);
	
		//Adds game and scrollpane to new gridpane
		GridPane mainGrid= new GridPane();
		mainGrid.setAlignment(Pos.CENTER);
		mainGrid.add(gridPane, 1, 1);
		mainGrid.add(scroll1, 0, 1);
		
		game=new Scene(mainGrid, 1000, 1000);
		addArrowKeyListener();
		window.setScene(game);
		window.show();
		
	}
	
	public void addArrowKeyListener(){
		game.setOnKeyPressed(e ->{
			if		(e.getCode() == KeyCode.W){m.keyMove("down");}
			else if (e.getCode() == KeyCode.S){m.keyMove("up");}
			else if (e.getCode() == KeyCode.A){m.keyMove("right");}
			else if (e.getCode() == KeyCode.D){m.keyMove("left");}
		}); 
	}
	
	
}