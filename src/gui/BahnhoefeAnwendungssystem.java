package gui;
   
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import business.Bahnhof;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.*;

public class BahnhoefeAnwendungssystem {
	  
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     					= new  Pane();
    private Label lblEingabe    	 		= new Label("Eingabe");
    private Label lblAnzeige   	 	    	= new Label("Anzeige");
    private Label lblName 					= new Label("Name:");
    private Label lblOrt   		 			= new Label("Ort:");
    private Label lblAnzahlGleise  	 		= new Label("AnzahlGleise:");
    private Label lblLetzteRenovierung   	= new Label("Letzte Renovierung:");
    private Label lblZugarten  				= new Label("Zugarten:");
    private TextField txtName 	 			= new TextField();
    private TextField txtOrt				= new TextField();
    private TextField txtAnzahlGleise		= new TextField();
    private TextField txtLetzteRenovierung	= new TextField();
    private TextField txtZugarten	 		= new TextField();
    private TextArea txtAnzeige  			= new TextArea();
    private Button btnEingabe 		 		= new Button("Eingabe");
    private Button btnAnzeige 		 		= new Button("Anzeige");
    private MenuBar mnbrMenuLeiste  		= new MenuBar();
    private Menu mnDatei             		= new Menu("Datei");
    private MenuItem mnItmCsvImport 		= new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport 		= new MenuItem("txt-Import");
    private MenuItem mnItmCsvExport 		= new MenuItem("csv-Export");    
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    // speichert temporaer ein Objekt vom Typ Bahnhof
    private Bahnhof bahnhof;
    
