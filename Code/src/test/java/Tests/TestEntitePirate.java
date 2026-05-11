package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestEntitePirate {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Barbe Noire");
    }
    
    @Test
    void testConstructeur() {
        assertEquals("Barbe Noire", pirate.getNom());
        assertEquals(5, pirate.getVie());
        assertEquals(0, pirate.getPosition());
        assertEquals(0, pirate.getDuréeEmpoisonnement());
    }
    
    @Test
    void testSoignerVieAugmente() {
        pirate.setVie(3);
        pirate.soigner();
        assertEquals(4, pirate.getVie());
    }
    
    @Test
    void testSoignerVieMaximale() {
        pirate.setVie(5);
        pirate.soigner();
        assertEquals(5, pirate.getVie());
    }
    
    @Test
    void testPrendreDegats() {
        pirate.prendreDegats(2);
        assertEquals(3, pirate.getVie());
    }
    
    @Test
    void testPrendreDegatsNegatif() {
        pirate.prendreDegats(10);
        assertEquals(-5, pirate.getVie());
    }
    
    @Test
    void testSoignerMultiple() {
        pirate.setVie(2);
        pirate.soigner();
        pirate.soigner();
        pirate.soigner();
        assertEquals(5, pirate.getVie());
    }
}