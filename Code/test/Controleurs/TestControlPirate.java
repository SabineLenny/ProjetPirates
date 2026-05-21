package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlPirate {
	
    private ControlPirate controlPirate;
    @BeforeEach
    void setUp() {
        controlPirate= new ControlPirate();
    }
    @Test
    void testCreationPirate() {
        Pirate pirate = controlPirate.creationPirate("Barbe Noire");
        assertNotNull(pirate);
        assertEquals("Barbe Noire", pirate.getNom());
        assertEquals(5, pirate.getVie());
        assertEquals(0, pirate.getPosition());
    }
    
    @Test
    void testCreationPirateNomVide() {
        Pirate pirate = controlPirate.creationPirate("");
        assertNotNull(pirate);
        assertEquals("", pirate.getNom());
    }
}
