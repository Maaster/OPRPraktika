/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ausdruck;

/**
 *
 * @author Maaster
 */
public class Variable extends Ausdruck {
    
    private String variable;
    
    /**
     * Erstellt eine neue Variable.
     * @param var 
     */
    public Variable(String var) {
        variable = var;
    }

    /**
     * Gibt den Wert dieser Variable.
     * @param belegung Belegung der Variable.
     * @return Den Wert
     */
    @Override
    public int gibWert(Variablenbelegung belegung) {
        return belegung.gibWert(variable);
    }
    
    /**
     * Überprüft, ob das übergeben Objekt das gleiche ist, wie das aktuelle.
     * @param obj das zu prüfende Objekt
     * @return True genau dann, wenn das übergeben Otjekt das gleiche ist,
     * wie das aktuelle.
     */
    @Override
    public boolean equals(Object obj) {
        boolean sindGleich = true;
        
        if (obj == null) {
            sindGleich = false;
        }
        if (getClass() != obj.getClass()) {
            sindGleich = false;
        }
        
        final Variable other = (Variable) obj;
        
        if ((this.variable == null)
                ? (other.variable != null)
                : !this.variable.equals(other.variable)) {
            sindGleich = false;
        }
        
        return sindGleich;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.variable != null ? this.variable.hashCode() : 0);
        return hash;
    }
}
