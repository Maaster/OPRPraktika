/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package labyrinth;

import java.util.Random;
import labyrinth.util.Punkt;

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
    
    /**
    * Mögliche Richtungen: Hoch(-1,0), Runter(1,0), Rechts(0,1), Links(0,-1)
    */
    private static final Punkt[] RICHTUNG = { new Punkt(-1, 0),
                        new Punkt(0, -1),  new Punkt(1, 0), new Punkt(0, 1) };
    
    public Labyrinth(int breite, int hoehe) {
        
        this.breite = breite;
        this.hoehe = hoehe;
        anzahlWandelemente = (breite + 1) * (hoehe + 1);
        wandelemente = new Wandelement[anzahlWandelemente];
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
        return wandelemente[index].startpunkt;
    }
    
    public Punkt gibEndpunkt(int index) {
        return wandelemente[index].endpunkt;
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
            aktuellerPunkt = wandelemente[rand.nextInt(index)].endpunkt;
            
            wandelemente[index] = new Wandelement();
            
            //Neuen Startpunkt setzen
            wandelemente[index].startpunkt = aktuellerPunkt;
            //Neuen Endpunkt durch zufällige Richtungs-Addierung setzen und 
            // testen, ob er gültig ist
            
            naechsterPunkt = aktuellerPunkt.addiere(RICHTUNG[rand.nextInt(4)]);
            
            if(istGueltigerPunkt(naechsterPunkt))
            {
                wandelemente[index].endpunkt = naechsterPunkt;
            
                index++;
            }
        }
    }
    
    public void erzeugeAussenwand() {
        
        for(int i = 0; i <= breite - 1; i++) {
            //Obere Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].startpunkt = new Punkt(i, 0);
            wandelemente[index].endpunkt = new Punkt(i, 0).addiere(RICHTUNG[2]);
            index++;
            
            //Linke Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].startpunkt = new Punkt(0, i);
            wandelemente[index].endpunkt = new Punkt(0, i).addiere(RICHTUNG[1]);
            index++;
            
            //Rechte Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].startpunkt = new Punkt(breite, i);
            wandelemente[index].endpunkt = new Punkt(breite, i).addiere(RICHTUNG[1]);
            index++;
            
            //Untere Seite
            wandelemente[index] = new Wandelement();
            wandelemente[index].startpunkt = new Punkt(i, hoehe);
            wandelemente[index].endpunkt = new Punkt(i, hoehe).addiere(RICHTUNG[2]);
            index++;
        }
    }
    
    public boolean istGueltigerPunkt(Punkt naechsterPunkt) {
        boolean istGueltig = true;
        
        if(naechsterPunkt.gibX() < 0 || naechsterPunkt.gibX() > breite)
            istGueltig = false;
        
        if(naechsterPunkt.gibY() < 0 || naechsterPunkt.gibY() > hoehe - 1)
            istGueltig = false;
        
        for(int i = 0; i < index &&  istGueltig; i++) {
            if((naechsterPunkt.gibX() == wandelemente[i].startpunkt.gibX()) 
                    && (naechsterPunkt.gibY() == wandelemente[i].startpunkt.gibY()))
                istGueltig = false;
            
            if((naechsterPunkt.gibX() == wandelemente[i].endpunkt.gibX()) 
                    && (naechsterPunkt.gibY() == wandelemente[i].endpunkt.gibY()))
                istGueltig = false;
        }
        
        return istGueltig;
    }
}
