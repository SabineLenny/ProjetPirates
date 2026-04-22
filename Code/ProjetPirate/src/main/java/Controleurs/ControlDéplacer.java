package Controleurs;

import Entite.Pirate;

public class ControlDéplacer {
	public static void deplacer(Pirate pirate,int distance) {
		int position= pirate.getPosition()+distance;
		if (position>30) {
			position=30 - (position-30);
		}
		pirate.setPosition(position);
	}
}
