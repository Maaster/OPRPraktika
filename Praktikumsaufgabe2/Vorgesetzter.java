package Praktikumsaufgabe2;

/**
 *
 * @author Maaster
 */
public class Vorgesetzter extends Mitarbeiter {
    
    private int bestelllimitVorgesetzter;
    
    public Vorgesetzter(String name) {
        super(name);
        this.bestelllimitVorgesetzter = -1;
        super.rang = "Vorgesetzter";
    }
    
    public void setzeBestelllimit(int limit) {
        this.bestelllimitVorgesetzter = limit;
    }
    
    @Override
    public boolean darfBestellen(int kosten) {
        
        boolean darfBestellen;
        
        if(bestelllimitVorgesetzter > 0)
            darfBestellen = kosten <= bestelllimitVorgesetzter;
        else
            darfBestellen = kosten <= Mitarbeiter.bestelllimit;
        
        return darfBestellen;
    }
    
    @Override
    public String gibInfo() {
        String infoText;
        int limit;
        
        if(bestelllimitVorgesetzter > 0)
            limit = bestelllimitVorgesetzter;
        else
            limit = bestelllimit;
        
        if(vorgesetzten == null)
            infoText = "Ich bin Vorgesetzter, Name " + name 
                            + ". Mein Bestelllimit ist "
                                + limit + " EUR.";
        else
            infoText = "Ich bin Vorgesetzter, Name " + name 
                            + ". Mein Vorgesetzter ist " + vorgesetzten.name 
                                + ". Mein Bestelllimit ist "
                                    + limit + " EUR.";
        
        return infoText;
    }
}
