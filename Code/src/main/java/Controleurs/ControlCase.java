package Controleurs;

import Entite.Pirate;
import Entite.TypeCase;

public class ControlCase {
    
    private final ControlCaseBombe controlBombe = new ControlCaseBombe();
    private final ControlCasePoison controlPoison= new ControlCasePoison();
    private final ControlCaseSoin controlSoin = new ControlCaseSoin();
    private final ControlCaseEchange controlEchange = new ControlCaseEchange();
    
    //Lenny

    public String selectCase (TypeCase caseType,Pirate pirate, Pirate autrePirate) {
        switch (caseType) {
        case NORMAL: {
            return pirate.getNom() + " a atteint une case sans effet";
        }
        case BOMBE: {
            return controlBombe.appliquerEffet(pirate,null);
        }
        case EMPOISONNEMENT:{
            return controlPoison.appliquerEffet(pirate,null);
        }
        case SOIN:{
            return controlSoin.appliquerEffet(pirate,null);
        }
        case ECHANGE:{
            return controlEchange.appliquerEffet(pirate, autrePirate);
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
