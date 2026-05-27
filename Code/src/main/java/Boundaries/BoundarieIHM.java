package Boundaries;

import Controleurs.ControlDeplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import java.util.Map;

public class BoundarieIHM extends BoundariesJeuTextuel{

    static final ControlJeuPirate CONTROL_JEU_PIRATE = new ControlJeuPirate();
    static final ControlVerifierVie CONTROL_VIE = new ControlVerifierVie();
    static final ControlVerifierPoison CONTROL_POISON = new ControlVerifierPoison();
    static final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
    static final ControlDeplacer CONTROL_DEPLACER = new ControlDeplacer();
    static final ControlFinJeu CONTROL_FIN = new ControlFinJeu();
    
    public ControlJeuPirate getControlJeuPirate () {
        return CONTROL_JEU_PIRATE;
    }
    public ControlVerifierVie getControlVerifierVie () {
        return CONTROL_VIE;
    }
    public ControlVerifierPoison getControlVerifierPoison () {
        return CONTROL_POISON;
    }
    public ControlPlateau getControlPlateau () {
        return CONTROL_PLATEAU;
    }
    public ControlDeplacer getControlDeplacer () {
        return CONTROL_DEPLACER;
    }
    public ControlFinJeu getControlFinJeu () {
        return CONTROL_FIN;
    }
    
    public int lancerDesTour () {
        return CONTROL_DEPLACER.getControlDes().lancerDes(1)[0];
    }
    
    public int getPositionCaseAvecString (String nom) {
        return CONTROL_PLATEAU.positionCaseAvecString(CONTROL_JEU_PIRATE.getPlateau(), nom);
    }
    
}
