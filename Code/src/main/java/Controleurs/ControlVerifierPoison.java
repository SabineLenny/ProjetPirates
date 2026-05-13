package Controleurs;

import Entite.Pirate;

public class ControlVerifierPoison {
    
    public String verificationPoison(Pirate pirate){
        if(pirate.getDureeEmpoisonnement()>0){
            pirate.prendreDegats(1);
            pirate.setDureeEmpoisonnement(pirate.getDureeEmpoisonnement()-1);
            return "Le pirate " + pirate.getNom() + "prend 1 degat de poison.\n";
        }
        return "Le pirate" + pirate.getNom() + "n'est pas empoisonne, il ne prend pas de degats.\n";
    }
}         
