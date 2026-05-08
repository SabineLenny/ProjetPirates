package Controleurs;

import Entite.Pirate;
import Entite.TypeCase;

public class ControlCase {
	//Lenny
	
	//TypesCases Case;
	
	public static String selectCase (TypeCase caseType,Pirate pirate, Pirate autrePirate) {
		switch (caseType) {
		case NORMAL: {
                    return pirate.getNom() + " a atteint une case sans effet";
		}
		case BOMBE: {
                    return ControlCaseBombe.appliquerEffet(pirate);
		}
		case EMPOISONNEMENT:{
                    return ControlCasePoison.appliquerEffet(pirate);
		}
		case SOIN:{
                    return ControlCaseSoin.appliquerEffet(pirate);
		}
		case ECHANGE:{
                    return ControlCaseEchange.appliquerEffet(pirate, autrePirate);
		}
		case DEPART:{
                    return pirate.getNom() + " est sur le départ";
		}
		case ARRIVEE:{
                    return ControlCaseArrivee.appliquerEffet(pirate);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + caseType);
		}
	}
}
