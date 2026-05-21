package Controleurs;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlCaseBombe {
    
    private Pirate pirate;
    private ControlCaseBombe controlCaseBombe;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
        controlCaseBombe=new ControlCaseBombe();
    }
    
    @Test
    void testAppliquerEffetBombe() {
        int vieInitiale = pirate.getVie();
        controlCaseBombe.appliquerEffet(pirate, null);
        assertTrue(pirate.getVie() < vieInitiale);
    }
}
