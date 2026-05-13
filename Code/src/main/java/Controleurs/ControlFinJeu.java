package Controleurs;

import Entite.Pirate;

public class ControlFinJeu {
    
    public Boolean finJeu(Pirate pirate){
        return !(pirate.getPosition()==29);
    }
}