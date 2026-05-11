package Boundaries;

import Controleurs.ControlDéplacer;
import Controleurs.ControlFinJeu;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlPlateau;
import java.util.Scanner;

public class BoundariesJeuTextuel {
    
    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in);
        ControlJeuPirate cjp = new ControlJeuPirate();
        boolean run = true;
        cjp.instancierJeu();
        
        while (run) {
            ControlDéplacer.deplacer(cjp.getMapPirate().get(cjp.getIndicePirate()));
            System.out.println(cjp.getMapPirate().get(cjp.getIndicePirate()).getPosition());
            ControlPlateau.activerCase(cjp.getPlateau(),cjp.getMapPirate().get(cjp.getIndicePirate()),cjp.getMapPirate().get((cjp.getIndicePirate()+1)%2));
            cjp.incrementIndicePirate();
            s.next();
            run = ControlFinJeu.finJeu(cjp.getMapPirate().get(cjp.getIndicePirate()));
            if (run == false) {
                System.out.println("Victoire de " + cjp.getMapPirate().get(cjp.getIndicePirate()).getNom());
            }
        }
    }
}
