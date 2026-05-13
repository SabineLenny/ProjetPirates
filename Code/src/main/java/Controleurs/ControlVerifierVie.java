package Controleurs;

import Entite.Pirate;

public class ControlVerifierVie {
    
    public static String affichageVie(Pirate pirate){
         if(pirate.getVie()<=0){
            return pirate.getNom()+"est mort !\n";
          }
          else{
              return "Personne n'est encore mort.\n";
          }
    }
    
    public static boolean VerifierVie(Pirate pirate) {
        return pirate.getVie()<=0;
    }
    
}
