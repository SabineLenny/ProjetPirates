package Controleurs;

import Entite.Pirate;

public class ControlCasePoison {
    
    private final ControlLancerDeDés controlDés = new ControlLancerDeDés();
    
    public String appliquerEffet(Pirate pirate){
        int[] resultat = controlDés.lancerDés(1);
        pirate.setDuréeEmpoisonnement(resultat[0]);
        pirate.soigner();
        return pirate.getNom() + " est empoisonne pendant " + resultat[0] + " tours.";
    }
}
