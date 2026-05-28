package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;
//Code par Maksym, édité et testé par Elouan
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
        assertEquals(1, pirate.getPosition());
    }
    
    @Test
    void testCreationPirateNomVide() {
        Pirate pirate = controlPirate.creationPirate("");
        assertNotNull(pirate);
        assertEquals("", pirate.getNom());
    }
}
