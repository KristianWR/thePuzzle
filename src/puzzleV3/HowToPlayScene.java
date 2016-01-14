package puzzleV3;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HowToPlayScene {
	
	Stage howStage;
	Scene howScene;
	Button back;
	
	public HowToPlayScene(Stage window){
		this.howStage= window;
		back = new Button();
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
	    
	    Label win= new Label("Win condition:");
	    win.getStyleClass().add("label-fed");
	    Label tekst=new Label(" You win the game by \n having alle the numbers"
	    		+ " in order \n with the empty field as the last field");
	    tekst.getStyleClass().add("label-about");
	    
	    grid.add(tut, 0, 0);
	    grid.add(htp, 0, 1);
	    grid.add(win, 0, 2);
	    grid.add(tekst, 0, 3);
	    
	    
		back.setMaxWidth(40);
		back.getStyleClass().add("button-back");
		back.setAlignment(Pos.TOP_LEFT);
	
		border.setTop(back);
		border.setCenter(grid);
	    howScene = new Scene(border, 750, 750);
		howStage.setScene(howScene);
		howScene.getStylesheets().add(View.class.getResource("screen2.css").toExternalForm());
	}
}
