package Controleurs;

import Entite.Pirate;

public class ControlCaseBombe {
    
    private final ControlLancerDeDes controlDés = new ControlLancerDeDes();
    
    public String appliquerEffet(Pirate pirate){
        int[] resultat = controlDés.lancerDes(1);
        int degats = resultat[0]/2;
        if (degats == 0) degats = 1;
        pirate.prendreDegats(degats);
        return pirate.getNom() + " est tombe sur une BOMBAAAA ! Il a pris " + Integer.toString(degats) + "!\n";
    }
}
