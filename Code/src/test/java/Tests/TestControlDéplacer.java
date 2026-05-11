package Tests;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controleurs.ControlDéplacer;
import Entite.Pirate;

class ControlDéplacerTest {
    
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Test");
    }
    
    @Test
    void testDeplacerPiratePosition() {
        int positionInitiale = pirate.getPosition();
        ControlDéplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() > positionInitiale);
        assertTrue(pirate.getPosition() <= 30);
    }
    
    @Test
    void testDeplacerBounce() {
        pirate.setPosition(27);
        ControlDéplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() <= 30);
    }
    
    @Test
    void testDeplacerPositionMin0() {
        pirate.setPosition(0);
        ControlDéplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() > 0);
    }
}
