package Controleurs;

import Entite.Pirate;

public class ControlDéplacer {
	//Elouan
	public static String deplacer(Pirate pirate) {
            int[] dés = ControlLancerDeDés.lancerDés(2);
            int distance = ControlLancerDeDés.additionDés(dés);
            int position= pirate.getPosition()+distance;
            if (position>30) {
                    position=30 - (position-30);
            }
            pirate.setPosition(position);
            return "Le pirate " + pirate.getNom() + " s'est deplace de " + distance +" cases";
	}
	//Elouan
}