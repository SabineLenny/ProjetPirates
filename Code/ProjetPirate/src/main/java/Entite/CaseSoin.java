package Entite;

public class CaseSoin implements Case{
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		pirateActif.setDuréeEmpoisonnement(0);
		pirateActif.soigner();
	}
	//

}
