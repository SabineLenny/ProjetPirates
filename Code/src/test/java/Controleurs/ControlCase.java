package Controleurs;

import Entite.Case;
import Entite.Pirate;
import Entite.TypeCase;

public class ControlCase {
	//Lenny
	
	//TypesCases Case;
	
	void select_case (TypeCase caseType,Pirate pirate, Pirate autrePirate) {
		switch (caseType) {
		case NORMAL: {
                    break;
		}
		case BOMBE: {
                    ControlCaseBombe.appliquerEffet(pirate);
                    break;
		}
		case EMPOISONNEMENT:{
                    ControlCasePoison.appliquerEffet(pirate);
                    break;
		}
		case SOIN:{
                    ControlCaseSoin.appliquerEffet(pirate);
                    break;
		}
		case ECHANGE:{
                    ControlCaseEchange.appliquerEffet(pirate, autrePirate);
                    break;
		}
		case DEPART:{
                    break;
		}
		case ARRIVEE:{
                    ControlCaseArrivee.appliquerEffet(pirate);
                    break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + caseType);
		}
	}
	
	
	
	
	
}
