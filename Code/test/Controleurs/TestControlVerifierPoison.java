package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlVerifierPoison {
    
    private Pirate pirate;
    private ControlVerifierPoison controlVerifierPoison;
    
    @BeforeEach
    void setUp() {
    	controlVerifierPoison=new ControlVerifierPoison();
        pirate = new Pirate("Pirate");
    }
    
    @Test
    void testVerificationPoisonPiratEmpoisonne() {
        pirate.setDureeEmpoisonnement(3);
        pirate.setVie(5);
        controlVerifierPoison.verificationPoison(pirate);
        assertEquals(4, pirate.getVie());
        assertEquals(2, pirate.getDureeEmpoisonnement());
    } 
    
    @Test
    void testVerificationPoisonPiratNonEmpoisonne() {
        pirate.setDureeEmpoisonnement(0);
        pirate.setVie(5);
        controlVerifierPoison.verificationPoison(pirate);
        assertEquals(5, pirate.getVie());
    }
    
    @Test
    void testVerificationPoisonDureeDiminue() {
        pirate.setDureeEmpoisonnement(5);
        controlVerifierPoison.verificationPoison(pirate);
        assertEquals(4, pirate.getDureeEmpoisonnement());
    }
}
