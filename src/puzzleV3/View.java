package puzzleV3;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class View extends Application{
	// The main stage of the application
	Stage window = new Stage();
	
	// The instances of the different "objects"
	MainMenuScene mainMenuScene = new MainMenuScene(window);
	HowToPlayScene howScene = new HowToPlayScene(window);
	SizePickerScene sizeScene = new SizePickerScene(window);
	AboutScene aboutScene = new AboutScene(window);
	Model m = new Model();
	Controller kontrol = new Controller(m);
	GameScene gameScene = new GameScene(window, mainMenuScene, sizeScene, kontrol);
	
	//Media player variables
	MediaPlayer mpMusic;
	Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/15zen.mp3").toString());

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
		
		
		/*
		 * GameScene 
		 */
		
		//Gives gameScene.back action to chance scene
		gameScene.back.setOnAction(e -> {
			sizeScene.sizePickerM();
			
			if(gameScene.getTimeline().getCurrentRate() != 0.0){
				gameScene.getTimeline().stop();
			}
			
		});
		//Gives gameScene mute button action to mute
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
		
		//Makes the main Stage "window" visible
		window.show();
		
		
		//initializing media variables and starting background music 
		mpMusic = new MediaPlayer(backgroundMusic);		 
		mpMusic.setAutoPlay(true);	
		mpMusic.setVolume(0.9);
		mpMusic.setCycleCount(1000);
		
	}
	
}