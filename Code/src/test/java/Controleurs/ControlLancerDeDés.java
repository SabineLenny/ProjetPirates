package Controleurs;

import java.util.Random;

public class ControlLancerDeDés {
	//Elouan
	public static int[] lancerDés(int nbDés) {
		Random random=new Random();
		int[] result=new int[nbDés];
		for(int i=0;i<nbDés;i++) {
			result[i]=random.nextInt(1,7);
		}
		return result;
	}
	//

        public static String affichageDés (int[] resDés) {
            String resultat ="Resultat du tirage de des : ";
            int totalDés = 0;
            for (int i = 0; i < resDés.length; i++) {
                resultat += "\nde " + (i+1) + " : " + resDés[i];
                totalDés += resDés[i];
            }
            return resultat + "\ndistance : " + totalDés;
        }
}
