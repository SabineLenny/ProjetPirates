package Controleurs;

import Entite.Pirate;

public class ControlCasePoison {
    
    private final ControlLancerDeDes controlDés = new ControlLancerDeDes();
    
    public String appliquerEffet(Pirate pirate){
        int[] resultat = controlDés.lancerDes(1);
        pirate.setDureeEmpoisonnement(resultat[0]);
        pirate.soigner();
        return pirate.getNom() + " est empoisonne pendant " + resultat[0] + " tours.";
    }
}
