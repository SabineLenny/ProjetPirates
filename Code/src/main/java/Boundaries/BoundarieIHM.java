package Boundaries;

import Controleurs.ControlDeplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import java.util.Map;

public class BoundarieIHM extends BoundariesJeuTextuel{

    //Ulysse
    static final ControlJeuPirate CONTROL_JEU_PIRATE = new ControlJeuPirate();
    static final ControlVerifierVie CONTROL_VIE = new ControlVerifierVie();
    static final ControlVerifierPoison CONTROL_POISON = new ControlVerifierPoison();
    static final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
    static final ControlDeplacer CONTROL_DEPLACER = new ControlDeplacer();
    static final ControlFinJeu CONTROL_FIN = new ControlFinJeu();
    
    public int lancerDesTour () {
        return CONTROL_DEPLACER.getControlDes().lancerDes(1)[0];
    }
    
    public int getPositionCaseAvecString (String nom) {
        return CONTROL_PLATEAU.positionCaseAvecString(CONTROL_JEU_PIRATE.getPlateau(), nom);
    }
    
    public void instancierJeu(String pirate1, String pirate2) {
        CONTROL_JEU_PIRATE.instancierJeu(pirate1, pirate2);
    }
    
    public int[] lancerDes () {
        return CONTROL_DEPLACER.lancerDes();
    }
    
    public int deplacementPirate (Integer pirate, int[] lancer) {
        return CONTROL_DEPLACER.deplacer(CONTROL_JEU_PIRATE.getMapPirate().get(pirate),lancer);
    }
    
    public String deplacementPirateAffichage (Integer pirate, int distance) {
        return CONTROL_DEPLACER.affichageDeplacement(CONTROL_JEU_PIRATE.getMapPirate().get(pirate), distance);
    }
    
    public String affichagePlateau () {
        return CONTROL_PLATEAU.affichagePlateau(CONTROL_JEU_PIRATE.getPlateau());
    }
    
    public String verificationPoison(Integer pirate) {
        return CONTROL_POISON.verificationPoison(CONTROL_JEU_PIRATE.getMapPirate().get(pirate));
    }
    
    public boolean finJeu (Integer pirate) {
        return CONTROL_FIN.finJeu(CONTROL_JEU_PIRATE.getMapPirate().get(pirate));
    }
    
    public boolean verificationVie (Integer pirate) {
        return CONTROL_VIE.verifierVie(CONTROL_JEU_PIRATE.getMapPirate().get(pirate));
    }
    
    public String affichageVie (Integer pirate) {
        return CONTROL_VIE.affichageVie(CONTROL_JEU_PIRATE.getMapPirate().get(pirate));
    }
    
    public String activerCase (Integer pirate1, Integer pirate2) {
        return CONTROL_PLATEAU.activerCase(CONTROL_JEU_PIRATE.getPlateau(),
                CONTROL_JEU_PIRATE.getMapPirate().get(pirate1),
                CONTROL_JEU_PIRATE.getMapPirate().get(pirate2));
    }
    
    public int piratePos (Integer pirate) {
        return CONTROL_JEU_PIRATE.getMapPirate().get(pirate).getPosition();
    }
    
    public boolean estEmpoisonne (Integer pirate) {
        return CONTROL_JEU_PIRATE.getMapPirate().get(pirate).getDureeEmpoisonnement()>0;
    }
    
    public int getPirateVie (Integer pirate) {
        return CONTROL_JEU_PIRATE.getMapPirate().get(pirate).getVie();
    }
    //Ulysse
}
