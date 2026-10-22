package main;

import gui.BahnhoefeAnwendungssystem;
import gui.BahnhoefeControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		new BahnhoefeControl(primaryStage);
	}	
	
	public static void main(String[] args){
		launch(args);
	}
}
