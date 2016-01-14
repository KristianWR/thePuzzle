package puzzleV3;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.geometry.*;

public class AlertBox {

	Button mainMenuButton;
	Button restartButton;
	Stage window;
	
	public AlertBox(){
		mainMenuButton = new Button();
		restartButton = new Button();
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
	}
	
    public void display(String title, String message) {
       // window = new Stage();

        //Block events to other windows
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        mainMenuButton = new Button("Main Menu");
        
        restartButton = new Button("Try again");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,restartButton, mainMenuButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(e -> Platform.exit());
    }
    
    public Button getMainMenuButton() {
    	return mainMenuButton;
    }
    
    public Button getRestartButton(){
    	return restartButton;
    }
    
    public Stage getAlertBoxWindow(){
    	return window;
    }


}