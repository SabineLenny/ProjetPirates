package Controleurs;

import Entite.Pirate;

public class ControlCasePoison {
    
    private final ControlLancerDeDes CONTROL_DES = new ControlLancerDeDes();
    
    public String appliquerEffet(Pirate pirate1, Pirate pirate2){
        int[] resultat = CONTROL_DES.lancerDes(1);
        pirate1.setDureeEmpoisonnement(resultat[0]);
        pirate1.soigner();
        return pirate1.getNom() + " est empoisonne pendant " + resultat[0] + " tours.";
    }
}
