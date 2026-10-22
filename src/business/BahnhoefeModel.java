package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BahnhoefeModel {
	
	private Bahnhof bahnhof;

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}
	
	public void leseAusDatei() throws IOException {
		BufferedReader ein = new BufferedReader(new FileReader("Bahnhof.csv"));
			String[] zeile = ein.readLine().split(";");
			this.bahnhof = new Bahnhof(zeile[0], 
				zeile[1], 
				Integer.parseInt(zeile[2]), 
				Integer.parseInt(zeile[3]), 
				zeile[4].split("_"));
				ein.close();
		
	}
	
	public void schreibeBahnhoefeInCsvDatei() throws IOException {
		BufferedWriter aus 
		= new BufferedWriter(new FileWriter("BahnhoefeAusgabe.csv", true));
		aus.write(bahnhof.gibBahnhofZurueck(';'));
		aus.close();
	} 

}
