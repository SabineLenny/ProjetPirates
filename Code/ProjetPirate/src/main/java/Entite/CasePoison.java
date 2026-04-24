package Entite;

import Controleurs.ControlLancerDeDés;

public class CasePoison implements Case {
	//Elouan
	@Override
	public String appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		int i[]= ControlLancerDeDés.lancerDés(1);
		pirateActif.setDuréeEmpoisonnement(i[0]);
		String result="Le pirate est empoisonné pour " + Integer.toString(i[0]) + "tours!!!\n";
		return result;
	}
	//

}
