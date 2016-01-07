package puzzleV2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PuzzleView extends Application {
	Stage window;
	Scene scene;
	public GridPane gridPane;
	Label[][] tempLabels = {{new Label("hej"), new Label("med"), new Label("dig")},
							{new Label("hyggeligt"), new Label("at møde"), new Label("dig")},
							{new Label("farvel"), new Label("vi ses en"), new Label("anden gang")}};
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("15-Puzzle");
		
		
		////// GRIDPANE LAYOUT
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);	 
		
		gridPane = addLabels(gridPane, tempLabels);
		
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
		
		window.setScene(scene);
		window.show();
	}
}












