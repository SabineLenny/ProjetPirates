package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlCaseSoin {
    
    private Pirate pirate;
    private ControlCaseSoin controlCaseSoin;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testAppliquerEffetSoigne() {
        pirate.setVie(2);
        controlCaseSoin.appliquerEffet(pirate, null);
        assertEquals(3, pirate.getVie());
    }
    
    @Test
    void testAppliquerEffetSoinSupprimeEmpoisonnement() {
        pirate.setDureeEmpoisonnement(3);
        controlCaseSoin.appliquerEffet(pirate, null);
        assertEquals(0, pirate.getDureeEmpoisonnement());
    }
    
    @Test
    void testAppliquerEffetSoinVieMax() {
        pirate.setVie(5);
        controlCaseSoin.appliquerEffet(pirate, null);
        assertEquals(5, pirate.getVie());
    }
}
