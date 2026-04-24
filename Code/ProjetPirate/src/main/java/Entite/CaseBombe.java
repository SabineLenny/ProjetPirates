package Entite;

import Controleurs.ControlLancerDeDés;

public class CaseBombe implements Case {
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		ControlLancerDeDés lancerDes = new ControlLancerDeDés();
		int i[]= lancerDes.lancerDés(1);
		pirateActif.setVie(i[0]);
		
	}
	//

}
