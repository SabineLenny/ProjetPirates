package Entite;

import Controleurs.ControlLancerDeDés;

public class CasePoison implements Case {
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		ControlLancerDeDés lancerDes = new ControlLancerDeDés();
		int i[]= lancerDes.lancerDés(1);
		pirateActif.setDuréeEmpoisonnement(i[0]);
		
	}
	//

}
