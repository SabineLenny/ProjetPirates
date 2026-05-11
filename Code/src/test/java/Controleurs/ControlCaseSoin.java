package Controleurs;

import Entite.Pirate;

public class ControlCaseSoin {
    public static String appliquerEffet(Pirate pirate){
        pirate.setDuréeEmpoisonnement(0);
        pirate.soigner();
        return pirate.getNom() + " est soigné de 1 pv!";
    }
}
