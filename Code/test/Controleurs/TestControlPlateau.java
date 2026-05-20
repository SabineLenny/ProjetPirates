package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Plateau;
import Entite.TypeCase;

class TestControlPlateau {
    
    private Plateau plateau;
    private ControlPlateau controlPlateau;
    
    @BeforeEach
    void setUp() {
        plateau = controlPlateau.creationPlateau();
    }
    
    @Test
    void testCreationPlateauNonNull() {
        assertNotNull(plateau);
    }
    
    @Test
    void testCreationPlateauTaille() {
        assertTrue(plateau.getPlateau().size() > 0);
    }
    
    @Test
    void testCreationPlateauContientDepart() {
        assertEquals(TypeCase.DEPART, plateau.getPlateau().get(0));
    }
}
