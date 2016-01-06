package puzzleVersion1;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application{

	private MenuBar buildMenuBarWithMenus(ReadOnlyDoubleProperty menuWidthProperty)
	   {
MenuBar menuBar = new MenuBar();

//Prepare left-most 'File' drop-down menu
Menu fileMenu = new Menu("File");
fileMenu.getItems().add(new MenuItem("New"));
fileMenu.getItems().add(new MenuItem("Open"));
fileMenu.getItems().add(new MenuItem("Save"));
fileMenu.getItems().add(new MenuItem("Save As"));
fileMenu.getItems().add(new SeparatorMenuItem());
fileMenu.getItems().add(new MenuItem("Exit"));
menuBar.getMenus().add(fileMenu);

//Prepare 'Examples' drop-down menu
Menu examplesMenu = new Menu("JavaFX Exempler");
examplesMenu.getItems().add(new MenuItem("Text Exampel"));
examplesMenu.getItems().add(new MenuItem("Hej"));
examplesMenu.getItems().add(new MenuItem("Hej Pelle"));
menuBar.getMenus().add(examplesMenu);

//Prepare 'Help' drop-down menu
Menu helpMenu = new Menu("Help");
MenuItem searchMenuItem = new MenuItem("Søg");
helpMenu.getItems().add(searchMenuItem);
MenuItem onlineManualMenuItem = new MenuItem("Pilsner");
helpMenu.getItems().add(onlineManualMenuItem);
helpMenu.getItems().add(new SeparatorMenuItem());

menuBar.getMenus().add(helpMenu);
return menuBar;
	   }
	
	
	Stage window;
	Scene scene1;
	Controller kontrol;
	Button[][] buttons;
	int[][] rowCol;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		
		window.setTitle("15-Puzzle");
		
		kontrol = new Controller(3);
		buttons = kontrol.currentPuzzle.getButtons();
	    
	    ///// LEFT SIDE ////////
	    
	    VBox leftSide = new VBox();
	    Label label2 = new Label("THIS IS LEFT SIDE");
	    leftSide.getChildren().add(label2);
	    
	    ////// GRIDPANE LAYOUT CENTER IN BORDERPANE /////////
	    
	    GridPane layout1 = new GridPane();
	    layout1.setPadding(new Insets(5,5,5,5));
	    layout1.setHgap(5);
	    layout1.setVgap(5);
	    	
	    layout1= addButtons(layout1);

	    
	    
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
	    MenuBar menuBar = buildMenuBarWithMenus(window.widthProperty());
	    borderPane.setTop(menuBar);
	    borderPane.setLeft(leftSide);
	    borderPane.setCenter(layout1);
	    borderPane.setRight(rightSide);
	    borderPane.setBottom(bottomMenu);
	   
		
		scene1 = new Scene(borderPane,350,350);
		
		window.setScene(scene1);
		window.show();

	}
	
	public GridPane addButtons(GridPane layout1){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				GridPane.setConstraints(buttons[i][j], j, i);
				layout1.getChildren().add(buttons[i][j]);
				buttons[i][j].setOnAction(e -> {
					Object obj = e.getSource();
					if (obj instanceof Button){
						kontrol.checkMove(((Button) obj).getText());
						removeButtons(buttons, layout1);
						addButtons(layout1);
					}
				});
			}
		}
		return layout1;
	}
	
	public GridPane removeButtons(Button[][] btn1, GridPane layout1){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				layout1.getChildren().remove(btn1[i][j]);
			}
		}
		return layout1;
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








