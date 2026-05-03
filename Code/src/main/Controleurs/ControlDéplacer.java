package Controleurs;

import Entite.Pirate;

public class ControlDéplacer {
	//Elouan
	public static String deplacer(Pirate pirate,int distance) {
		int position= pirate.getPosition()+distance;
		if (position>30) {
			position=30 - (position-30);
		}
		pirate.setPosition(position);
                return "Le pirate " + pirate.getNom() + " s'est deplacé de " + distance +" cases";
	}
	//Elouan
}