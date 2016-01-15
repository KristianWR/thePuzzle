package puzzleV3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SizePickerScene {
	//Variables
	private Stage sizeStage;
	private Scene sizePicker;
	private int initSize;
	private CheckBox cb1;
	private Button goBack;
	private Button go;
	private TextField sizePrompt;
	
	//Constructor
	public SizePickerScene(Stage window){
		this.sizeStage = window;
		
		goBack = new Button();
		go = new Button("Start");
	}

	public void sizePickerM(){
		
		GridPane grid = new GridPane();
		BorderPane borderP = new BorderPane();
		Label chooseSize = new Label("Choose the size of the game");
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		Label timeInfo = new Label("Enable time pressure");
		
		sizePicker = new Scene(borderP, 700, 700);
		sizePrompt = new TextField();
		cb1 = new CheckBox();
		
		timeInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		//This sets the light gray sample text in the text field
		sizePrompt.setPromptText("E.g. 3");
		
		cb1.setText("Time pressure");
		cb1.setTextFill(Color.WHITE);
		
		goBack.setMaxWidth(40);
		goBack.getStyleClass().add("button-back");
		
		//Sets the horizontal and vertical gap between grids and sets the position of the grid
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setAlignment(Pos.TOP_CENTER);
		
		//Sets the given elements in the to grid at the given location
		GridPane.setConstraints(chooseSize, 1, 2);
		GridPane.setConstraints(sizePrompt, 1, 3);
		GridPane.setConstraints(sizeInfo, 1, 4);
		GridPane.setConstraints(go, 2, 6);
		GridPane.setConstraints(timeInfo, 1, 5);
		GridPane.setConstraints(cb1, 1, 6);
		GridPane.setConstraints(goBack, 0, 0);

		//Adds elements to the grid
		grid.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, cb1, timeInfo, go);
		
		//Adds elements to the main layout
		borderP.setTop(goBack);
		borderP.setCenter(grid);
		
		borderP.setPadding(new Insets(15,15,15,15));
		
		sizeStage.setScene(sizePicker);
	
		//Tells Eclipse to look in the same package as view and use screen2
		sizePicker.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	//Getter methods
	public Button getGoBackButton(){
		return goBack;
	}
	
	public Scene getSizeScene(){
		return sizePicker;
	}
	
	public Button getGoButton(){
		return go;
	}
	
	public int getInitSize(){
		return initSize;
	}
	
	public CheckBox getCheckBox(){
		return cb1;
	}
	
	public TextField getSizePrompt(){
		return sizePrompt;
	}
}
