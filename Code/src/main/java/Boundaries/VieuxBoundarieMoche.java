package Boundaries;

import Controleurs.ControlCaseArrivee;
import Controleurs.ControlCaseBombe;
import Controleurs.ControlCaseEchange;
import Controleurs.ControlCasePoison;
import Controleurs.ControlCaseSoin;
import Controleurs.ControlDéplacer;
import Controleurs.ControlJeuPirate;
import Controleurs.ControlLancerDeDés;
import java.util.Scanner;


public class VieuxBoundarieMoche {
    //Ulysse
    public static void main (String[] args) {
        
        Scanner s = new Scanner(System.in);
        ControlJeuPirate cjp = new ControlJeuPirate();
        boolean run = true;
        cjp.instancierJeu();
        
        while (run) {
            System.out.println("Entrez votre action, \nvie : " + cjp.getMapPirate().get(cjp.getIndicePirate()).getVie() +
                    "\nposition : " + cjp.getMapPirate().get(cjp.getIndicePirate()).getPosition() +
                    "\nduree empoisonnement : " + cjp.getMapPirate().get(cjp.getIndicePirate()).getDuréeEmpoisonnement() + "\n");
            String action = s.next();
            if (action.equals("deplacer")) {
                System.out.println("Tirage des ?");
                String actionLigne = ControlDéplacer.deplacer(cjp.getMapPirate().get(cjp.getIndicePirate()));
                System.out.println(actionLigne);
                System.out.println("Position : " + cjp.getMapPirate().get(cjp.getIndicePirate()).getPosition());
            } else if (action.equals("lancer1de")) {
                System.out.println("Lancer le de");
                int[] dés= ControlLancerDeDés.lancerDés(1);
                String actionLigne = ControlLancerDeDés.affichageDés(dés);
                System.out.println(actionLigne);
            } else if (action.equals("lancer2de")) {
                System.out.println("Lancer le de");
                int[] dés= ControlLancerDeDés.lancerDés(2);
                String actionLigne = ControlLancerDeDés.affichageDés(dés);
                System.out.println(actionLigne);
            } else if (action.equals("bombe")) {
                System.out.println("bombe");
                String actionLigne = ControlCaseBombe.appliquerEffet(cjp.getMapPirate().get(cjp.getIndicePirate()));
                System.out.println(actionLigne);
            } else if (action.equals("poison")) {
                System.out.println("poison");
                String actionLigne = ControlCasePoison.appliquerEffet(cjp.getMapPirate().get(cjp.getIndicePirate()));
                System.out.println(actionLigne);
            }
            else if (action.equals("soin")) {
                System.out.println("soin");
                String actionLigne = ControlCaseSoin.appliquerEffet(cjp.getMapPirate().get(cjp.getIndicePirate()));
                System.out.println(actionLigne);
            }
            else if (action.equals("echange")) {
                System.out.println("echange");
                String actionLigne = ControlCaseEchange.appliquerEffet(cjp.getMapPirate().get(cjp.getIndicePirate()),cjp.getMapPirate().get((cjp.getIndicePirate() + 1)%2));
                System.out.println(actionLigne);
            }
            else if (action.equals("arrivee")) {
                System.out.println("arrivee");
                String actionLigne = ControlCaseArrivee.appliquerEffet(cjp.getMapPirate().get(cjp.getIndicePirate()));
                System.out.println(actionLigne);
            } else {
                System.out.println("e");
            }
            cjp.incrementIndicePirate();
        }
    }
    //Ulysse
}
