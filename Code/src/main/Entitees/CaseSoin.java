package Entite;

public class CaseSoin implements Case{
	//Elouan
	@Override
	public String appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		pirateActif.setDuréeEmpoisonnement(0);
		pirateActif.soigner();
		String result= "Le pirate est soigné, ca fait du bien.\n";
		return result;
	}
	//

}
