package puzzleV3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.media.Media;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
	MediaPlayer mpMusic;
	MediaPlayer mpFX;
	MediaPlayer mpWin;
	//timer variables
    private static final Integer STARTTIME = 3;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("15-Puzzle");
		mainMenuM();
		window.show();
		
		Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/zelda.mp3").toString());
		Media winMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/win.mp3").toString());
		//Media backgroundMusic = new Media(getClass().getResourceAsStream("zelda.mp3"));
		mpMusic = new MediaPlayer(backgroundMusic);		
		mpWin = new MediaPlayer(winMusic);
		
		mpMusic.setAutoPlay(true);	
		mpMusic.setVolume(0.3);
		
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
		
		Button goBack = new Button();
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setMaxWidth(40);
		goBack.getStyleClass().add("button-back");
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
		Button btn_mute = new Button("Mute Music");
	    Button btn_muteFX = new Button("Mute SoundFX");
	    
	    Media tileSwap = new Media(View.class.getClassLoader().getResource("puzzleV3/walk2.mp3").toString());	
	    
    	mpFX = new MediaPlayer(tileSwap);
		mpFX.setVolume(1.0);
		
		// Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        timerLabel.setTextFill(Color.BLACK);
        timerLabel.setStyle("-fx-font-size: 4em;");
        Label timeLeft = new Label();
        timeLeft.setText("Time left:");
		
	    //toggle music on/off
	    btn_mute.setOnAction(new EventHandler<ActionEvent>() {										
			
			public void handle(ActionEvent arg0) {
				
				//mpMusic.setMute(true);
				
				if (mpMusic.getVolume() != 0.0){					
					mpMusic.setVolume(0.0);
				} else {
					mpMusic.setVolume(0.3);
				}
				
			
			}
		});
	    
	    //toggle sound fx on/off
	    btn_muteFX.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			
			public void handle(ActionEvent arg0) {
															
				if (mpFX.getVolume() != 0.0){					
					mpFX.setVolume(0.0);
				} else {
					mpFX.setVolume(1.0);
				}								
				
			}
		});
	    
	    	    
		Label playLabel = kontrol.theModel.isPlaying;
		playLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	
            	mpWin.stop();
            	mpWin.play();
            	popUpWin();
            	                        	
            }
        }); 
		Label moves = kontrol.theModel.moveCount;
		moves.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	  
            	mpFX.stop();
            	mpFX.play();
            	
            	 if (timeline != null) {
	                    timeline.stop();    
	                }
	                				                
	                //System.out.println(timeSeconds.getValue()); 				                				               
	                timeSeconds.set(STARTTIME);
	                timeline = new Timeline();
	                timeline.getKeyFrames().add(				                		
	                        new KeyFrame(Duration.seconds(STARTTIME+1),
	                        new KeyValue(timeSeconds, 0)));
	                
	                timeline.playFromStart();
	                //When times runs out
	                				                				           
	                timeline.setOnFinished((ActionEvent event1) -> {
	                        // put event handler code here
	                		popUpLoose();
	                		//PopUpWin.display("Game over", "Time's up!");
	                		//System.out.println("Timer Works");
	                		
	                });
            	//mpFX.setAutoPlay(true);             	            	
            	//if (mpFX.getVolume() != 0.0){}         }					            					
            	//System.out.println("NOISE!!!!!!!!!!!!!");
            	
				}
        }); 
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
		mainGrid.add(playLabel, 0, 2);
		mainGrid.add(moves, 1, 1);
		mainGrid.add(btn_mute, 2, 0);
		mainGrid.add(btn_muteFX, 2, 1);
		mainGrid.add(timerLabel, 3, 0);
		mainGrid.add(timeLeft, 3, 1);
		
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
	
	public void popUpWin(){
		Stage window = new Stage();
		Scene scene;
		
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Congratulations");
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText("Congratulations! You won!");
        
        Button restartButton = new Button("Play again");
        restartButton.setOnAction(e -> {
        	sizePickerM();
        	window.close();
        });
        
        Button mainMenuButton = new Button("Main menu");
        mainMenuButton.setOnAction(e -> {
        	mainMenuM();
        	window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, restartButton, mainMenuButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

	}
	
	public void popUpLoose(){
		Stage window = new Stage();
		Scene scene;
		
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game over");
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText("Time is up! You have lost!");
        
        Button restartButton = new Button("Try again");
        restartButton.setOnAction(e -> {
        	sizePickerM();
        	window.close();
        });
        
        Button mainMenuButton = new Button("Main menu");
        mainMenuButton.setOnAction(e -> {
        	mainMenuM();
        	window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, restartButton, mainMenuButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        scene = new Scene(layout);
        window.setScene(scene);
        window.show();

	}
	

}



