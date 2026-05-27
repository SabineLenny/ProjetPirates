package Controleurs;

import Entite.Pirate;

public class ControlCaseBombe implements ControlCaseSpecial {
    
    private final ControlLancerDeDes CONTROL_DES = new ControlLancerDeDes();
    
    public String appliquerEffet(Pirate pirate1, Pirate pirate2){
        int[] resultat = CONTROL_DES.lancerDes(1);
        int degats = 3;
        pirate1.prendreDegats(degats);
        return pirate1.getNom() + " est tombe sur une BOMBAAAA ! Il a pris " + Integer.toString(degats) + "\n";
    }
}
