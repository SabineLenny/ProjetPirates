package Controleurs;

import Entite.Pirate;

public class ControlDeplacer {
    
    private final ControlLancerDeDes controlDes = new ControlLancerDeDes();
    //Elouan
    public String deplacer(Pirate pirate) {
        int[] dés = controlDes.lancerDes(2);
        int distance = controlDes.additionDes(dés);
        int position= pirate.getPosition()+distance;
        if (position>30) {
                position=30 - (position-30);
        }
        pirate.setPosition(position);
        return "Le pirate " + pirate.getNom() + " s'est deplace de " + distance +" cases";
    }
    //Elouan
}