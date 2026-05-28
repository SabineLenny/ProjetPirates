package Boundaries;

import Controleurs.ControlDeplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import java.util.Scanner;

public class BoundariesJeuTextuel {
    
    static final ControlJeuPirate CONTROL_JEU_PIRATE = new ControlJeuPirate();
    static final ControlVerifierVie CONTROL_VIE = new ControlVerifierVie();
    static final ControlVerifierPoison CONTROL_POISON = new ControlVerifierPoison();
    static final ControlPlateau CONTROL_PLATEAU = new ControlPlateau();
    static final ControlDeplacer CONTROL_DEPLACER = new ControlDeplacer();
    static final ControlFinJeu CONTROL_FIN = new ControlFinJeu();
    
    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in); 
        boolean run = true;
        boolean piratesEnVie = true;
        
        System.out.println("Nom du pirate 1 : ");
        String nom1 = s.next();
        System.out.println("Nom du pirate 2 : ");
        String nom2 = s.next();
        
        s.nextLine(); // C'est necessaire pour vider le tampon, sinon la méthode s.nextLine() suivante ne fonctionnera pas

        
        CONTROL_JEU_PIRATE.instancierJeu(nom1,nom2);
        int deplacement;
        String effetCase;
            
        
        while (run && piratesEnVie) {
            
            s.nextLine();

            String poison = CONTROL_POISON.verificationPoison(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            System.out.println(poison);
            
            int [] lancer = CONTROL_DEPLACER.lancerDes();
            deplacement = CONTROL_DEPLACER.deplacer(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()),lancer);
            System.out.println(CONTROL_DEPLACER.affichageDeplacement(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()), deplacement));
            
                          
            effetCase = CONTROL_PLATEAU.activerCase(CONTROL_JEU_PIRATE.getPlateau(),CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()),CONTROL_JEU_PIRATE.getMapPirate().get((CONTROL_JEU_PIRATE.getIndicePirate()+1)%2));
            System.out.println(effetCase);
            
            System.out.println("position : " + CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()).getPosition());
            System.out.println("pv : " + CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()).getVie());


            System.out.println("\n");
            
            

            run = CONTROL_FIN.finJeu(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            if (run == false) {
                System.out.println("Victoire de " + CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()).getNom());
            }
            
            piratesEnVie = CONTROL_VIE.verifierVie(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate()));
            if (piratesEnVie == false) {
                System.out.println(CONTROL_VIE.affichageVie(CONTROL_JEU_PIRATE.getMapPirate().get(CONTROL_JEU_PIRATE.getIndicePirate())));
            }
            
            CONTROL_JEU_PIRATE.incrementIndicePirate();
            



        }
    }
}
