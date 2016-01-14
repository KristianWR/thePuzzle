package puzzleV3;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	MediaPlayer mpLoose;
	//timer variables
    private static final Integer STARTTIME = 15;
    private Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    ScrollPane scroll1;
    CheckBox cb1;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("15-Puzzle");
		mainMenuM();
		window.show();
		window.setMaxHeight(window.getHeight());
		window.setMaxWidth(window.getWidth());
		
		Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/15zen.mp3").toString());
		Media winMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/win.mp3").toString());
		Media looseMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/loose.mp3").toString());
		mpMusic = new MediaPlayer(backgroundMusic);		
		mpWin = new MediaPlayer(winMusic);
		mpLoose = new MediaPlayer(looseMusic);
		
		mpMusic.setAutoPlay(true);	
		mpMusic.setVolume(0.9);
		
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

		mainMenu = new Scene(grid, 700, 700);
	    
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
		
		btnsetting.setOnAction(e -> howscreen());
		
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
		
		sizePicker = new Scene(borderP, 700, 700);
		
		Label chooseSize = new Label("Choose the size of the game");
		chooseSize.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		GridPane.setConstraints(chooseSize, 1, 2);
		
		TextField sizePrompt = new TextField();
		sizePrompt.setPromptText("E.g. 3");
		GridPane.setConstraints(sizePrompt, 1, 3);
		
		Label sizeInfo = new Label("(N.B. write a number between 3 and 100)");
		GridPane.setConstraints(sizeInfo, 1, 4);
		
		sizePrompt.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				try{
					initSize = Integer.parseInt(sizePrompt.getText());
					if ( initSize < 3 || initSize > 100){
						System.out.println("not between 3-100");
						sizeInfo.setText("must be between 3-100");
					}else{
						gameSceneM();					
					}
				}catch (NumberFormatException ex){
					System.out.println("not a int");
					sizeInfo.setText("must! be an integer");
				}
			}
		});
		
		
		Button go = new Button("Start");
		GridPane.setConstraints(go, 2, 6);
		go.setOnAction(e -> {
			try{
				initSize = Integer.parseInt(sizePrompt.getText());
				if ( initSize < 3 || initSize > 100){
					System.out.println("not between 3-100");
					sizeInfo.setText("must be between 3-100");
				}else{
					gameSceneM();					
				}
			}catch (NumberFormatException ex){
				System.out.println("not a int");
				sizeInfo.setText("must! be an integer");
			}
		});
		
		Label timeInfo = new Label("Enable time pressure");
		GridPane.setConstraints(timeInfo, 1, 5);
		timeInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
		
		cb1 = new CheckBox();
		cb1.setText("Time pressure");
		cb1.setTextFill(Color.WHITE);
		
		GridPane.setConstraints(cb1, 1, 6);
		
		Button goBack = new Button();
		GridPane.setConstraints(goBack, 0, 0);
		goBack.setMaxWidth(40);
		goBack.getStyleClass().add("button-back");
		goBack.setOnAction(e -> mainMenuM());
		
		grid2.getChildren().addAll(chooseSize, sizePrompt, sizeInfo, cb1, timeInfo, go);
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
	    Button randomize = new Button("Randomize");
	    timeline = new Timeline();
	    timeSeconds.set(STARTTIME);	 

	    	    
	    Media tileSwap = new Media(View.class.getClassLoader().getResource("puzzleV3/walk2.mp3").toString());	
	    
    	mpFX = new MediaPlayer(tileSwap);
		mpFX.setVolume(1.0);
		
		// Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        //timerLabel.setStyle("-fx-font-size: 4em;");
	    timerLabel.getStyleClass().remove("label");
        timerLabel.getStyleClass().add("skrift");
        Label timeLeft = new Label("Time left:");
	    timeLeft.getStyleClass().remove("label");
        timeLeft.getStyleClass().add("skrift");
        
        Label numberOfMoves = new Label("Number \nof moves:");
	    numberOfMoves.getStyleClass().remove("label");
        numberOfMoves.getStyleClass().add("skrift");
		
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
	    
	    //randomizes the game
	    randomize.setOnAction(e -> kontrol.theModel.randomMove());
	    	    
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
	    moves.getStyleClass().remove("label");
        moves.getStyleClass().add("skrift");
		moves.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	  
            	mpFX.stop();
            	mpFX.play();
            	
            	if(cb1.isSelected() == true) {
            	 if (timeline != null) {
	                    timeline.stop();    
	                }
	                				                
	                timeSeconds.set(STARTTIME);
	                timeline.getKeyFrames().add(				                		
	                        new KeyFrame(Duration.seconds(STARTTIME+1),
	                        new KeyValue(timeSeconds, 0)));
	                
	                timeline.playFromStart();
	                //When times runs out
	                				                				           
	                timeline.setOnFinished((ActionEvent event1) -> {
	                		mpLoose.stop();
	                		mpLoose.play();
	                		popUpLoose();
	                });
            	}
				}
        }); 
		
		///// THE MAIN LAYOUT OF THE SCENE /////
		
			
		GridPane mainGrid = new GridPane();
		mainGrid.setPadding(new Insets(5,5,5,5));
		mainGrid.setHgap(5);
		mainGrid.setVgap(5);		
		
		//// GRIDPANE ---- THE GAME --- //////
		
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		gridPane.getStyleClass().addAll("pane","gridPane");
	
		gridPane= addLabels(gridPane, labels);
		
		gridPane.setMaxSize(600, 600);
		gridPane.setAlignment(Pos.CENTER);
		
		//// BACKBUTTON ---- The button for going back ////
		
		Button back = new Button();
		back.setMaxWidth(30);
		back.getStyleClass().add("button-back");
		back.setOnAction(e -> {
			
			sizePickerM();
			
			if(timeline.getCurrentRate() != 0.0) {
			timeline.stop();
			}
			
			
		});
		back.setAlignment(Pos.TOP_LEFT);
		
		HBox topBtns = new HBox();
		topBtns.setPadding(new Insets(15,12,15,12));
		topBtns.setSpacing(250);
		topBtns.setAlignment(Pos.CENTER_LEFT);
		
		topBtns.getChildren().addAll(back, randomize);
		
		//// SCROLLPANE --- The layout that has the scroll feature for the game layout ///
		
		scroll1 = new ScrollPane(gridPane);
		scroll1.setFitToWidth(true);
		
		////// The HBOX containing top button //////
		
		
		HBox bottomBtns = new HBox();
		bottomBtns.setPadding(new Insets(15, 12, 15, 12));
	    bottomBtns.setSpacing(35);
	    bottomBtns.setAlignment(Pos.CENTER);
	    
	    bottomBtns.getChildren().addAll(btn_mute,btn_muteFX);
	    
	    /////// VBox containing content right of game ///////
	    
	    VBox rightContent = new VBox();
	    rightContent.setPadding(new Insets(15, 12, 15, 12));
	    rightContent.setSpacing(10);
	    
	    if(cb1.isSelected() == true) {
	    rightContent.getChildren().addAll(timeLeft,timerLabel,numberOfMoves,moves);
	    } else {
	    	rightContent.getChildren().addAll(numberOfMoves,moves);
	    }
		////// All content for the main layout is added here ///// 
	    
		//mainGrid.add(back, 0, 0);
	    mainGrid.setAlignment(Pos.CENTER);
		mainGrid.add(gridPane, 1, 1);
		mainGrid.add(scroll1, 0, 1);
		//mainGrid.add(bottomBtns, 1, 0);
		//mainGrid.add(rightContent, 2, 1);
		
		BorderPane main = new BorderPane();
		main.setCenter(mainGrid);
		main.setTop(topBtns);
		main.setRight(rightContent);
		main.setBottom(bottomBtns);
		StackPane stack = new StackPane();
		stack.setPrefWidth(40);
		main.setLeft(stack);
		
		/*		
		mainGrid.add(playLabel, 0, 2);
		
		mainGrid.add(moves, 0, 1);
		mainGrid.add(btn_mute, 2, 0);
		mainGrid.add(btn_muteFX, 2, 1);
		mainGrid.add(timerLabel, 3, 0);
		mainGrid.add(timeLeft, 3, 1);*/
		
		game = new Scene(main, 700, 700);
		window.setScene(game);
		game.getStylesheets().add(View.class.getResource("screen3.css").toExternalForm());
	}
	public void aboutscreen(){
		BorderPane border=new BorderPane();
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    Button back = new Button();
			back.setMaxWidth(40);
			back.getStyleClass().add("button-back");
			back.setOnAction(e -> mainMenuM());
			back.setAlignment(Pos.TOP_LEFT);
	    
	    Label dev= new Label("The awesome developers:");
	    dev.getStyleClass().add("label-fed");
	    Label tekst= new Label("Kristian Wolthers Rasmussen \nPelle Rubin Galløe \nRasmus Suonperä Liebst "
	    		+ "\nJia Johnny Ye");
	    tekst.getStyleClass().add("label-about");
	    
	    grid.add(dev, 0,0);
	    grid.add(tekst, 0,1);
	    
	    border.setTop(back);
	    border.setCenter(grid);
		
	    about = new Scene(border, 750, 750);
		window.setScene(about);
		about.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	public void howscreen(){
		BorderPane border=new BorderPane();
		GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(20);
	    grid.setAlignment(Pos.TOP_CENTER);
	    
	    Label tut= new Label("Tutorial:");
	    tut.getStyleClass().add("label-fed");
	    Label htp= new Label(" You play the game by \n pressing"
	    		+ " one of the fields \n next to the empty field \n");
	    htp.getStyleClass().add("label-about");
	    
	    Label win= new Label("Wincondition:");
	    win.getStyleClass().add("label-fed");
	    Label tekst=new Label(" You win the game by \n having alle the numbers"
	    		+ " in order \n with the empty field as the last field");
	    tekst.getStyleClass().add("label-about");
	    
	    grid.add(tut, 0, 0);
	    grid.add(htp, 0, 1);
	    grid.add(win, 0, 2);
	    grid.add(tekst, 0, 3);
	    
	    Button back = new Button();
		back.setMaxWidth(40);
		back.getStyleClass().add("button-back");
		back.setOnAction(e -> mainMenuM());
		back.setAlignment(Pos.TOP_LEFT);
	
		border.setTop(back);
		border.setCenter(grid);
	    setting = new Scene(border, 750, 750);
		window.setScene(setting);
		setting.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
	
	public void popUpWin(){
		Stage window = new Stage();
		Scene scene;
		
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Congratulations");
        window.setMinWidth(250);
        
        timeline.stop();
        
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



