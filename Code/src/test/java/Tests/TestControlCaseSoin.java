package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlCaseSoin;
import Entite.Pirate;

class ControlCaseSoinTest {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testAppliquerEffetSoigne() {
        pirate.setVie(2);
        ControlCaseSoin.appliquerEffet(pirate);
        assertEquals(3, pirate.getVie());
    }
    
    @Test
    void testAppliquerEffetSoinSupprimeEmpoisonnement() {
        pirate.setDuréeEmpoisonnement(3);
        ControlCaseSoin.appliquerEffet(pirate);
        assertEquals(0, pirate.getDuréeEmpoisonnement());
    }
    
    @Test
    void testAppliquerEffetSoinVieMax() {
        pirate.setVie(5);
        ControlCaseSoin.appliquerEffet(pirate);
        assertEquals(5, pirate.getVie());
    }
}
