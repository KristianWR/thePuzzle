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
	
	//Game scene variables and objects
	private Stage gameStage;
	private MainMenuScene mainMenuScene;
	private SizePickerScene sizeScene;
	private AlertBox alertBox = new AlertBox();
	
	//Media variables
    private Integer STARTTIME = 10;
    private Label timerLabel = new Label();
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(STARTTIME);
    private Timeline timeline;
    
    private Scene gameScene;
	private int initSize;
	private Model theModel;

	private Button back;
	private Button btn_mute;
	
    //Media objects for different sounds that occur at special events handled later in the code.
    private Media tileSwap = new Media(Controller.class.getClassLoader().getResource("puzzleV3/walk2.mp3").toString());
    private Media winMusic = new Media(Controller.class.getClassLoader().getResource("puzzleV3/WinV1.mp3").toString());
	private Media looseMusic = new Media(Controller.class.getClassLoader().getResource("puzzleV3/LostV1.mp3").toString());
	
	//MediaPlayers for the different sounds declared above.
	private MediaPlayer mpWin = new MediaPlayer(winMusic);
	private MediaPlayer mpLoose = new MediaPlayer(looseMusic);
	private MediaPlayer mpFX = new MediaPlayer(tileSwap);
	
	//Constructor
	public GameScene(Stage window, MainMenuScene mainMenuScene, SizePickerScene sizeScene, Model m){
		this.gameStage = window;
		this.mainMenuScene = mainMenuScene;
		this.sizeScene = sizeScene;
		this.theModel = m;
		back = new Button();
		btn_mute = new Button("Mute Music");
		timeline = new Timeline();
	}
	
	/*This method constructs the GUI of the game scene. It also applies multiple
	 * evenHandlers for different events - all described below or in Controller.
	 */
	public void gameSceneM(){
	    //Game board 2-d array for the labels.
		Label[][] labels;
		
	    //Declares different UI objects used in the gameScene.
		Label moves = theModel.getMoveCount();
		Label playLabel = theModel.getIsPlaying();
	    Label numberOfMoves = new Label("Number \nof moves:");
	    Label timeLeft = new Label("Time left:");
	    Button randomize = new Button("Randomize");
	    Button btn_muteFX = new Button("Mute Sound FX");
	    
		mpFX.setVolume(1.0);
		
		//Creates a new label[][] in theModel based on the size chosen in sizePickerScene.
		theModel.createLabels(initSize);
		labels = theModel.getLabels();
		
		//Sets the initial time for the time pressure (which is optional).
		timeSeconds.set(STARTTIME);	
		timeline.stop();
		
		//Bind the timerLabel text property to the timeSeconds property
        timerLabel.textProperty().bind(timeSeconds.asString());
        
        //Removes the default css method label
        //adds a custom css method to the label
	    timerLabel.getStyleClass().remove("label");
	    timerLabel.getStyleClass().add("size");
	    timeLeft.getStyleClass().remove("label");
	    timeLeft.getStyleClass().add("skrift");
	    numberOfMoves.getStyleClass().remove("label");
	    numberOfMoves.getStyleClass().add("skrift");
	    moves.getStyleClass().remove("label");
	    moves.getStyleClass().add("size");
		
	    //Toggle sound fx on/off
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
	    
	    //Randomizes all tiles
	    randomize.setOnAction(e -> theModel.randomMove());
	    
	    /* A ChangeListener registers changes to the label playLabel. 
	     *  If this happens the method "changed" is called. If the label 
	     * does not have the text "yes", it means the player has won. 
	     * If this is the case, the following happens: 
	     * The win mediaplayer will play. The timeline will stop, if it 
	     * is active. An alertbox is then called, that informs
	     * the player, she has won. The alertbox object has two
	     * buttons - one will take the player to the main scene, the other
	     * to the pick size scene. 
	     */
		playLabel.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	
            	if (playLabel.getText().equals("yes")){
            	}else{
                	mpWin.stop();
                	mpWin.play(); 
                	
        			if(timeline.getCurrentRate() != 0.0) {
        				timeline.stop();
        			}
                	
                	alertBox.display("You won", "Congratulations");
      	          
    				alertBox.getMainMenuButton().setOnAction(e -> {
    	                			
    							gameStage.setScene(mainMenuScene.getMainMenu());
    	                			alertBox.getWindow().close();
    	            });
    	            alertBox.getRestartButton().setOnAction(e -> {
    	                    		gameStage.setScene(sizeScene.getSizePicker());
    	                    		alertBox.getWindow().close();
    	            });
                	                        	

            	}
            }
        }); 
		
		/*The move label increments every time a tile is moved. When this happens, a
		 *the changed method is called. 
		 */ 
		moves.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            	//"Rewinds" the sound, then plays it   
            	mpFX.stop();
            	mpFX.play();
            	
            	//If checkbox is checked (true), the timer animation is activated 
            	if(sizeScene.getCb1().isSelected() == true) {
            		
            		//Sets up the timer animation with the defined start time
	                timeSeconds.set(STARTTIME);
	                timeline.getKeyFrames().add(				                		
	                        new KeyFrame(Duration.seconds(STARTTIME+1),
	                        new KeyValue(timeSeconds, 0)));
	                timeline.playFromStart();
	                				                				           
	                //Detects when the timeline is finished (defined in KeyValue as 0)
	                //and triggers an action event
	                timeline.setOnFinished((ActionEvent event1) -> {
	                		//"Rewinds" then plays sound
	                		mpLoose.stop();
	                		mpLoose.play();
	                		
	                		//An alert box is called via its display method.
	                    	alertBox.display("You lost", "You are a looser!");
	        				alertBox.getMainMenuButton().setOnAction(e -> {
	        	                			gameStage.setScene(mainMenuScene.getMainMenu());
	        	                			alertBox.getWindow().close();
	        	            });
	        	            alertBox.getRestartButton().setOnAction(e -> {
	        	                    		gameStage.setScene(sizeScene.getSizePicker());
	        	                    		alertBox.getWindow().close();
	        	            });
	                });
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
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(5,5,5,5));
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		
		//Adds the css method pane to the game's gridpane
		gridPane.getStyleClass().add("pane");
		gridPane= addLabels(gridPane, labels);
		gridPane.setMaxSize(600, 600);
		gridPane.setAlignment(Pos.CENTER);
		
		//// TOP BUTTONS ////
		back.setMaxWidth(30);
		back.setMinWidth(40);
		back.getStyleClass().add("button-back");
		back.setAlignment(Pos.TOP_LEFT);
		
		HBox topBtns = new HBox();
		topBtns.setPadding(new Insets(15,12,15,12));
		topBtns.setSpacing(250);
		topBtns.setAlignment(Pos.CENTER_LEFT);
		
		topBtns.getChildren().addAll(back, randomize);
		
		//// SCROLLPANE --- The layout that has the scroll feature for the game layout ///
		ScrollPane scroll1 = new ScrollPane(gridPane);
		scroll1.setFitToWidth(true);
		
		////// The HBOX containing bottom buttons //////
		HBox bottomBtns = new HBox();
		bottomBtns.setPadding(new Insets(15, 12, 15, 12));
	    bottomBtns.setSpacing(35);
	    bottomBtns.setAlignment(Pos.CENTER);
	    bottomBtns.getChildren().addAll(btn_mute,btn_muteFX);
	    
	    /////// VBox containing content right of game ///////
	    VBox rightContent = new VBox();
	    rightContent.setPadding(new Insets(15, 12, 15, 12));
	    rightContent.setSpacing(60);
	    
	    //If the checkbox for time pressure is enabled in the size picker scene,
	    // timer labels are added to the layout.
	    if(sizeScene.getCb1().isSelected() == true) {
	    
	    rightContent.getChildren().addAll(timeLeft,timerLabel,numberOfMoves,moves);
	    } else {
	    	rightContent.getChildren().addAll(numberOfMoves,moves);
	    }
	    
		////// All content for the main layout is added here ///// 
	    BorderPane main = new BorderPane();
	    StackPane stack = new StackPane();
	    
	    mainGrid.setAlignment(Pos.CENTER);
		mainGrid.add(gridPane, 1, 1);
		mainGrid.add(scroll1, 0, 1);
		
		main.setCenter(mainGrid);
		main.setTop(topBtns);
		main.setRight(rightContent);
		main.setBottom(bottomBtns);
		main.setLeft(stack);
		
		stack.setPrefWidth(40);
		
		gameScene = new Scene(main, 700, 700);
		gameStage.setScene(gameScene);
		
		//Tells Eclipse to look in the same package as controller and use screen3
		gameScene.getStylesheets().add(Controller.class.getResource("screen3.css").toExternalForm());
	}
	
	//Adds the labels to the gridpane
	public GridPane addLabels(GridPane gridPane, Label[][] tempLabels){
		for(int i = 0; i < tempLabels.length; i++){
			for(int j = 0; j < tempLabels.length; j++){
					GridPane.setConstraints(tempLabels[i][j], j, i);
					gridPane.getChildren().add(tempLabels[i][j]);
			}
		}
		return gridPane;
	}
	
	// Getter and setter methods
	public Timeline getTimeline() {
		return timeline;
	}
	
	public Scene getGameScene() {
		return gameScene;
	}
	
	public int getInitSize() {
		return initSize;
	}
	
	public void setInitSize(int initSize) {
		this.initSize = initSize;
	}

	public Model getTheModel() {
		return theModel;
	}
	
	public Button getBack() {
		return back;
	}
	
	public Button getBtn_mute() {
		return btn_mute;
	}
}
