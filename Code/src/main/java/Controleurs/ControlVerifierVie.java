package Controleurs;

import Entite.Pirate;

public class ControlVerifierVie {
    public static String verifierVie(Pirate pirate1,Pirate pirate2){
         if(pirate1.getVie()<=0){
            return pirate1.getNom()+"est mort !\n" + pirate2.getNom() + " a gagne !\n";
          }
          else if(pirate2.getVie()<=0){
            return pirate2.getNom()+"est mort !\n" + pirate1.getNom() + " a gagne !\n";
          }
          else{
              return "Personne n'est encore mort.\n";
          }
    }
}
