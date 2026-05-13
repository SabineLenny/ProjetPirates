package Controleurs;

import Entite.Pirate;

public class ControlDéplacer {
    
    private final ControlLancerDeDés controlDés = new ControlLancerDeDés();
    //Elouan
    public String deplacer(Pirate pirate) {
        int[] dés = controlDés.lancerDés(2);
        int distance = controlDés.additionDés(dés);
        int position= pirate.getPosition()+distance;
        if (position>30) {
                position=30 - (position-30);
        }
        pirate.setPosition(position);
        return "Le pirate " + pirate.getNom() + " s'est deplace de " + distance +" cases";
    }
    //Elouan
}