package puzzleV3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SizePickerScene {
	Stage sizeStage;
	Scene sizePicker;
	int initSize;
	CheckBox cb1;
	Button goBack;
	Button go;
	TextField sizePrompt;
	
	public SizePickerScene(Stage window){
		this.sizeStage = window;
		
		//Creates two new buttons
		goBack = new Button();
		go = new Button("Start");
	}

public void sizePickerM(){
		
		//Creates gridpane
		GridPane grid2 = new GridPane();
		grid2.setHgap(10);
		grid2.setVgap(20);
		grid2.setAlignment(Pos.TOP_CENTER);
		
		BorderPane borderP = new BorderPane();
		
		sizePicker = new Scene(borderP, 700, 700);
		
		Label chooseSize = new Label("Choose the size of the game");
		chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		//Adds choosesize with location on grid
		GridPane.setConstraints(chooseSize, 1, 2);
		
		//Creates a new Textfield
		sizePrompt = new TextField();
		sizePrompt.setPromptText("E.g. 3");
		
		//Adds sizeprompt with location on grid
		GridPane.setConstraints(sizePrompt, 1, 3);
		
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		
		//Adds sizeInfo with location on grid
		GridPane.setConstraints(sizeInfo, 1, 4);
		
		//Adds go with location on grid
		GridPane.setConstraints(go, 2, 6);
		
		Label timeInfo = new Label("Enable time pressure");
		
		//Adds timeInfo with location on grid
		GridPane.setConstraints(timeInfo, 1, 5);
		timeInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			
		cb1 = new CheckBox();
		cb1.setText("Time pressure");
		cb1.setTextFill(Color.WHITE);
		cb1.setOnKeyPressed(e -> {

				try{
					initSize = Integer.parseInt(sizePrompt.getText());
					if ( initSize < 3 || initSize > 100){
						System.out.println("not between 3-100");
						sizeInfo.setText("Please enter a size first!");			
					}
				}catch (NumberFormatException ex){
					System.out.println("not an int");
					sizeInfo.setText("Please enter a size first!");
				}
		});
		//Adds cb1  with location on grid
		GridPane.setConstraints(cb1, 1, 6);
		
		//Adds goback with location on grid
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setMaxWidth(40);
		goBack.getStyleClass().add("button-back");

		//Adds elements to grid
		grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, cb1, timeInfo, go);
		borderP.setPadding(new Insets(15,15,15,15));
		borderP.setTop(goBack);
		borderP.setCenter(grid2);
		sizeStage.setScene(sizePicker);
	
		//Tells eclipse to look in the same package as view and use screen2
		sizePicker.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	//This is a get-method which returns the sizepicker
	public Button getGoBackButton(){
		return goBack;
	}
	//This is a get-method which returns the sizepicker
	public Scene getSizeScene(){
		return sizePicker;
	}
	//This is a get-method which returns the go
	public Button getGoButton(){
		return go;
	}
	//This is a get-method which returns the initSize
	public int getInitSize(){
		return initSize;
	}
    //This is a get-method which returns the cb1
	public CheckBox getCheckBox(){
		return cb1;
	}
	//This is a get-method which returns the sizePrompt
	public TextField getSizePrompt(){
		return sizePrompt;
	}
}
