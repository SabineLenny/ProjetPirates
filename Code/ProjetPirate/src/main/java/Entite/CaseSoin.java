package Entite;

public class CaseSoin implements Case{
	//Elouan
	@Override
	public void appliquerEffet(Pirate pirate) {
		pirate.setDuréeEmpoisonnement(0);
		pirate.soigner();
	}
	//

}
