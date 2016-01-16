package puzzleV3;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Platform;
import javafx.geometry.*;

public class AlertBox {

	private Button mainMenuButton;
	private Button restartButton;
	private Stage window;

	public AlertBox() {
		mainMenuButton = new Button("Main Menu");
		restartButton = new Button("Try again");
		setWindow(new Stage());

		// Forces you to handle the pop-up window
		getWindow().initModality(Modality.APPLICATION_MODAL);
	}

	public void display(String title, String message) {
		Label label = new Label();
		VBox layout = new VBox(10);
		Scene scene = new Scene(layout, 250, 250);

		label.setText(message);

		// Adds elements to the VBox layout
		layout.getChildren().addAll(label, restartButton, mainMenuButton);
		layout.setAlignment(Pos.CENTER);

		getWindow().setTitle(title);
		getWindow().setMinWidth(250);
		getWindow().setMinHeight(250);
		getWindow().setScene(scene);
		getWindow().show();

		//Tells the window to exit program when close is clicked
		getWindow().setOnCloseRequest(e -> Platform.exit());

		// Tells Eclipse to look in the same package as controller and use the Alert.css file
		scene.getStylesheets().add(Controller.class.getResource("Alert.css").toExternalForm());
	}

	// Getter methods
	public Button getMainMenuButton() {
		return mainMenuButton;
	}

	public Button getRestartButton() {
		return restartButton;
	}

	public Stage getAlertBoxWindow() {
		return getWindow();
	}

	public Stage getWindow() {
		return window;
	}

	public void setWindow(Stage window) {
		this.window = window;
	}

}