package Controleurs;

import Entite.Pirate;

public class ControlVerifierPoison {
    
    public String vérificationPoison(Pirate pirate){
        if(pirate.getDuréeEmpoisonnement()>0){
            pirate.prendreDegats(1);
            pirate.setDuréeEmpoisonnement(pirate.getDuréeEmpoisonnement()-1);
            return "Le pirate " + pirate.getNom() + "prend 1 degat de poison.\n";
        }
        return "Le pirate" + pirate.getNom() + "n'est pas empoisonne, il ne prend pas de degats.\n";
    }
}         
