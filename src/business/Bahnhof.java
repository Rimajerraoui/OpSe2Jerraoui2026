package business;

public class Bahnhof {
	
    private String name;
    private String ort;
    private int anzahlGleise;
    private int letzteRenovierung;
    private String[] zugarten;
    
    public Bahnhof(String name, String ort, int anzahlGleise,
       	int letzteRenovierung, String[] zugarten){
    	this.name = name;
      	this.ort = ort;
       	this.anzahlGleise = anzahlGleise;
       	this.letzteRenovierung = letzteRenovierung;
       	this.zugarten = zugarten;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getAnzahlGleise() {
		return anzahlGleise;
	}

	public void setAnzahlGleise(int anzahlGleise) {
		this.anzahlGleise = anzahlGleise;
	}

	public int getLetzteRenovierung() {
		return letzteRenovierung;
	}

	public void setLetzteRenovierung(int letzteRenovierung) {
		this.letzteRenovierung = letzteRenovierung;
	}

	public String[] getZugarten() {
		return zugarten;
	}

	public void setZugarten(String[] zugarten) {
		this.zugarten = zugarten;
	}
	
 	public String getZugartenAlsString(char trenner) {
		String ergebnis = "";
		int i = 0;
		for(i = 0; i < this.getZugarten().length - 1; i++) {
			ergebnis = ergebnis + this.getZugarten()[i] + trenner; 
		}
		return ergebnis	+ this.getZugarten()[i];
	}
	
	public String gibBahnhofZurueck(char trenner){
  		return this.getName() + trenner 
  			+ this.getOrt() + trenner
  			+ this.getAnzahlGleise() + trenner
  		    + this.getLetzteRenovierung() + trenner + "\n"
  		    + this.getZugartenAlsString(trenner) + "\n";
  	}
}

