package puzzleVersion1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application{

	Stage window;
	Scene scene1;
	Controller kontrol;
	public static String btnID; 

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		
		window.setTitle("15-Puzzle");
		
		kontrol = new Controller(3);
		Button[][] btn1 = kontrol.currentPuzzle.getButtons();
		
	    
	    ////  TOP MENU ////////
	    
	    HBox topMenu = new HBox();
	    Label label1 = new Label("THIS IS THE TOP BAR");
	    topMenu.getChildren().add(label1);
	    
	    ///// LEFT SIDE ////////
	    
	    VBox leftSide = new VBox();
	    Label label2 = new Label("THIS IS LEFT SIDE");
	    leftSide.getChildren().add(label2);
	    
	    ////// GRIDPANE LAYOUT CENTER IN BORDERPANE /////////
	    
	    GridPane layout1 = new GridPane();
	    layout1.setPadding(new Insets(5,5,5,5));
	    layout1.setHgap(5);
	    layout1.setVgap(5);
	    
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				GridPane.setConstraints(btn1[i][j], j, i);
				layout1.getChildren().add(btn1[i][j]);
				btnID = btn1[i][j].getText();
				btn1[i][j].setOnAction(e -> {
					kontrol.checkMove(btnID);
				});
			}
		}
	    
	    
	    //// RIGHT SIDE ///////////
	    VBox rightSide = new VBox();
	    Label label3 = new Label("THIS IS RIGHT SIDE");
	    rightSide.getChildren().add(label3);
	    
	    //// BOTTOM BAR //////////
	    
	    HBox bottomMenu = new HBox();
	    Label label4 = new Label("THIS IS THE BOTTOM BAR");
	    bottomMenu.getChildren().add(label4);
	    
	    
	    //////  BORDERPANE LAYOUT ////////
	    
	    BorderPane borderPane = new BorderPane();
	    borderPane.setTop(topMenu);
	    borderPane.setLeft(leftSide);
	    borderPane.setCenter(layout1);
	    borderPane.setRight(rightSide);
	    borderPane.setBottom(bottomMenu);
	   
		
		scene1 = new Scene(borderPane,350,350);
		
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








