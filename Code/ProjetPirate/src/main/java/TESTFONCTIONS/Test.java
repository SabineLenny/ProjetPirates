package TESTFONCTIONS;

import Controleurs.ControlDéplacer;
import Controleurs.ControlLancerDeDés;
import Controleurs.ControleurPirate;
import Entite.Pirate;

public class Test {
	
	public static void main(String[] args) {
		//Elouan
		Pirate pirate=ControleurPirate.creationPirate();
		System.out.println("Pirate : \n -Vie : " + pirate.getVie() + "\n -Position : " + pirate.getPosition() + "\n -empoisonnement : " +pirate.getDuréeEmpoisonnement());
		System.out.println("\nLancé de 2 Dés : \n");
		int[] dés=ControlLancerDeDés.lancerDés(2);
		System.out.println("- " + dés[0] + "\n - " + dés[1] + "\n");
		ControlDéplacer.deplacer(pirate, dés[0]+dés[1]);
		System.out.println("Déplacement de somme des deux dés.\n" + "Nouvelle Position : " + pirate.getPosition());
		//

	}

}
