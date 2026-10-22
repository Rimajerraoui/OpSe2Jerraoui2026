package gui;



import business.BahnhoefeModel;
import business.Bahnhof;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ownUtil.MeldungsfensterAnzeiger;

public class BahnhoefeView {
	
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
	
		private BahnhoefeModel bahnModel;
		private BahnhoefeControl bahnControl;
	public BahnhoefeView(BahnhoefeControl bahnControl,Stage primaryStage,BahnhoefeModel bahnModel) {
		
		this.bahnModel = bahnModel;
		this.bahnControl = bahnControl;
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
		    		bahnControl.leseAusDatei("csv");
		    	}
		    });
		    mnItmTxtImport.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent e) {
			    	bahnControl.leseAusDatei("txt");
			    }
	    	});
		    mnItmCsvExport.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					bahnControl.schreibeBahnhoefeInCsvDatei();
				}	
		    });
	    }
	    
	    private void nehmeBahnhofAuf(){
	    	try{ 
	    		bahnModel.setBahnhof(new Bahnhof(
	    			txtName.getText(), 
	   	            txtOrt.getText(),
	   	            Integer.parseInt(txtAnzahlGleise.getText()),
	   	        	Integer.parseInt(txtLetzteRenovierung.getText()),
	    		    txtZugarten.getText().split(";")));
	    		zeigeInformationsfensterAn("Der Bahnhof wurde aufgenommen!");
	       	}
	       	catch(Exception exc){
	       		zeigeFehlermeldungsfensterAn(exc.getMessage());
	     	}
	    }
	   
	    private void zeigeBahnhoefeAn(){
	    	if(bahnModel.getBahnhof()!= null){
	    		txtAnzeige.setText(
	    		bahnModel.getBahnhof().gibBahnhofZurueck(' '));
	    	}
	    	else{
	    		zeigeInformationsfensterAn("Bisher wurde keine Bahnhof aufgenommen!");
	    	}
	    }    
			  
	  
		

	     void zeigeInformationsfensterAn(String meldung){
	    	new MeldungsfensterAnzeiger(AlertType.INFORMATION,
	    		"Information", meldung).zeigeMeldungsfensterAn();
	    }	
	    
	    void zeigeFehlermeldungsfensterAn(String meldung){
	       	new MeldungsfensterAnzeiger(AlertType.ERROR,
	        	"Fehler", meldung).zeigeMeldungsfensterAn();
	    }
	
	
	

}
