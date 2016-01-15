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

	public AlertBox() {
		mainMenuButton = new Button("Main Menu");
		restartButton = new Button("Try again");
		window = new Stage();

		// Forces you to handle the pop-up window
		window.initModality(Modality.APPLICATION_MODAL);
	}

	public void display(String title, String message) {
		Label label = new Label();
		VBox layout = new VBox(10);
		Scene scene = new Scene(layout, 250, 250);

		label.setText(message);

		// Adds elements to the VBox layout
		layout.getChildren().addAll(label, restartButton, mainMenuButton);
		layout.setAlignment(Pos.CENTER);

		window.setTitle(title);
		window.setMinWidth(250);
		window.setMinHeight(250);
		window.setScene(scene);
		window.show();

		//Tells the window to exit program when close is clicked
		window.setOnCloseRequest(e -> Platform.exit());

		// Tells Eclipse to look in the same package as view and use the Alert.css file
		scene.getStylesheets().add(View.class.getResource("Alert.css").toExternalForm());
	}

	// Getter methods
	public Button getMainMenuButton() {
		return mainMenuButton;
	}

	public Button getRestartButton() {
		return restartButton;
	}

	public Stage getAlertBoxWindow() {
		return window;
	}

}