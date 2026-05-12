package Controleurs;

import Entite.Pirate;

public class ControlCasePoison {
    public static String appliquerEffet(Pirate pirate){
        int[] resultat = ControlLancerDeDés.lancerDés(1);
        pirate.setDuréeEmpoisonnement(resultat[0]);
        pirate.soigner();
        return pirate.getNom() + " est empoisonne pendant " + resultat[0] + " tours.";
    }
}
