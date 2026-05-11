package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlVerifierPoison;
import Entite.Pirate;

class ControlVerifierPoisonTest {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testVerificationPoisonPiratEmpoisonne() {
        pirate.setDuréeEmpoisonnement(3);
        pirate.setVie(5);
        ControlVerifierPoison.vérificationPoison(pirate);
        assertEquals(4, pirate.getVie());
        assertEquals(2, pirate.getDuréeEmpoisonnement());
    } 
    
    @Test
    void testVerificationPoisonPiratNonEmpoisonne() {
        pirate.setDuréeEmpoisonnement(0);
        pirate.setVie(5);
        ControlVerifierPoison.vérificationPoison(pirate);
        assertEquals(5, pirate.getVie());
    }
    
    @Test
    void testVerificationPoisonDureeDiminue() {
        pirate.setDuréeEmpoisonnement(5);
        ControlVerifierPoison.vérificationPoison(pirate);
        assertEquals(4, pirate.getDuréeEmpoisonnement());
    }
}
