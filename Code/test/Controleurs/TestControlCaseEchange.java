package Controleurs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;

class TestControlCaseEchange {
    
    private Pirate pirate1;
    private Pirate pirate2;
    private ControlCaseEchange controlCaseEchange;
    
    @BeforeEach
    void setUp() {
        pirate1 = new Pirate("Pirate1");
        pirate2 = new Pirate("Pirate2");
        pirate1.setPosition(10);
        pirate2.setPosition(20);
        controlCaseEchange=new ControlCaseEchange();
    }
    
    @Test
    void testAppliquerEffetEchangePositions() {
        controlCaseEchange.appliquerEffet(pirate1, pirate2);
        assertEquals(20, pirate1.getPosition());
        assertEquals(10, pirate2.getPosition());
    }
    
    @Test
    void testAppliquerEffetEchangePosition0() {
        pirate1.setPosition(0);
        pirate2.setPosition(15);
        controlCaseEchange.appliquerEffet(pirate1, pirate2);
        assertEquals(15, pirate1.getPosition());
        assertEquals(0, pirate2.getPosition());
    }
}
