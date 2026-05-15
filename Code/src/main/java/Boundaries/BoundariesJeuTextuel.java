package Boundaries;

import Controleurs.ControlDeplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import java.util.Scanner;

public class BoundariesJeuTextuel {
    
    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in); 
        boolean run = true;
        boolean piratesEnVie = true;
        
        final ControlJeuPirate CONTROL_JEU_PIRATE = new ControlJeuPirate();
        final ControlVerifierVie CONTROL_VIE = new ControlVerifierVie();
        final ControlVerifierPoison CONTROL_POISON = new ControlVerifierPoison();
        final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
        final ControlDeplacer CONTROL_DEPLACER = new ControlDeplacer();
        final ControlFinJeu CONTROL_FIN = new ControlFinJeu();
        
        CONTROL_JEU_PIRATE.instancierJeu();
        String deplacement;
        String effetCase;
                
        while (run && piratesEnVie) {
            
            String poison = CONTROL_POISON.verificationPoison(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            System.out.println(poison);
            
            deplacement = CONTROL_DEPLACER.deplacer(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            System.out.println(deplacement);
            
            effetCase = CONTROL_PLATEAU.activerCase(CONTROL_JEU_PIRATE.getPlateau(),CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()),CONTROL_JEU_PIRATE.getMapPirate().get((CONTROL_JEU_PIRATE.getIndicePirate()+1)%2));
            System.out.println(effetCase);
            
            CONTROL_JEU_PIRATE.incrementIndicePirate();
            s.next();
            run = CONTROL_FIN.finJeu(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            if (run == false) {
                System.out.println("Victoire de " + CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()).getNom());
            }
            
            piratesEnVie = CONTROL_VIE.verifierVie(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            if (piratesEnVie == false) {
                System.out.println(CONTROL_VIE.verifierVie(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate())));
            }
        }
    }
}
