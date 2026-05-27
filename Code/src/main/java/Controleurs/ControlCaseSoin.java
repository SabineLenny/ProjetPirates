package Controleurs;

import Entite.Pirate;

public class ControlCaseSoin implements ControlCaseSpecial {
    
    public String appliquerEffet(Pirate pirate1, Pirate pirate2){
        pirate1.setDureeEmpoisonnement(0);
        pirate1.soigner();
        return pirate1.getNom() + " est soigne de 1 pv!\n";
    }
}
