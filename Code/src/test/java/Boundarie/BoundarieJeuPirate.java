package Boundaries;

import Controleurs.ControlDéplacer;
import Controleurs.ControleurPirate;
import Entite.Pirate;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class BoundarieJeuPirate {
    /*
        Initialiser le jeu
        Lancer le jeu
        Recevoir une action de l'utilisateur
        L'envoyer à un controleur
        Recevoir le retour du controleur
        Traiter l'action (changement des variables dans le boundarie)
        Afficher les actions
    */
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
            } else {
                System.out.println("e");
            }
            
        }
    }
    //Ulysse
}
