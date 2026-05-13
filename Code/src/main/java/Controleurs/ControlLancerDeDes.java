package Controleurs;

import java.util.Random;

public class ControlLancerDeDes {
	//Elouan
	public int[] lancerDes(int nbDes) {
		Random random=new Random();
		int[] result=new int[nbDes];
		for(int i=0;i<nbDes;i++) {
			result[i]=random.nextInt(1,7);
		}
		return result;
	}
	//

        public String affichageDes (int[] resDes) {
            String resultat ="Resultat du tirage de des : ";
            int totalDés = 0;
            for (int i = 0; i < resDes.length; i++) {
                resultat += "\nde " + (i+1) + " : " + resDes[i];
                totalDés += resDes[i];
            }
            return resultat + "\ndistance : " + totalDés;
        }
        
        public int additionDes (int[] resDes) {
            int totalDes = 0;
            for (int i = 0; i < resDes.length; i++) {
                totalDes += resDes[i];
            }
            return totalDes;
        }
}
