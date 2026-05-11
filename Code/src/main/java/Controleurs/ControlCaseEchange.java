package Controleurs;

import Entite.Pirate;

public class ControlCaseEchange {
    public static String appliquerEffet(Pirate pirate1, Pirate pirate2){
        int temp = pirate1.getPosition();
        pirate1.setPosition(pirate2.getPosition());
        pirate2.setPosition(temp);
        return "Les positions de " + pirate1.getNom() + " et " 
                + pirate2.getNom() + " sont échangées. " + pirate1.getNom() + " est sur la case " + Integer.toString(pirate1.getPosition()) + " et " + pirate2.getNom() + " est sur la case " + Integer.toString(pirate2.getPosition()) + ".\n";
    }
}
