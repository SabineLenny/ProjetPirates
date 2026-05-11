package Boundaries;

import Controleurs.ControlCaseArrivee;
import Controleurs.ControlCaseBombe;
import Controleurs.ControlCaseEchange;
import Controleurs.ControlCasePoison;
import Controleurs.ControlCaseSoin;
import Controleurs.ControlDéplacer;
import Controleurs.ControlPirate;
import Controleurs.ControlLancerDeDés;
import Controleurs.ControlPlateau;
import Entite.Pirate;
import Entite.Plateau;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class BoundarieJeuPirate {
    //Ulysse
    public static void main (String[] args) {
        
        //Initialiser le jeu
        boolean run = true;
        int indicePirate = 0;
        Map<Integer,Pirate> mapPirate = new TreeMap<>();
        mapPirate.put(0, ControlPirate.creationPirate("Pirate 1"));
        mapPirate.put(1, ControlPirate.creationPirate("Pirate 2"));
        Scanner s = new Scanner(System.in);
        Plateau p = ControlPlateau.creationPlateau();
        System.out.println(ControlPlateau.affichagePlateau(p));
        
        //Lancer le jeu
        while (run) {
            
            //Recevoir une action de l'utilisateur
            System.out.println("Entrez votre action, \nvie : " + mapPirate.get(indicePirate).getVie() +
                    "\nposition : " + mapPirate.get(indicePirate).getPosition() +
                    "\nduree empoisonnement : " + mapPirate.get(indicePirate).getDuréeEmpoisonnement() + "\n");
            String action = s.next();
            if (action.equals("deplacer")) {
                System.out.println("Tirage des ?");
                String actionLigne = ControlDéplacer.deplacer(mapPirate.get(indicePirate));
                System.out.println(actionLigne);
                System.out.println("Position : " + mapPirate.get(indicePirate).getPosition());
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
                String actionLigne = ControlCaseBombe.appliquerEffet(mapPirate.get(indicePirate));
                System.out.println(actionLigne);
            } else if (action.equals("poison")) {
                System.out.println("poison");
                String actionLigne = ControlCasePoison.appliquerEffet(mapPirate.get(indicePirate));
                System.out.println(actionLigne);
            }
            else if (action.equals("soin")) {
                System.out.println("soin");
                String actionLigne = ControlCaseSoin.appliquerEffet(mapPirate.get(indicePirate));
                System.out.println(actionLigne);
            }
            else if (action.equals("echange")) {
                System.out.println("echange");
                String actionLigne = ControlCaseEchange.appliquerEffet(mapPirate.get(indicePirate),mapPirate.get((indicePirate + 1)%2));
                System.out.println(actionLigne);
            }
            else if (action.equals("arrivee")) {
                System.out.println("arrivee");
                String actionLigne = ControlCaseArrivee.appliquerEffet(mapPirate.get(indicePirate));
                System.out.println(actionLigne);
            } else {
                System.out.println("e");
            }
            indicePirate = (indicePirate + 1) % 2;
        }
    }
    //Ulysse
}
