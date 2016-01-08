package puzzleV3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application{

	Stage window;
	Scene game;
	GridPane gridPane;
	Label[][] labels;
	Controller kontrol;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Model temp = new Model();
		kontrol = new Controller(temp);
		kontrol.theModel.createLabels(3);
		labels = kontrol.theModel.getLabels();
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.setOnMouseClicked(e -> {
			System.out.println("hejhej");
			kontrol.mouseClick(e.getX(), e.getY());
			removeLabels(labels, gridPane);
			addLabels(gridPane, kontrol.theModel.getLabels());
		});
		    	
		gridPane= addLabels(gridPane, labels);
		
		game = new Scene(gridPane, 400, 400);
		
		window = primaryStage;
		window.setTitle("15-Puzzle");
		
		window.setScene(game);
		window.show();
		
	}
	
	public GridPane addLabels(GridPane gridPane, Label[][] tempLabels){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().add(tempLabels[i][j]);
			}
		}
		return gridPane;
	}
	
	public GridPane removeLabels(Label[][] tempLabels, GridPane gridPane){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().remove(tempLabels[i][j]);
			}
		}
		return gridPane;
	}

}
