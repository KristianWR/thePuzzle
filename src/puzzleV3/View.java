package puzzleV3;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class View extends Application{
	// The main stage of the application
	Stage window = new Stage();
	
	// Instances of objects (including scenes) 
	MainMenuScene mainMenuScene = new MainMenuScene(window);
	HowToPlayScene howScene = new HowToPlayScene(window);
	SizePickerScene sizeScene = new SizePickerScene(window);
	AboutScene aboutScene = new AboutScene(window);
	Model m = new Model();
	Controller kontrol = new Controller(m);
	GameScene gameScene = new GameScene(window, mainMenuScene, sizeScene, kontrol);
	
	//Background music variable and object
	MediaPlayer mpMusic;
	Media backgroundMusic = new Media(View.class.getClassLoader().getResource("puzzleV3/15zen.mp3").toString());
	
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
		//Back button chances scene to SizePickerScene
		gameScene.back.setOnAction(e -> {
			sizeScene.sizePickerM();
			
			if(gameScene.getTimeline().getCurrentRate() != 0.0){
				gameScene.getTimeline().stop();
			}
		});
		
		//Mute button toggles music on/off 
		gameScene.getMuteButton().setOnAction(e -> {										
			if (mpMusic.getVolume() != 0.0){					
				mpMusic.setVolume(0.0);
				gameScene.setMuteButtonText("Play Music");
			} else {
				mpMusic.setVolume(0.9);
				gameScene.setMuteButtonText("Mute Music");
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
	
}