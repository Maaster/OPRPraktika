package labyrinth;

import java.util.Random;
import util.Punkt;

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
    * Mögliche Richtungen: Links(-1,0), Hoch(0,-1) Rechts(1,0), Runter(0,1), 
    */
    private final Punkt[] RICHTUNG = { new Punkt(-1, 0),
                        new Punkt(0, -1), new Punkt(1, 0), new Punkt(0, 1) };
    
    
    public Labyrinth(int breite, int hoehe) {
        
        this.breite = breite;
        this.hoehe = hoehe;
        anzahlWandelemente = (breite + 1) * (hoehe + 1);
        wandelemente = new Wandelement[anzahlWandelemente];
        belegtePunkte = new boolean[breite + 1][hoehe + 1]; 
        index = 0;
        
        erzeugeWandelemente();
    }
    
    public int gibHoehe() {
        return hoehe;
    }
    
    public int gibBreite() {
        return breite;
    }
    
    public int gibAnzahlWandelemente() {
        return anzahlWandelemente;
    }
    
    public Punkt gibStartpunkt(int index) {
        return wandelemente[index].getStartpunkt();
    }
    
    public Punkt gibEndpunkt(int index) {
        return wandelemente[index].getEndpunkt();
    }
    
    public void erzeugeWandelemente() {
        erzeugeAussenwand();
        
        //Zufällige Richtung und Punkt auswählen
        Random rand = new Random();
        
        Punkt aktuellerPunkt;
        Punkt naechsterPunkt;
        
        //Restliche Wandelemente erzeugen
        while(index < anzahlWandelemente) {
            
            //Zufälligen Endpunkt auswählen
            aktuellerPunkt = wandelemente[rand.nextInt(index)].getEndpunkt();
            
            wandelemente[index] = new Wandelement();
            
            //Neuen Startpunkt setzen
            wandelemente[index].setStartpunkt(aktuellerPunkt);
            
            belegtePunkte[aktuellerPunkt.gibX()][aktuellerPunkt.gibY()] = true;
            
            //Neuen Endpunkt durch zufällige Richtungs-Addierung setzen und
            //testen, ob er gültig ist
            
            naechsterPunkt = aktuellerPunkt.addiere(RICHTUNG[rand.nextInt(4)]);
            
            
            if(istGueltigerPunkt(naechsterPunkt))
            {
                wandelemente[index].setEndpunkt(naechsterPunkt);
                                
                belegtePunkte[naechsterPunkt.gibX()]
                        [naechsterPunkt.gibY()] = true;
                
                index++;
            }
        }
    }
    
    public void erzeugeAussenwand() {
        
        for(int i = 0; i <= breite - 1; i++) {
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
        
        for(int i = 0; i < hoehe; i++) {
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
    
    public boolean istGueltigerPunkt(Punkt naechsterPunkt) {
        boolean istGueltig = true;
        
        //Check ob Wand ausserhalb ist
        if(!naechsterPunkt.istInnerhalb(
                    new Punkt(0,1), new Punkt(breite, hoehe)))
            istGueltig = false;
        
        //Check ob Wand bereits existiert 
        //bzw ein geschlossener Raum gezogen wird
        if(istGueltig &&
                belegtePunkte[naechsterPunkt.gibX()][naechsterPunkt.gibY()])
            istGueltig = false;
            
        return istGueltig;
    }
}