    public BahnhoefeAnwendungssystem(Stage primaryStage){
    	Scene scene = new Scene(this.pane, 700, 340);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Bahnhoefen");
    	primaryStage.show();
    	this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
       	// Labels
    	lblEingabe.setLayoutX(20);
    	lblEingabe.setLayoutY(40);
    	Font font = new Font("Arial", 24); 
    	lblEingabe.setFont(font);
    	lblEingabe.setStyle("-fx-font-weight: bold;"); 
    	lblAnzeige.setLayoutX(400);
    	lblAnzeige.setLayoutY(40);
      	lblAnzeige.setFont(font);
       	lblAnzeige.setStyle("-fx-font-weight: bold;"); 
       	lblName.setLayoutX(20);
    	lblName.setLayoutY(90);
    	lblOrt.setLayoutX(20);
    	lblOrt.setLayoutY(130);
    	lblAnzahlGleise.setLayoutX(20);
    	lblAnzahlGleise.setLayoutY(170);
    	lblLetzteRenovierung.setLayoutX(20);
    	lblLetzteRenovierung.setLayoutY(210);
    	lblZugarten.setLayoutX(20);
    	lblZugarten.setLayoutY(250);    	
       	pane.getChildren().addAll(lblEingabe, lblAnzeige, 
       		lblName, lblOrt, lblAnzahlGleise,
       		lblLetzteRenovierung, lblZugarten);
    
    	// Textfelder
     	txtName.setLayoutX(170);
    	txtName.setLayoutY(90);
    	txtName.setPrefWidth(200);
    	txtOrt.setLayoutX(170);
    	txtOrt.setLayoutY(130);
    	txtOrt.setPrefWidth(200);
    	txtAnzahlGleise.setLayoutX(170);
    	txtAnzahlGleise.setLayoutY(170);
    	txtAnzahlGleise.setPrefWidth(200);
      	txtLetzteRenovierung.setLayoutX(170);
    	txtLetzteRenovierung.setLayoutY(210);
    	txtLetzteRenovierung.setPrefWidth(200);
    	txtZugarten.setLayoutX(170);
    	txtZugarten.setLayoutY(250);
    	txtZugarten.setPrefWidth(200);
      	pane.getChildren().addAll( 
     		txtName, txtOrt, txtAnzahlGleise,
     		txtLetzteRenovierung, txtZugarten);
     	
        // Textbereich	
        txtAnzeige.setEditable(false);
     	txtAnzeige.setLayoutX(400);
    	txtAnzeige.setLayoutY(90);
     	txtAnzeige.setPrefWidth(270);
    	txtAnzeige.setPrefHeight(185);
       	pane.getChildren().add(txtAnzeige); 
       	
        // Buttons
        btnEingabe.setLayoutX(20);
        btnEingabe.setLayoutY(290);
        btnAnzeige.setLayoutX(400);
        btnAnzeige.setLayoutY(290);
        pane.getChildren().addAll(btnEingabe, btnAnzeige); 
        
 		// Menue
  	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(mnItmCsvImport);
  	    this.mnDatei.getItems().add(mnItmTxtImport);
  	    this.mnDatei.getItems().add(new SeparatorMenuItem());
  	    this.mnDatei.getItems().add(mnItmCsvExport);
 	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    btnEingabe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
        	    nehmeBahnhofAuf();
            }
	    });
	    btnAnzeige.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	            zeigeBahnhoefeAn();
	        } 
   	    });
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	       	 	leseAusDatei("csv");
	    	}
	    });
	    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		     	leseAusDatei("txt");
		    }
    	});
	    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				schreibeBahnhoefeInCsvDatei();
			}	
	    });
    }
    
    private void nehmeBahnhofAuf(){
    	try{ 
    		this.bahnhof = new Bahnhof(
    			txtName.getText(), 
   	            txtOrt.getText(),
   	            Integer.parseInt(txtAnzahlGleise.getText()),
   	        	Integer.parseInt(txtLetzteRenovierung.getText()),
    		    txtZugarten.getText().split(";"));
    		zeigeInformationsfensterAn("Der Bahnhof wurde aufgenommen!");
       	}
       	catch(Exception exc){
       		zeigeFehlermeldungsfensterAn(exc.getMessage());
     	}
    }
   
    private void zeigeBahnhoefeAn(){
    	if(this.bahnhof != null){
    		txtAnzeige.setText(
    			this.bahnhof.gibBahnhofZurueck(' '));
    	}
    	else{
    		zeigeInformationsfensterAn("Bisher wurde keine Bahnhof aufgenommen!");
    	}
    }    
		  
    private void leseAusDatei(String typ){
    	try {
      		if("csv".equals(typ)){
      			BufferedReader ein = new BufferedReader(new FileReader("Bahnhof.csv"));
      			String[] zeile = ein.readLine().split(";");
      			this.bahnhof = new Bahnhof(zeile[0], 
      				zeile[1], 
      				Integer.parseInt(zeile[2]), 
      				Integer.parseInt(zeile[3]), 
      				zeile[4].split("_"));
      				ein.close();
      	  			zeigeInformationsfensterAn(
      	  	   			"Der Bahnhof wurde gelesen!");
      		}
       		else{
	   			zeigeInformationsfensterAn(
	   				"Noch nicht implementiert!");
	   		}
		}
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Lesen!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Lesen!");
		}
	}
		
	private void schreibeBahnhoefeInCsvDatei() {
		try {
			BufferedWriter aus 
				= new BufferedWriter(new FileWriter("BahnhoefeAusgabe.csv", true));
			aus.write(bahnhof.gibBahnhofZurueck(';'));
			aus.close();
   			zeigeInformationsfensterAn(
	   			"Die Bahnhoefe wurden gespeichert!");
		}	
		catch(IOException exc){
			zeigeFehlermeldungsfensterAn(
				"IOException beim Speichern!");
		}
		catch(Exception exc){
			zeigeFehlermeldungsfensterAn(
				"Unbekannter Fehler beim Speichern!");
		}
	}

    private void zeigeInformationsfensterAn(String meldung){
    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
    		"Information", meldung).zeigeMeldungsfensterAn();
    }	
    
    void zeigeFehlermeldungsfensterAn(String meldung){
       	new MeldungsfensterAnzeiger(AlertType.ERROR,
        	"Fehler", meldung).zeigeMeldungsfensterAn();
    }

}
