package Controleurs;

import Entite.Case;
import Entite.Plateau;
import Entite.TypeCase;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class ControleurPlateau {
    
    public Plateau creationPlateau () {
        Map<Integer,TypeCase> m = new TreeMap<>();
        Random random=new Random();
        for (int i = 0; i < 30; i++) {
            m.put(i, TypeCase.NORMAL);
        }
    }
}
