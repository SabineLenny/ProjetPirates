package Controleurs;

import Entite.Pirate;

public class ControlVerifierPoison {
    public static String vérificationPoison(Pirate pirate){
        if(pirate.getDuréeEmpoisonnement()>0){
            pirate.prendreDegats(1);
            pirate.setDuréeEmpoisonnement(pirate.getDuréeEmpoisonnement()-1);
            return "Le pirate " + pirate.getNom() + "prend 1 dégat de poison.\n";
        }
        return "Le pirate" + pirate.getNom() + "n'est pas empoisonné, il ne prend pas de dégats.\n";
    }
}         
