package Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import Controleurs.ControlPirate;
import Entite.Pirate;

class ControlPirateTest {
    
    @Test
    void testCreationPirate() {
        Pirate pirate = ControlPirate.creationPirate("Barbe Noire");
        assertNotNull(pirate);
        assertEquals("Barbe Noire", pirate.getNom());
        assertEquals(5, pirate.getVie());
        assertEquals(0, pirate.getPosition());
    }
    
    @Test
    void testCreationPirateNomVide() {
        Pirate pirate = ControlPirate.creationPirate("");
        assertNotNull(pirate);
        assertEquals("", pirate.getNom());
    }
}
