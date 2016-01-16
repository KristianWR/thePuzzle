package puzzleV3;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class View extends Application{
	// The main stage of the application
	private Stage window = new Stage();
	
	// Instances of objects (including scenes) 
	private MainMenuScene mainMenuScene = new MainMenuScene(window);
	private HowToPlayScene howScene = new HowToPlayScene(window);
	private SizePickerScene sizeScene = new SizePickerScene(window);
	private AboutScene aboutScene = new AboutScene(window);
	private Model theModel = new Model();
	private GameScene gameScene = new GameScene(window, mainMenuScene, sizeScene, theModel);
	
	//Background music variable and object
	private MediaPlayer mpMusic;
	private Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/15zen.mp3").toString());
	
	//Main method (launches program)
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/*
		 * MainScene 
		 */
		mainMenuScene.mainMenuM();
		mainMenuScene.btnHow.setOnAction(e -> howScene.howSceneM());
		mainMenuScene.btnPlay.setOnAction(e -> sizeScene.sizePickerM());
		mainMenuScene.btnAbout.setOnAction(e -> aboutScene.aboutSceneM());
		/*
		 * HowScene
		 */
		howScene.back.setOnAction(e -> mainMenuScene.mainMenuM());
		/*
		 * AboutScene
		 */
		aboutScene.back.setOnAction(e -> mainMenuScene.mainMenuM());
		/*
		 * SizeScene
		 */
		sizeScene.goBack.setOnAction(e -> mainMenuScene.mainMenuM());
		sizeScene.go.setOnAction(e -> {
			try{
				int size = sizeScene.initSize;
				size = Integer.parseInt(sizeScene.sizePrompt.getText());
				
				if(size < 3 || size > 100){
					System.out.println("Not between 3-100");
					sizeScene.sizeInfo.setText("Must be between 3 and 100!");
				}else {
					gameScene.initSize = size;
					gameScene.gameSceneM();
					addArrowKeyListener();
				}
				
			}catch(NumberFormatException ex){
				System.out.println("Not a number");
				sizeScene.sizeInfo.setText("That is not a number.");
			}
		});
		
		/*
		 * GameScene 
		 */
		//Back button chances scene to SizePickerScene
		gameScene.back.setOnAction(e -> {
			sizeScene.sizePickerM();
			if(gameScene.timeline.getCurrentRate() != 0.0){
				gameScene.timeline.stop();
			}
		});
		
		//Mute button toggles music on/off 
		gameScene.btn_mute.setOnAction(e -> {										
			if (mpMusic.getVolume() != 0.0){					
				mpMusic.setVolume(0.0);
				gameScene.btn_mute.setText("Play Music");
			} else {
				mpMusic.setVolume(0.9);
				gameScene.btn_mute.setText("Mute Music");
			}
		});
		
		
		//Makes the main Stage "window" visible
		window.show();
		
		//Initializing media variables and starting background music in loop 1000 times
		mpMusic = new MediaPlayer(backgroundMusic);		 
		mpMusic.setAutoPlay(true);	
		mpMusic.setVolume(0.9);
		mpMusic.setCycleCount(1000);
		
	}
	/*
	 * 
	 */
	public void addArrowKeyListener(){
		gameScene.gameScene.setOnKeyPressed(e ->{
			if		(e.getCode() == KeyCode.W){theModel.keyMove("down");}
			else if (e.getCode() == KeyCode.S){theModel.keyMove("up");}
			else if (e.getCode() == KeyCode.A){theModel.keyMove("right");}
			else if (e.getCode() == KeyCode.D){theModel.keyMove("left");}
		}); 
	}
}