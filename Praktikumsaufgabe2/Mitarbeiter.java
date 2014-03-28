package Praktikumsaufgabe2;

/**
 *
 * @author Maaster
 */
public class Mitarbeiter {
    
    protected String name;
    protected static int bestelllimit = 20;
    Vorgesetzter vorgesetzten;
    protected String rang;
    
    public Mitarbeiter(String name) {
        this.name = name;
        rang = "freier Mitarbeiter";
    }
    
    public static void setzeAllgemeinesLimit(int limit) {
        bestelllimit = limit;
    }
    
    public Vorgesetzter gibVorgesetzten() {
        return vorgesetzten;
    }
    
    public void setzeVorgesetzten(Vorgesetzter neuerVorgesetzter) {
        
        if(neuerVorgesetzter != null) {
            vorgesetzten = neuerVorgesetzter;
            if(rang.equals("freier Mitarbeiter"))
            rang = "Mitarbeiter";
        }
        else {
            vorgesetzten = null;
            if(rang.equals("Mitarbeiter"))
                rang = "freier Mitarbeiter";
        }
            
    }
    
    public boolean darfBestellen(int kosten) {
        return kosten <= bestelllimit;
    }
    
    public String gibInfo() {
        String infoText;
        
        if(vorgesetzten == null)
                infoText = "Ich bin freier Mitarbeiter, Name " + name 
                            + ". Mein Bestelllimit ist " 
                                + bestelllimit + " EUR.";
        else
            infoText = "Ich bin Mitarbeiter " + ", Name " + name 
                            + ". Mein Vorgesetzter ist " + vorgesetzten.name
                                + ". Mein Bestelllimit ist " 
                                    + bestelllimit + " EUR.";
        
        return infoText;
    }
    
    public String gibHierarchie() {
        
        return (vorgesetzten == null)
                ? rang + " " + name
                : vorgesetzten.gibHierarchie() + '\n' +  rang + " " + name;
    }
}
