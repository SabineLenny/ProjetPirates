package Controleurs;

import Entite.Pirate;

public class ControlDéplacer {
    
    private final ControlLancerDeDes controlDés = new ControlLancerDeDes();
    //Elouan
    public String deplacer(Pirate pirate) {
        int[] dés = controlDés.lancerDes(2);
        int distance = controlDés.additionDes(dés);
        int position= pirate.getPosition()+distance;
        if (position>30) {
                position=30 - (position-30);
        }
        pirate.setPosition(position);
        return "Le pirate " + pirate.getNom() + " s'est deplace de " + distance +" cases";
    }
    //Elouan
}