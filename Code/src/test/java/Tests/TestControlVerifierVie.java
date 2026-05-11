package Tests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlVerifierVie;
import Entite.Pirate;

class ControlVerifierVieTest {
    
    private Pirate pirate1;
    private Pirate pirate2;
    
    @BeforeEach
    void setUp() {
        pirate1 = new Pirate("Pirate1");
        pirate2 = new Pirate("Pirate2");
    }
    
    @Test
    void testVerifierViePirate1Mort() {
        pirate1.setVie(0);
        pirate2.setVie(3);
        String resultat = ControlVerifierVie.verifierVie(pirate1, pirate2);
        assertTrue(resultat.contains("Pirate1"));
        assertTrue(resultat.contains("mort"));
        assertTrue(resultat.contains("Pirate2"));
        assertTrue(resultat.contains("gagné"));
    }
    
    @Test
    void testVerifierViePirate2Mort() {
        pirate1.setVie(3);
        pirate2.setVie(0);
        String resultat = ControlVerifierVie.verifierVie(pirate1, pirate2);
        assertTrue(resultat.contains("Pirate2"));
        assertTrue(resultat.contains("mort"));
        assertTrue(resultat.contains("Pirate1"));
        assertTrue(resultat.contains("gagné"));
    }
    
    @Test
    void testVerifierVieAucunMort() {
        pirate1.setVie(3);
        pirate2.setVie(2);
        String resultat = ControlVerifierVie.verifierVie(pirate1, pirate2);
        assertTrue(resultat.contains("Personne"));
        assertTrue(resultat.contains("mort"));
    }
    
    @Test
    void testVerifierViePirate1VieNegative() {
        pirate1.setVie(-5);
        pirate2.setVie(3);
        String resultat = ControlVerifierVie.verifierVie(pirate1, pirate2);
        assertTrue(resultat.contains("Pirate1"));
        assertTrue(resultat.contains("mort"));
    }
}
