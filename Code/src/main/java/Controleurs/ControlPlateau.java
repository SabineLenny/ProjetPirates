package Controleurs;

import Entite.Pirate;
import Entite.Plateau;
import Entite.TypeCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class ControlPlateau {
    
    public static Plateau creationPlateau () {
        Map<Integer,TypeCase> plateau = new TreeMap<>();
        for (int i = 0; i < 29; i++) {
            plateau.put(i, TypeCase.NORMAL);
        }
        Plateau plateauAleatoire = new Plateau(plateau);
        distributionCaseSpecial(plateauAleatoire);
        return plateauAleatoire;
    }
    
    public static String affichagePlateau (Plateau plateau) {
        String affichage = "Plateau \n";
        for (int i = 0; i < plateau.getPlateau().size(); i++) {
            affichage += plateau.getPlateau().get(i) + " " + (i+1) + "\n";
        }
        return affichage;
    }
    
    public static void distributionCaseSpecial (Plateau plateau) {
        List<Integer> dejaDistribue = new ArrayList<>();
        List<TypeCase> listeCase = new ArrayList<>();
        Random random=new Random();
        int caseSelection;
        
        listeCase.add(TypeCase.BOMBE);
        listeCase.add(TypeCase.ECHANGE);
        listeCase.add(TypeCase.ECHANGE);
        listeCase.add(TypeCase.EMPOISONNEMENT);
        listeCase.add(TypeCase.SOIN);
        listeCase.add(TypeCase.SOIN);
        
        plateau.getPlateau().put(0, TypeCase.DEPART);
        plateau.getPlateau().put(plateau.getPlateau().size(), TypeCase.ARRIVEE);
        
        for (TypeCase caseType : listeCase) {
            do {
                caseSelection = random.nextInt(1,plateau.getPlateau().size()-1);
            } while (dejaDistribue.contains(caseSelection));
            
            dejaDistribue.add(caseSelection);
            
            plateau.getPlateau().put(caseSelection, caseType);
        }
    }
    
    public static String activerCase (Plateau plateau, Pirate p1, Pirate p2) {
        TypeCase tc = plateau.getPlateau().get(p1.getPosition());
        System.out.println(tc);
        return ControlCase.selectCase(tc, p1, p2);
    }
}
