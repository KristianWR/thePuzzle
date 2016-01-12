package puzzleV3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class View extends Application{

	Stage window;
	Scene mainMenu;
	Scene sizePicker;
	Scene game;
	Scene about;
	Scene setting;
	GridPane gridPane;
	Label[][] labels;
	Controller kontrol;
	int initSize;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("15-Puzzle");
		mainMenuM();
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
	
	
	public void mainMenuM(){
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);

		mainMenu = new Scene(grid, 750, 750);
	    
		//titel label
		Label titel = new Label("15-Puzzle");
		GridPane.setConstraints(titel, 0, 2);

	    Button btnplay = new Button("Play");
		GridPane.setConstraints(btnplay, 0, 5);
		
	    Button btnsetting = new Button("Setting");
		GridPane.setConstraints(btnsetting, 0, 6);
		
		Button btnabout = new Button("About");
		GridPane.setConstraints(btnabout, 0, 7);
	    
	    Button btnexit = new Button("Exit");
		GridPane.setConstraints(btnexit, 0, 8);
		
		btnplay.setOnAction(e -> sizePickerM());
		
		btnsetting.setOnAction(e -> settingscreen());
		
		btnabout.setOnAction(e -> aboutscreen());
		
		btnexit.setOnAction(e -> {Platform.exit();
		});
	    
	    btnplay.setMaxWidth(Double.MAX_VALUE);
	    btnsetting.setMaxWidth(Double.MAX_VALUE);
	    btnabout.setMaxWidth(Double.MAX_VALUE);
	    btnexit.setMaxWidth(Double.MAX_VALUE);
	    
	    
	    grid.getChildren().addAll(titel, btnplay, btnsetting, btnabout, btnexit);	
	    
	    window.setScene(mainMenu);
	    mainMenu.getStylesheets().add(View.class.getResource("screen1.css").toExternalForm());
	}
	public void sizePickerM(){
		GridPane grid2 = new GridPane();
		grid2.setHgap(10);
		grid2.setVgap(20);
		grid2.setAlignment(Pos.TOP_CENTER);
		
		BorderPane borderP = new BorderPane();
		
		sizePicker = new Scene(borderP, 750, 750);
		
		Label chooseSize = new Label("Choose the size of the game");
		GridPane.setConstraints(chooseSize, 5, 2);
		
		TextField sizePrompt = new TextField();
		sizePrompt.setPromptText("E.g. 3");
		GridPane.setConstraints(sizePrompt, 5, 3);
		
		sizePrompt.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				initSize = Integer.parseInt(sizePrompt.getText());
				gameSceneM();
			}
		});
		
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		GridPane.setConstraints(sizeInfo, 5, 4);
		
		Button go = new Button("Start");
		GridPane.setConstraints(go, 6, 3);
		go.setOnAction(e -> {
			initSize = Integer.parseInt(sizePrompt.getText());
			gameSceneM();
		});
		
		Image backButton = new Image(getClass().getResourceAsStream("BackArrow.png"));
		Button goBack = new Button();
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setGraphic(new ImageView(backButton));
		goBack.setOnAction(e -> mainMenuM());
		
		grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, go, goBack);
		borderP.setTop(goBack);
		borderP.setCenter(grid2);
		window.setScene(sizePicker);
		sizePicker.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	
	public void gameSceneM(){

		Model temp = new Model();
		kontrol = new Controller(temp);
		kontrol.theModel.createLabels(initSize);
		labels = kontrol.theModel.getLabels();
		
		GridPane mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(5,5,5,5));
		mainGrid.setHgap(5);
		mainGrid.setVgap(5);
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.getStyleClass().addAll("pane","gridPane");
		
		    	
		gridPane= addLabels(gridPane, labels);
		
		ScrollPane scroll1 = new ScrollPane(gridPane);
		scroll1.setFitToWidth(true);
		scroll1.setFitToHeight(true);
		
		mainGrid.add(gridPane, 0, 0);
		mainGrid.add(scroll1, 0, 1);
		
		game = new Scene(mainGrid, 750, 750);

		window.setScene(game);
		game.getStylesheets().add(View.class.getResource("screen3.css").toExternalForm());
	}
	public void aboutscreen(){
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.CENTER);
	    
	    Label dev= new Label("The awesome developers: \n Kristian Wolthers Rasmussen \n Pelle Rubin Galløe \n Rasmus Suonperä Liebst "
	    		+ "\n Jia Johnny Ye");
	    grid.getChildren().add(dev);

		
	    about = new Scene(grid, 750, 750);
		window.setScene(about);
		about.getStylesheets().add(View.class.getResource("screen1.css").toExternalForm());
	}
	public void settingscreen(){
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.CENTER);
	    
		
	    setting = new Scene(grid, 750, 750);
		window.setScene(setting);
		setting.getStylesheets().add(View.class.getResource("screen1.css").toExternalForm());
	}

}


