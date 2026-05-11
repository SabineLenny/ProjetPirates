package Controleurs;

import Entite.Pirate;

public class ControlCaseBombe {
    public static String appliquerEffet(Pirate pirate){
        int[] resultat = ControlLancerDeDés.lancerDés(1);
        int degats = resultat[0]/2;
        if (degats == 0) degats = 1;
        pirate.prendreDegats(degats);
        return pirate.getNom() + " est tombé sur une BOMBAAAA ! Il a pris " + Integer.toString(degats) + "!\n";
    }
}
