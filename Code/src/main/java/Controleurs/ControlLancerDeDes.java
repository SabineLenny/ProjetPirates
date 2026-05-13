package Controleurs;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ControlLancerDeDes {
	//Elouan
	public int[] lancerDes(int nbDes) {
		Random random=new Random();
		int[] result=new int[nbDes];
                Stream.iterate(0,(i) -> i+1).limit(nbDes).forEach((i) -> result[i] = random.nextInt(1,7));
		return result;
	}
	//

        public String affichageDes (int[] resDes) {
            final StringBuilder resultat = new StringBuilder("Resultat du tirage de des : ");
            int totalDes = IntStream.range(0, resDes.length)
                    .map((i) -> {
                        resultat.append("\nde ").append(i+1).append(" : ").append(resDes[i]);
                        return resDes[i];
                            })
                    .sum();
            return resultat.append("\ndistance : ").append(totalDes).toString();
        }
        
        public int additionDes (int[] resDes) {
            int totalDes = IntStream.range(0, resDes.length).map((i) -> resDes[i]).sum();
            return totalDes;
        }
}
