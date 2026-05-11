package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import Controleurs.ControlLancerDeDés;

class ControlLancerDeDésTest {
    
    @Test
    void testLancerDésUnDe() {
        int[] resultat = ControlLancerDeDés.lancerDés(1);
        assertEquals(1, resultat.length);
        assertTrue(resultat[0] >= 1 && resultat[0] <= 6);
    }
    
    @Test
    void testLancerDésDeuxDes() {
        int[] resultat = ControlLancerDeDés.lancerDés(2);
        assertEquals(2, resultat.length);
        for (int de : resultat) {
            assertTrue(de >= 1 && de <= 6);
        }
    }
    
    @Test
    void testAdditionDés() {
        int[] des = {1, 2, 3};
        int total = ControlLancerDeDés.additionDés(des);
        assertEquals(6, total);
    }
    
    @Test
    void testAdditionDésUnDe() {
        int[] des = {4};
        int total = ControlLancerDeDés.additionDés(des);
        assertEquals(4, total);
    }
}
