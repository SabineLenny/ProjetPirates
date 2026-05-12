package Controleurs;

import Entite.Pirate;

public class ControlFinJeu {
    
    public static Boolean finJeu(Pirate pirate){
        return !(pirate.getPosition()==29);
    }
}