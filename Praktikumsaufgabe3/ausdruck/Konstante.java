/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

/**
 *
 * @author Maaster
 */
public class Konstante extends Ausdruck {
    
    public Konstante(int wert) {
        
    }

    @Override
    public int gibWert(Variablenbelegung belegung) {
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
