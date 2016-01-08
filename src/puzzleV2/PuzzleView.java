package puzzleV2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuzzleView extends Application {
	Stage window;
	Scene scene;
	Scene main;
	
	public GridPane gridPane;
	Label[][] tempLabels = {{new Label("hej"), new Label("med"), new Label("dig")},
							{new Label("hyggeligt"), new Label("at møde"), new Label("dig")},
							{new Label("farvel"), new Label("vi ses en"), new Label("anden gang")}};
	
	Button play;
	VBox box;
	Label[][] labels;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("15-Puzzle");
		
		
		/////// MAIN LAYOUT
		
		box = new VBox();
		play = new Button("Play");
		
		box.getChildren().add(play);
		
		play.setOnAction(e -> PuzzleController);
		
		////// GRIDPANE LAYOUT
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);	 
		
		gridPane = addLabels(gridPane, labels);
		
		updateWindow();
	}
	
	public GridPane addLabels(GridPane gridPane, Label[][] labels){
		for(int i = 0; i < labels.length; i++){
			for(int j = 0; j < labels.length; j++){
				GridPane.setConstraints(labels[i][j], j, i);
				gridPane.getChildren().add(labels[i][j]);
			}
		}
		return gridPane;
	}
	public GridPane removeLabels(GridPane gridPane, Label[][] labels){
		for(int i = 0; i < labels.length; i++){
			for(int j = 0; j < labels.length; j++){
				GridPane.setConstraints(labels[i][j], j, i);
				gridPane.getChildren().remove(labels[i][j]);
			}
		}
		return gridPane;
	}
	public void updateWindow(){
		scene = new Scene(gridPane, 400, 400);
		main = new Scene(box, 400, 400);
		window.setScene(main);
		window.show();
	}
}












