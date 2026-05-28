package Controleurs;

import Entite.Pirate;
import Entite.Plateau;
import java.util.Map;
import java.util.TreeMap;

public class ControlJeuPirate{
    
    private Map<Integer,Pirate> mapPirate;
    private Plateau p;
    int indicePirate;
    boolean run;
    private final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
    private final ControlPirate CONTROL_PIRATE = new ControlPirate();
    
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
    
    public void instancierJeu (String pirate1, String pirate2) {
        mapPirate = new TreeMap<>();
        mapPirate.put(0, CONTROL_PIRATE.creationPirate(pirate1));
        mapPirate.put(1, CONTROL_PIRATE.creationPirate(pirate2));
        p = CONTROL_PLATEAU.creationPlateau();
        indicePirate = 0;
        run = true; 
    }
}