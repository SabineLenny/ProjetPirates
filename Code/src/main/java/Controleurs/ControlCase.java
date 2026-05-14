package Controleurs;

import Entite.Pirate;
import Entite.TypeCase;

public class ControlCase {
    
    private final ControlCaseBombe CONTROL_BOMBE = new ControlCaseBombe();
    private final ControlCasePoison CONTROL_POISON= new ControlCasePoison();
    private final ControlCaseSoin CONTROL_SOIN = new ControlCaseSoin();
    private final ControlCaseEchange CONTROL_ECHANGE = new ControlCaseEchange();
    
    //Lenny

    public String selectCase (TypeCase caseType,Pirate pirate, Pirate autrePirate) {
        switch (caseType) {
        case NORMAL: {
            return pirate.getNom() + " a atteint une case sans effet";
        }
        case BOMBE: {
            return CONTROL_BOMBE.appliquerEffet(pirate,null);
        }
        case EMPOISONNEMENT:{
            return CONTROL_POISON.appliquerEffet(pirate,null);
        }
        case SOIN:{
            return CONTROL_SOIN.appliquerEffet(pirate,null);
        }
        case ECHANGE:{
            return CONTROL_ECHANGE.appliquerEffet(pirate, autrePirate);
        }
        case DEPART:{
            return pirate.getNom() + " est sur le depart";
        }
        case ARRIVEE:{
            return pirate.getNom() + " est arrive";
        }
        default:
                throw new IllegalArgumentException("Unexpected value: " + caseType);
        }
    }
}
