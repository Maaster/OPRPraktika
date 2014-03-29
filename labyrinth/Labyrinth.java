package labyrinth;

import java.util.Random;

/**
*
* @author Maaster
*/
public class Labyrinth {
    
    private int breite;
    private int hoehe;
    private int index;
    private int anzahlWandelemente;
    private Wandelement[] wandelemente;
    private boolean[][] belegtePunkte;
    
    /**
    * Mögliche Richtungen: Links(-1,0), Hoch(0,-1),
    * Rechts(1,0), Runter(0,1).
    */
    private final Punkt[] RICHTUNG = {new Punkt(-1, 0),
                        new Punkt(0, -1), new Punkt(1, 0), new Punkt(0, 1) };
    
    /**
     * Erstellt das Labyrinth mit der angegeben Breite und Höhe.
     * @param breite Breite des Labyrinthes
     * @param hoehe Länge des Labyrinthes
     */
    public Labyrinth(int breite, int hoehe) {
        
        this.breite = breite;
        this.hoehe = hoehe;
        anzahlWandelemente = (breite + 1) * (hoehe + 1);
        wandelemente = new Wandelement[anzahlWandelemente];
        belegtePunkte = new boolean[breite + 1][hoehe + 1]; 
        index = 0;
        
        erzeugeWandelemente();
    }
    
    /**
     * Gibt die Höhe des Labyrinthes wieder.
     * @return Höhe des Labyrinthes
     */
    public int gibHoehe() {
        return hoehe;
    }
    
    /**
     * Gibt die Breite des Labyrinthes wieder.
     * @return Breite des Labyrinthes
     */
    public int gibBreite() {
        return breite;
    }
    
    /**
     * Gibt die Anzahl aller möglichen Wandelemente wieder.
     * @return Anzahl aller möglichen Wandelemente
     */
    public int gibAnzahlWandelemente() {
        return anzahlWandelemente;
    }
    
    /**
     * Gibt den Startpunkt des x-ten Wandelementes wieder, wobei x
     * durch den übergebenen Parameter index definiert wird.
     * @param index Wandelement, von dem Startpunkt gefordert wird
     * @return Startpunkt des Wandelementes index
     */
    public Punkt gibStartpunkt(int index) {
        return wandelemente[index].getStartpunkt();
    }
    
    /**
     * Gibt den Endpunkt des x-ten Wandelementes wieder, wobei x
     * durch den übergebenen Parameter index definiert wird.
     * @param index Wandelement, von dem Endpunkt gefordert wird
     * @return Endpunkt des Wandelementes index
     */
    public Punkt gibEndpunkt(int index) {
        return wandelemente[index].getEndpunkt();
    }
    
    /**
     * Erzeugt das Labyrinth.
     */
    public void erzeugeWandelemente() {
        erzeugeAussenwand();
        
        //Zufällige Richtung und Punkt auswählen
        Random rand = new Random();
        
        Punkt aktuellerPunkt;
        Punkt naechsterPunkt;
        
        //Restliche Wandelemente erzeugen
        while(index < anzahlWandelemente){
            
            //Zufälligen Endpunkt auswählen
            aktuellerPunkt = wandelemente[rand.nextInt(index)].getEndpunkt();
            
                wandelemente[index] = new Wandelement();

                //Neuen Startpunkt setzen
                wandelemente[index].setStartpunkt(aktuellerPunkt);

                belegtePunkte[aktuellerPunkt.gibX()][aktuellerPunkt.gibY()] = true;

                //Neuen Endpunkt durch zufällige Richtungs-Addierung setzen und
                //testen, ob er gültig ist

                
                naechsterPunkt = aktuellerPunkt.addiere
                                            (RICHTUNG[rand.nextInt(4)]);

                //Falls gültig
                if(istGueltigerPunkt(naechsterPunkt)){
                    //Endpunkt setzen
                    wandelemente[index].setEndpunkt(naechsterPunkt);

                    //Als belegt setzen
                    belegtePunkte[naechsterPunkt.gibX()]
                            [naechsterPunkt.gibY()] = true;

                    index++;
                }
        }
    }
    
    /**
     * Erzeugt die Aussenwand des Labyrinthes.
     */
    public void erzeugeAussenwand() {
        
        for(int i = 0; i <= breite - 1; i++){
            //Obere Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].setStartpunkt(new Punkt(i,0));
            
            wandelemente[index].setEndpunkt(
                    new Punkt(i, 0).addiere(RICHTUNG[2]));
            
            belegtePunkte[wandelemente[index].getStartpunkt().gibX()]
                    [wandelemente[index].getStartpunkt().gibY()] = true;
            belegtePunkte[wandelemente[index].getEndpunkt().gibX()]
                    [wandelemente[index].getEndpunkt().gibY()] = true;
            
            index++;
            
            //Untere Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].setStartpunkt(new Punkt(i, hoehe));
            wandelemente[index].setEndpunkt(
                    new Punkt(i, hoehe).addiere(RICHTUNG[2]));
            
            belegtePunkte[wandelemente[index].getStartpunkt().gibX()]
                    [wandelemente[index].getStartpunkt().gibY()] = true;
            belegtePunkte[wandelemente[index].getEndpunkt().gibX()]
                    [wandelemente[index].getEndpunkt().gibY()] = true;
            
            index++;
        }
        
        for(int i = 0; i < hoehe; i++){
            //Linke Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].setStartpunkt(new Punkt(0, i));
            wandelemente[index].setEndpunkt(
                    new Punkt(0, i).addiere(RICHTUNG[3]));
            
            belegtePunkte[wandelemente[index].getStartpunkt().gibX()]
                    [wandelemente[index].getStartpunkt().gibY()] = true;
            belegtePunkte[wandelemente[index].getEndpunkt().gibX()]
                    [wandelemente[index].getEndpunkt().gibY()] = true;
            
            index++;
            
            //Rechte Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].setStartpunkt(new Punkt(breite, i));
            wandelemente[index].setEndpunkt(
                    new Punkt(breite, i).addiere(RICHTUNG[3]));
            
            belegtePunkte[wandelemente[index].getStartpunkt().gibX()]
                    [wandelemente[index].getStartpunkt().gibY()] = true;
            belegtePunkte[wandelemente[index].getEndpunkt().gibX()]
                    [wandelemente[index].getEndpunkt().gibY()] = true;
            
            index++;
        }
    }
    
    /**
     * Überprüft, ob der angegebene Punkt gültig ist, das heisst
     * ob er innerhalb des Labyrinthes liegt und noch nicht belegt ist.
     * @param naechsterPunkt Der zu überprüfende Punkt
     * @return true genau dann, wenn Punkt innerhalb des Labyrinthes
     * ist und noch nicht belegt ist
     */
    public boolean istGueltigerPunkt(Punkt naechsterPunkt) {
        boolean istGueltig = true;
        
        //Check ob Wand ausserhalb ist
        if(!naechsterPunkt.istInnerhalb(
                    new Punkt(0,1),new Punkt(breite, hoehe))){
            istGueltig = false;
        }
            
        //Check ob Wand bereits existiert 
        //bzw ein geschlossener Raum gezogen wird
        if(istGueltig 
            && belegtePunkte[naechsterPunkt.gibX()][naechsterPunkt.gibY()]) {
            istGueltig = false;
        }
        
        return istGueltig;
    }
}

