package Controleurs;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlCasePoison {
    
    private Pirate pirate;
    private ControlCasePoison controlCasePoison;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testAppliquerEffetPoisonDefinitDuree() {
        controlCasePoison.appliquerEffet(pirate, null);
        assertTrue(pirate.getDureeEmpoisonnement() > 0);
        assertTrue(pirate.getDureeEmpoisonnement() <= 6);
    }
}
