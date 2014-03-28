/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package labyrinth;

import util.Punkt;
/**
 *
 * @author maaster
 */
public class Wandelement {
    
    private Punkt startpunkt;
    private Punkt endpunkt;
    
    public Wandelement() {
        
    }

    /**
     * @return the startpunkt
     */
    public Punkt getStartpunkt() {
        return startpunkt;
    }

    /**
     * @param startpunkt the startpunkt to set
     */
    public void setStartpunkt(Punkt startpunkt) {
        this.startpunkt = startpunkt;
    }

    /**
     * @return the endpunkt
     */
    public Punkt getEndpunkt() {
        return endpunkt;
    }

    /**
     * @param endpunkt the endpunkt to set
     */
    public void setEndpunkt(Punkt endpunkt) {
        this.endpunkt = endpunkt;
    }
    
    
}
