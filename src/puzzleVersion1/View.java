package puzzleVersion1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class View extends Application{

	Stage window;
	Scene scene1;
	Controller kontrol;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		
		window.setTitle("Hello");
		
		GridPane layout1 = new GridPane();
		layout1.setPadding(new Insets(5));
	    layout1.setHgap(5);
	    layout1.setVgap(5);
	    ColumnConstraints column1 = new ColumnConstraints(100);
	    ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
	    column2.setHgrow(Priority.ALWAYS);
	    layout1.getColumnConstraints().addAll(column1, column2);
	   
		
		
		kontrol = new Controller(3);
		Button[][] btn1 = kontrol.currentPuzzle.getButtons();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				btn1[i][j].setLayoutX(i);
				btn1[i][j].setLayoutY(j);
				layout1.getChildren().add(btn1[i][j]);
			}
		}
		
		scene1 = new Scene(layout1,200,200);
		
		window.setScene(scene1);
		window.show();

	}

}


















/// First scene 

/*		Label label1 = new Label("This is scene 1");
		Button button1 = new Button("Click to go to scene 2!");
		button1.setOnAction(e -> window.setScene(scene2));
		
		VBox layout1 = new VBox();
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1,200,200);
		
		/// Second scene
		
		Label label2 = new Label("This is scene 2");
		Button button2 = new Button("Click to go back to scene 1");
		button2.setStyle("-fx-background-color: #ffff33;");
		button2.setOnAction(e -> window.setScene(scene1));
		
		HBox layout2 = new HBox();
		layout2.setSpacing(10);
		layout2.setStyle("-fx-background-color: #ff4d4d;");
		layout2.getChildren().addAll(label2, button2);
		scene2 = new Scene(layout2, 500,500);*/








