package Entite;

import Controleurs.ControlLancerDeDés;

public class CaseBombe implements Case {
	//Elouan
	@Override
	public String appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		int i[]= ControlLancerDeDés.lancerDés(1);
		pirateActif.setVie(i[0]);
		String result="Le pirate perd " + Integer.toString(i[0]) + "points de vie\n";
		return result;
	}
	//

}
