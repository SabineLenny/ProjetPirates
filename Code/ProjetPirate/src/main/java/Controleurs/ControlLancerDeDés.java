package Controleurs;

import java.util.Random;

public class ControlLancerDeDés {

	public static int[] lancerDés(int nbDés) {
		Random random=new Random();
		int[] result=new int[nbDés];
		for(int i=0;i<nbDés;i++) {
			result[i]=random.nextInt(1,7);
		}
		return result;
		
	}
}
