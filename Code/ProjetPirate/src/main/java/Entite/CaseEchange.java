package Entite;

public class CaseEchange implements Case {

	@Override
	public String appliquerEffet(Pirate pirateActif,Pirate autrePirate) {
		int i=pirateActif.getPosition();
		pirateActif.setPosition(autrePirate.getPosition());
		autrePirate.setPosition(i);
		return "Les 2 pirates ont échangé de place.\n";
	}

}
