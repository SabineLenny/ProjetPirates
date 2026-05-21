package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;


class TestControlLancerDeDes {

    private ControlLancerDeDes controlLancerDeDes;
    
    @BeforeEach
    void setUp() {
        controlLancerDeDes=new ControlLancerDeDes();
    }
    @Test
    void testLancerDesUnDe() {
        int[] resultat = controlLancerDeDes.lancerDes(1);
        assertEquals(1, resultat.length);
        assertTrue(resultat[0] >= 1 && resultat[0] <= 6);
    }
    
    @Test
    void testLancerDesDeuxDes() {
        int[] resultat = controlLancerDeDes.lancerDes(2);
        assertEquals(2, resultat.length);
        for (int de : resultat) {
            assertTrue(de >= 1 && de <= 6);
        }
    }
    
    @Test
    void testAdditionDes() {
        int[] des = {1, 2, 3};
        int total = controlLancerDeDes.additionDes(des);
        assertEquals(6, total);
    }
    
    @Test
    void testAdditionDesUnDe() {
        int[] des = {4};
        int total = controlLancerDeDes.additionDes(des);
        assertEquals(4, total);
    }
}
