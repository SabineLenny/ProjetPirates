package Tests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlCaseBombe;
import Entite.Pirate;

class ControlCaseBombeTest {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testAppliquerEffetBombe() {
        int vieInitiale = pirate.getVie();
        ControlCaseBombe.appliquerEffet(pirate);
        assertTrue(pirate.getVie() < vieInitiale);
    }
}
