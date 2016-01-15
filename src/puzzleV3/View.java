package puzzleV3;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class View extends Application{
	Stage window = new Stage();
	
	MainMenuScene mainMenuScene = new MainMenuScene(window);
	HowToPlayScene howScene = new HowToPlayScene(window);
	SizePickerScene sizeScene = new SizePickerScene(window); 
	Model m = new Model();
	Controller kontrol = new Controller(m);
	GameScene gameScene = new GameScene(window, mainMenuScene, sizeScene, kontrol);
	//mediaplayer variables
	MediaPlayer mpMusic;
	MediaPlayer mpWin;
	MediaPlayer mpLoose;
	//timer variables

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainMenuScene.mainMenuM();
		
		mainMenuScene.btnHow.setOnAction(e -> howScene.howSceneM());
				
		mainMenuScene.btnPlay.setOnAction(e -> sizeScene.sizePickerM());
		
		howScene.back.setOnAction(e -> mainMenuScene.mainMenuM());
		
		sizeScene.goBack.setOnAction(e -> mainMenuScene.mainMenuM());
		
		sizeScene.go.setOnAction(e -> {
			try{
				int size = sizeScene.getInitSize();
				size = Integer.parseInt(sizeScene.getSizePrompt().getText());
				
				if(size < 3 || size > 100){
					System.out.println("Not between 3-100");
					///sizeInfo label
				}else {
					gameScene.initSize = size;
					gameScene.gameSceneM();	
				}
				
			}catch(NumberFormatException ex){
				System.out.println("Not a number");
			}
			
			
		});
		
		
		//Gives gameScene.back action to chance scene
		gameScene.back.setOnAction(e -> {
			sizeScene.sizePickerM();
			
			if(gameScene.getTimeline().getCurrentRate() != 0.0){
				gameScene.getTimeline().stop();
			}
			
		});
		//Gives gameScene mutebutton action to mute
		gameScene.getMuteButton().setOnAction(e -> {										
			if (mpMusic.getVolume() != 0.0){					
				mpMusic.setVolume(0.0);
				gameScene.setMuteButtonText("Play Music");
			} else {
				mpMusic.setVolume(0.9);
				gameScene.setMuteButtonText("Mute Music");
			}
		});
		
		gameScene.getBorderPane().setOnKeyPressed(e ->{
			if		(e.getCode() == KeyCode.UP){kontrol.theModel.keyMove("down");}
			else if (e.getCode() == KeyCode.DOWN){kontrol.theModel.keyMove("up");}
			else if (e.getCode() == KeyCode.LEFT){kontrol.theModel.keyMove("right");}
			else if (e.getCode() == KeyCode.RIGHT){kontrol.theModel.keyMove("left");}
		});
		
		window.show();
		
		
		//initializing media variables and starting background music 
		Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/15zen.mp3").toString());
		Media winMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/WinV1.mp3").toString());
		Media looseMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/LostV1.mp3").toString());
		mpMusic = new MediaPlayer(backgroundMusic);		
		mpWin = new MediaPlayer(winMusic);
		mpLoose = new MediaPlayer(looseMusic);
		
		mpMusic.setAutoPlay(true);	
		mpMusic.setVolume(0.9);
		
		mpMusic.setCycleCount(1000);
		
	}
	

	/*
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
		
	    Button btnsetting = new Button("How to play");
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
					sizeInfo.setText("Must be an integer");
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
				sizeInfo.setText("Please enter a size");
			}
		});
		
		Label timeInfo = new Label("Enable time pressure");
		GridPane.setConstraints(timeInfo, 1, 5);
		timeInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
			
		cb1 = new CheckBox();
		cb1.setText("Time pressure");
		cb1.setTextFill(Color.WHITE);
		cb1.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)){
				try{
					initSize = Integer.parseInt(sizePrompt.getText());
					if ( initSize < 3 || initSize > 100){
						System.out.println("not between 3-100");
						sizeInfo.setText("Please enter a size first!");
					}else{
						gameSceneM();					
					}
				}catch (NumberFormatException ex){
					System.out.println("not an int");
					sizeInfo.setText("Please enter a size first!");
				}
			}
		});
		
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
	    Button btn_muteFX = new Button("Mute Sound FX");
	    Button randomize = new Button("Randomize");
	    timeline = new Timeline();
	    timeSeconds.set(STARTTIME);	 	    	    
	    Media tileSwap = new Media(View.class.getClassLoader().getResource("puzzleV3/walk2.mp3").toString());		    
    	mpFX = new MediaPlayer(tileSwap);
		mpFX.setVolume(1.0);
		
		//bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        
        // ???
	    timerLabel.getStyleClass().remove("label");
        timerLabel.getStyleClass().add("size");
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
					btn_mute.setText("Play Music");
				} else {
					mpMusic.setVolume(0.9);
					btn_mute.setText("Mute Music");
				}
				
				
			
			}
		});
	    
	    //toggle sound fx on/off
	    btn_muteFX.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			
			public void handle(ActionEvent arg0) {
															
				if (mpFX.getVolume() != 0.0){					
					mpFX.setVolume(0.0);
					btn_muteFX.setText("Play Sound FX");
				} else {
					mpFX.setVolume(1.0);
					btn_muteFX.setText("Mute Sound FX");
				}								
				
			}
		});
	    
	    //randomizes all tiles
	    randomize.setOnAction(e -> kontrol.theModel.randomMove());
	    	    
		Label playLabel = kontrol.theModel.isPlaying;
		playLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	
            	mpMusic.pause();
            	mpWin.stop();
            	mpWin.play(); 
            	
    			if(timeline.getCurrentRate() != 0.0) {
    				timeline.stop();
    				}
            	
            	alertBox.display("You won", "Congratulations");
  	          
				alertBox.getMainMenuButton().setOnAction(e -> {
	                			window.setScene(mainMenu);
	                			alertBox.window.close();
	            });
	            alertBox.getRestartButton().setOnAction(e -> {
	                    		window.setScene(sizePicker);
	                    		alertBox.window.close();
	            });
            	                        	
            }
        }); 
		
		Label moves = kontrol.theModel.moveCount;
	    moves.getStyleClass().remove("label");
        moves.getStyleClass().add("size");
		moves.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	
            	//"rewinds" the sound, then plays it   
            	mpFX.stop();
            	mpFX.play();
            	
            	//if checkbox is checked, and therefore true, the timer animation is activated 
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
	                		mpMusic.pause();
	                		mpLoose.stop();
	                		mpLoose.play();
	                		
	                    	alertBox.display("You lost", "You are a looser!");
	            	          
	        				alertBox.getMainMenuButton().setOnAction(e -> {
	        	                			window.setScene(mainMenu);
	        	                			alertBox.window.close();
	        	            });
	        	            alertBox.getRestartButton().setOnAction(e -> {
	        	                    		window.setScene(sizePicker);
	        	                    		alertBox.window.close();
	        	            });
	                }
	                );
            	}
			}
        }
		); 
		
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
	    rightContent.setSpacing(60);
	    
	    if(cb1.isSelected() == true) {
	    
	    rightContent.getChildren().addAll(timeLeft,timerLabel,numberOfMoves,moves);
	    } else {
	    	rightContent.getChildren().addAll(numberOfMoves,moves);
	    }
		////// All content for the main layout is added here ///// 
	    
	    mainGrid.setAlignment(Pos.CENTER);
		mainGrid.add(gridPane, 1, 1);
		mainGrid.add(scroll1, 0, 1);
		
		BorderPane main = new BorderPane();
		main.setCenter(mainGrid);
		main.setTop(topBtns);
		main.setRight(rightContent);
		main.setBottom(bottomBtns);
		StackPane stack = new StackPane();
		stack.setPrefWidth(40);
		main.setLeft(stack);
		
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
	*/
}



