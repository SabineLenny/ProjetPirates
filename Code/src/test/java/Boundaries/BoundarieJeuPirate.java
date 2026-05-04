package Boundaries;

import Controleurs.ControlDéplacer;
import Controleurs.ControleurPirate;
import Controleurs.ControlLancerDeDés;
import Entite.Pirate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class BoundarieJeuPirate {
    //Ulysse
    public static void main (String[] args) {
        
        //Initialiser le jeu
        boolean run = true;
        int indicePirate = 1;
        Map<Integer,Pirate> mapPirate = new TreeMap<>();
        mapPirate.put(0, ControleurPirate.creationPirate("Pirate 1"));
        mapPirate.put(1, ControleurPirate.creationPirate("Pirate 2"));
        Scanner s = new Scanner(System.in);
        
        //Lancer le jeu
        while (run) {
            
            //Recevoir une action de l'utilisateur
            System.out.println("Entrez votre action");
            String action = s.next();
            if (action.equals("deplacer")) {
                System.out.println("De combien de case faut-il se deplacer ?");
                int distance = s.nextInt();
                String actionLigne = ControlDéplacer.deplacer(mapPirate.get(indicePirate), distance);
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
            } else {
                System.out.println("e");
            }
            
        }
    }
    //Ulysse
}
