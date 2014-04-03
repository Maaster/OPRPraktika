/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package personal;

/**
 *
 * @author Maaster
 */
public class MitarbeiterTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mitarbeiter walter = new Mitarbeiter("Walter Winkelmann");
        Vorgesetzter waltraud = new Vorgesetzter("Waltraud Wichtig");
        Vorgesetzter hermann = new Vorgesetzter("Hermann Wichtiger");
        walter.setzeVorgesetzten(waltraud);
        waltraud.setzeVorgesetzten(hermann);
        System.out.println(walter.darfBestellen(15));
        System.out.println(walter.darfBestellen(20));
        System.out.println(walter.darfBestellen(21));
        System.out.println(hermann.darfBestellen(15));
        System.out.println(hermann.darfBestellen(25));
        Mitarbeiter.setzeAllgemeinesLimit(30);
        Mitarbeiter willi = new Mitarbeiter("Willi Winzig");
        System.out.println(walter.darfBestellen(21));
        System.out.println(hermann.darfBestellen(25));
        waltraud.setzeBestelllimit(10);
        System.out.println(waltraud.darfBestellen(10));
        System.out.println(waltraud.darfBestellen(11));
        waltraud.setzeBestelllimit(5000);
        System.out.println(waltraud.darfBestellen(2000));
        System.out.println(waltraud.darfBestellen(7000));
        System.out.println(waltraud.gibInfo());
        System.out.println(waltraud.gibHierarchie());
        System.out.println(hermann.gibInfo());
        System.out.println(hermann.gibHierarchie());
        System.out.println(walter.gibInfo());
        System.out.println(walter.gibHierarchie());
        waltraud.setzeVorgesetzten(null);
        System.out.println(walter.gibHierarchie());
        System.out.println(willi.gibInfo());
        System.out.println(willi.gibHierarchie());
        
    }
}
