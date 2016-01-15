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
		goBack = new Button();
		go = new Button("Start");
	}

public void sizePickerM(){
		
		GridPane grid2 = new GridPane();
		grid2.setHgap(10);
		grid2.setVgap(20);
		grid2.setAlignment(Pos.TOP_CENTER);
		
		BorderPane borderP = new BorderPane();
		
		sizePicker = new Scene(borderP, 700, 700);
		
		Label chooseSize = new Label("Choose the size of the game");
		chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		GridPane.setConstraints(chooseSize, 1, 2);
		
		sizePrompt = new TextField();
		sizePrompt.setPromptText("E.g. 3");
		GridPane.setConstraints(sizePrompt, 1, 3);
		
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		GridPane.setConstraints(sizeInfo, 1, 4);
		
/*		sizePrompt.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				try{
					initSize = Integer.parseInt(sizePrompt.getText());
					if ( initSize < 3 || initSize > 100){
						System.out.println("not between 3-100");
						sizeInfo.setText("must be between 3-100");
					}else{
						System.out.println("IT WORKS");
						//gameSceneM();					
					}
				}catch (NumberFormatException ex){
					System.out.println("not a int");
					sizeInfo.setText("Must be an integer");
				}
			}
		});*/
		
		
		
		GridPane.setConstraints(go, 2, 6);
		
		Label timeInfo = new Label("Enable time pressure");
		GridPane.setConstraints(timeInfo, 1, 5);
		timeInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			
		cb1 = new CheckBox();
		cb1.setText("Time pressure");
		cb1.setTextFill(Color.WHITE);
		cb1.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				try{
					initSize = Integer.parseInt(sizePrompt.getText());
					if ( initSize < 3 || initSize > 100){
						System.out.println("not between 3-100");
						sizeInfo.setText("Please enter a size first!");
					}else{
						//gameSceneM();					
					}
				}catch (NumberFormatException ex){
					System.out.println("not an int");
					sizeInfo.setText("Please enter a size first!");
				}
			}
		});
		
		GridPane.setConstraints(cb1, 1, 6);
		
		
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setMaxWidth(40);
		goBack.getStyleClass().add("button-back");
		//goBack.setOnAction(e -> mainMenuM());
		
		grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, cb1, timeInfo, go);
		borderP.setPadding(new Insets(15,15,15,15));
		borderP.setTop(goBack);
		borderP.setCenter(grid2);
		sizeStage.setScene(sizePicker);
		sizePicker.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}

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
