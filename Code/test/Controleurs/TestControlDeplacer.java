package Controleurs;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlDeplacer {
    
    private Pirate pirate;
    private ControlDeplacer controlDeplacer;
    
    @BeforeEach
    void setUp() {
        pirate = new Pirate("Test");
    }
    
    @Test
    void testDeplacerPiratePosition() {
        int positionInitiale = pirate.getPosition();
        controlDeplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() > positionInitiale);
        assertTrue(pirate.getPosition() <= 30);
    }
    
    @Test
    void testDeplacerBounce() {
        pirate.setPosition(27);
        controlDeplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() <= 30);
    }
    
    @Test
    void testDeplacerPositionMin0() {
        pirate.setPosition(0);
        controlDeplacer.deplacer(pirate);
        assertTrue(pirate.getPosition() > 0);
    }
}
