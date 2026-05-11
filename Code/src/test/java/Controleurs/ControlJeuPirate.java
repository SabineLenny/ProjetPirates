package Controleurs;

import Entite.Pirate;
import Entite.Plateau;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ControlJeuPirate{
    
    private Map<Integer,Pirate> mapPirate;
    private Plateau p;
    int indicePirate;
    boolean run;
    Scanner s = new Scanner(System.in);
    
    public Map<Integer,Pirate> getMapPirate () {
        return this.mapPirate;
    }
    
    public Plateau getPlateau () {
        return this.p;
    }
    
    public int getIndicePirate () {
        return this.indicePirate;
    }
    
    public boolean getRun () {
        return this.run;
    }
    
    public void incrementIndicePirate () {
        this.indicePirate = (this.indicePirate + 1) % 2;
    }
    
    public void instancierJeu () {
        mapPirate = new TreeMap<>();
        mapPirate.put(0, ControlPirate.creationPirate("Pirate 1"));
        mapPirate.put(1, ControlPirate.creationPirate("Pirate 2"));
        p = ControlPlateau.creationPlateau();
        System.out.println(ControlPlateau.affichagePlateau(p));
        indicePirate = 0;
        run = true; 
    }
}