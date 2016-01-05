package puzzleVersion1;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application{

	Stage window;
	Scene scene1;
	Controller kontrol;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		window = primaryStage;
		
		window.setTitle("Hello");
		
		VBox layout1 = new VBox();
		
		
		kontrol = new Controller(3);
		Button[][] btn1 = kontrol.currentPuzzle.getButtons();
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				layout1.getChildren().add(btn1[i][j]);
			}
		}
		
		scene1 = new Scene(layout1,200,200);
		
		window.setScene(scene1);
		window.show();

	}

}
