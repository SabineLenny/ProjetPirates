package Tests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlCasePoison;
import Entite.Pirate;

class ControlCasePoisonTest {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testAppliquerEffetPoisonDefinitDuree() {
        ControlCasePoison.appliquerEffet(pirate);
        assertTrue(pirate.getDuréeEmpoisonnement() > 0);
        assertTrue(pirate.getDuréeEmpoisonnement() <= 6);
    }
}
