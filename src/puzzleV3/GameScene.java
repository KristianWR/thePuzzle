package puzzleV3;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameScene {
	
	Stage gameStage;
	Scene gameScene;
	Scene mainMenuScene;
	SizePickerScene sizeScene;
	
	AlertBox alertBox = new AlertBox();
	
    private static final Integer STARTTIME = 15;
    Timeline timeline;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    
    Label[][] labels;
	Controller kontrol;
	ScrollPane scroll1;
	MediaPlayer mpFX;
	int initSize;
	
	MediaPlayer mpMusic;
	MediaPlayer mpWin;
	MediaPlayer mpLoose;
	
	CheckBox cb1;
	GridPane gridPane;
	
	Button back;
	
	public GameScene(Stage window, Scene mainMenuScene, SizePickerScene sizeScene, CheckBox cb1, Controller kontrol){
		this.gameStage = window;
		this.mainMenuScene = mainMenuScene;
		this.sizeScene = sizeScene;
		this.cb1 = cb1;		
		this.kontrol = kontrol;
		back = new Button();
		timeline = new Timeline();
	}
	
	public void gameSceneM(){

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
	                			
							gameStage.setScene(mainMenuScene);
	                			alertBox.window.close();
	            });
	            alertBox.getRestartButton().setOnAction(e -> {
	                    		gameStage.setScene(sizeScene.getSizeScene());
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
            	if(sizeScene.cb1.isSelected() == true) {
            		
            		
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
	        	                			gameStage.setScene(mainMenuScene);
	        	                			alertBox.window.close();
	        	            });
	        	            alertBox.getRestartButton().setOnAction(e -> {
	        	                    		gameStage.setScene(sizeScene.getSizeScene());
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
		
		
		back.setMaxWidth(30);
		back.getStyleClass().add("button-back");
		
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
	    
	    
	    if(sizeScene.cb1.isSelected() == true) {
	    
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
		
		gameScene = new Scene(main, 700, 700);
		gameStage.setScene(gameScene);
		gameScene.getStylesheets().add(View.class.getResource("screen3.css").toExternalForm());
	}
	
	public Button getGoBackButton(){
		return back;
	}
	
	public Timeline getTimeline(){
		return timeline;
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
}
