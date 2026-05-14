package Controleurs;

import Entite.Pirate;
import Entite.Plateau;
import Entite.TypeCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Stream;


public class ControlPlateau {
    
    private final ControlCase CONTROL_CASE = new ControlCase();
    
    public Plateau creationPlateau () {
        Map<Integer,TypeCase> plateau = new TreeMap<>();
        Stream.iterate(1, i -> i+1).limit(29).forEach(i -> plateau.put(i,TypeCase.NORMAL));
        Plateau plateauAleatoire = new Plateau(plateau);
        distributionCaseSpecial(plateauAleatoire);
        return plateauAleatoire;
    }
    
    public String affichagePlateau (Plateau plateau) {
        StringBuilder affichage = new StringBuilder("Plateau \n");
        Stream.iterate(1, i -> i+1).limit(plateau.getPlateau().size()-1)
                .forEach(i -> affichage.append(plateau.getPlateau().get(i)).append(" ").append(i).append("\n"));
        return affichage.toString();
    }
    
    public void distributionCaseSpecial (Plateau plateau) {
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
    
    public String activerCase (Plateau plateau, Pirate p1, Pirate p2) {
        return CONTROL_CASE.selectCase(plateau.getPlateau().get(p1.getPosition()), p1, p2);
    }
}
