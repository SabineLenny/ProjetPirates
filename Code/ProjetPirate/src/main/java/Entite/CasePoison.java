package Entite;

import Controleurs.ControlLancerDeDés;

public class CasePoison implements Case {
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirate) {
		ControlLancerDeDés lancerDes = new ControlLancerDeDés();
		int i[]= lancerDes.lancerDés(1);
		pirate.setDuréeEmpoisonnement(i[0]);
		
	}
	//

}
