package Controleurs;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlVerifierVie {
    
    private Pirate pirate;
    private ControlVerifierVie controlVerifierVie;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testVerifierViePirateVivant() {
        pirate.setVie(3);
        boolean resultat = controlVerifierVie.verifierVie(pirate);
        assertTrue(resultat);
    }
    
    @Test
    void testVerifierViePirate2Mort() {
        pirate.setVie(0);
        boolean resultat = controlVerifierVie.verifierVie(pirate);
        assertFalse(resultat);
    }
    
    @Test
    void testVerifierViePirate1VieNegative() {
        pirate.setVie(-5);
        boolean resultat = controlVerifierVie.verifierVie(pirate);
        assertFalse(resultat);
    }
}
