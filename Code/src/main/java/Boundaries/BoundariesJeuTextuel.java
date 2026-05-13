package Boundaries;

import Controleurs.ControlDéplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import Controleurs.ControlVerifierPoison;
import Controleurs.ControlVerifierVie;
import java.util.Scanner;

public class BoundariesJeuTextuel {
    
    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in);
        ControlJeuPirate cjp = new ControlJeuPirate();
        boolean run = true;
        cjp.instancierJeu();
        String deplacement;
        String effetCase;
        
        while (run) {
            
            String poison = ControlVerifierPoison.vérificationPoison(cjp.getMapPirate().get(cjp.getIndicePirate()));
            System.out.println(poison);
            
            ControlDéplacer.deplacer(cjp.getMapPirate().get(cjp.getIndicePirate()));
            System.out.println(cjp.getMapPirate().get(cjp.getIndicePirate()).getPosition());
            
            deplacement = ControlDéplacer.deplacer(cjp.getMapPirate().get(cjp.getIndicePirate()));
            System.out.println(deplacement);
            
            effetCase = ControlPlateau.activerCase(cjp.getPlateau(),cjp.getMapPirate().get(cjp.getIndicePirate()),cjp.getMapPirate().get((cjp.getIndicePirate()+1)%2));
            System.out.println(effetCase);
            
            cjp.incrementIndicePirate();
            s.next();
            run = ControlFinJeu.finJeu(cjp.getMapPirate().get(cjp.getIndicePirate()));
            if (run == false) {
                System.out.println("Victoire de " + cjp.getMapPirate().get(cjp.getIndicePirate()).getNom());
            }
            
            run = ControlVerifierVie.VerifierVie(cjp.getMapPirate().get(cjp.getIndicePirate()));
            if (run == false) {
                System.out.println(ControlVerifierVie.VerifierVie(cjp.getMapPirate().get(cjp.getIndicePirate())));
            }
        }
    }
}
