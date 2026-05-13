package Controleurs;

import Entite.Pirate;

public class ControlCaseSoin {
    
    public String appliquerEffet(Pirate pirate){
        pirate.setDureeEmpoisonnement(0);
        pirate.soigner();
        return pirate.getNom() + " est soigne de 1 pv!";
    }
}
