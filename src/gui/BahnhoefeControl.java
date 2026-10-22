package gui;

import java.io.IOException;

import business.BahnhoefeModel;
import javafx.stage.Stage;

public class BahnhoefeControl {
	
	private BahnhoefeModel bahnModel;
	private BahnhoefeView bahnView;
	
	
	public BahnhoefeControl(Stage primaryStage) {
		
		this.bahnModel = new BahnhoefeModel();
		this.bahnView = new BahnhoefeView(this,primaryStage,bahnModel);
		
	}
	
	
	public void leseAusDatei(String typ){
		try {
      		if("csv".equals(typ)){
      			bahnModel.leseAusDatei();
      			bahnView.zeigeInformationsfensterAn(
      	  	   			"Der Bahnhof wurde gelesen!");
      		}else {
      			
      			bahnView.zeigeInformationsfensterAn(
    	   				"Noch nicht implementiert!");
    	   		}
    		}
    		catch(IOException exc){
    			bahnView.zeigeFehlermeldungsfensterAn(
    				"IOException beim Lesen!");
    		}
    		catch(Exception exc){
    			bahnView.zeigeFehlermeldungsfensterAn(
    				"Unbekannter Fehler beim Lesen!");
    		}
	}
	
	public void schreibeBahnhoefeInCsvDatei() {
		try {
			bahnModel.schreibeBahnhoefeInCsvDatei();
			bahnView.zeigeInformationsfensterAn("Die Bahnhoefe wurden gespeichert!");
		
	   			
		}	
		catch(IOException exc){
			bahnView.zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			bahnView.zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
		
	}
	
	
	

}
