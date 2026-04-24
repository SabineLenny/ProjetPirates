package Entite;

import Controleurs.ControlLancerDeDés;

public class CaseBombe implements Case {
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirate) {
		ControlLancerDeDés lancerDes = new ControlLancerDeDés();
		int i[]= lancerDes.lancerDés(1);
		pirate.setVie(i[0]);
		
	}
	//

}
