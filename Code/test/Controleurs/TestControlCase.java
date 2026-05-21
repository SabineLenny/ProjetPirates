package Controleurs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;
import Entite.TypeCase;

class TestControlCase {
    
    private ControlCase controlCase;
    private Pirate pirate1;
    private Pirate pirate2;
    
    @BeforeEach
    void setUp() {
        controlCase = new ControlCase();
        pirate1 = new Pirate("Pirate1");
        pirate2 = new Pirate("Pirate2");
    }
    
    @Test
    void testCaseNormal() {
        String resultat = controlCase.selectCase(TypeCase.NORMAL, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(resultat.contains("case sans effet"));
        assertTrue(resultat.contains(pirate1.getNom()));
    }
    
    @Test
    void testCaseBombe() {
        pirate1.setVie(5);
        int vieAvant = pirate1.getVie();
        String resultat = controlCase.selectCase(TypeCase.BOMBE, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(pirate1.getVie() < vieAvant);
    }
    
    @Test
    void testCaseEmpoisonnement() {
        int dureeAvant = pirate1.getDureeEmpoisonnement();
        String resultat = controlCase.selectCase(TypeCase.EMPOISONNEMENT, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(pirate1.getDureeEmpoisonnement() > dureeAvant);
    }
    
    @Test
    void testCaseSoin() {
        pirate1.setVie(2);
        int vieAvant = pirate1.getVie();
        String resultat = controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(pirate1.getVie() > vieAvant);
    }
    
    @Test
    void testCaseEchange() {
        pirate1.setPosition(5);
        pirate2.setPosition(15);
        String resultat = controlCase.selectCase(TypeCase.ECHANGE, pirate1, pirate2);
        assertNotNull(resultat);
        assertEquals(15, pirate1.getPosition());
        assertEquals(5, pirate2.getPosition());
    }
    
    @Test
    void testCaseDepart() {
        String resultat = controlCase.selectCase(TypeCase.DEPART, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(resultat.contains("depart"));
        assertTrue(resultat.contains(pirate1.getNom()));
    }
    
    @Test
    void testCaseArrivee() {
        String resultat = controlCase.selectCase(TypeCase.ARRIVEE, pirate1, pirate2);
        assertNotNull(resultat);
        assertTrue(resultat.contains("arrive"));
        assertTrue(resultat.contains(pirate1.getNom()));
    }
    
    @Test
    void testBommeReduitVie() {
        for (int i = 1; i <= 5; i++) {
            pirate1.setVie(i);
            int vieAvant = pirate1.getVie();
            controlCase.selectCase(TypeCase.BOMBE, pirate1, pirate2);
            assertTrue(pirate1.getVie() < vieAvant);
        }
    }
    
    @Test
    void testSoinAugmenteVie() {
        pirate1.setVie(2);
        controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        assertEquals(3, pirate1.getVie());
    }
    
    @Test
    void testSoinEliminePoisonEffet() {
        pirate1.setDureeEmpoisonnement(3);
        controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        assertEquals(0, pirate1.getDureeEmpoisonnement());
    }
    
    @Test
    void testPoisonDuree() {
        for (int i = 0; i < 5; i++) {
            pirate1.setDureeEmpoisonnement(0);
            controlCase.selectCase(TypeCase.EMPOISONNEMENT, pirate1, pirate2);
            assertTrue(pirate1.getDureeEmpoisonnement() >= 1 && 
                pirate1.getDureeEmpoisonnement() <= 6);
        }
    }
    
    @Test
    void testEchangePositions() {
        pirate1.setPosition(7);
        pirate2.setPosition(23);
        controlCase.selectCase(TypeCase.ECHANGE, pirate1, pirate2);
        assertEquals(23, pirate1.getPosition());
        assertEquals(7, pirate2.getPosition());
    }
    
    @Test
    void testTousRetournentMessage() {
        for (TypeCase typeCase : TypeCase.values()) {
            String resultat = controlCase.selectCase(typeCase, pirate1, pirate2);
            assertNotNull(resultat);
            assertFalse(resultat.isEmpty());
        }
    }
    
    @Test
    void testMessagesContiennentNom() {
        pirate1.setNom("TestPirate");
        TypeCase[] casesAvecNom = {TypeCase.NORMAL, TypeCase.DEPART, TypeCase.ARRIVEE};
        
        for (TypeCase typeCase : casesAvecNom) {
            String resultat = controlCase.selectCase(typeCase, pirate1, pirate2);
            assertTrue(resultat.contains("TestPirate"));
        }
    }
    
    @Test
    void testMultiplesBombes() {
        pirate1.setVie(5);
        int vieAfter1 = 5;
        
        for (int i = 0; i < 3; i++) {
            controlCase.selectCase(TypeCase.BOMBE, pirate1, pirate2);
            assertTrue(pirate1.getVie() < vieAfter1 || pirate1.getVie() == 0);
            vieAfter1 = pirate1.getVie();
        }
    }
    
    @Test
    void testBommePuisSoin() {
        pirate1.setVie(5);
        controlCase.selectCase(TypeCase.BOMBE, pirate1, pirate2);
        int vieApresBombe = pirate1.getVie();
        
        controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        int vieApresSoin = pirate1.getVie();
        
        assertTrue(vieApresSoin > vieApresBombe);
    }
    
    @Test
    void testPoisonPuisSoin() {
        controlCase.selectCase(TypeCase.EMPOISONNEMENT, pirate1, pirate2);
        assertTrue(pirate1.getDureeEmpoisonnement() > 0);
        
        controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        assertEquals(0, pirate1.getDureeEmpoisonnement());
    }
    
    @Test
    void testEchangeDouble() {
        int pos1Initial = pirate1.getPosition();
        int pos2Initial = pirate2.getPosition();
        
        controlCase.selectCase(TypeCase.ECHANGE, pirate1, pirate2);
        controlCase.selectCase(TypeCase.ECHANGE, pirate1, pirate2);
        
        assertEquals(pos1Initial, pirate1.getPosition());
        assertEquals(pos2Initial, pirate2.getPosition());
    }
    
   
    
    @Test
    void testSoinPleineSante() {
        pirate1.setVie(5);
        controlCase.selectCase(TypeCase.SOIN, pirate1, pirate2);
        assertEquals(5, pirate1.getVie());
    }
    
    @Test
    void testEchangePositionIdentiques() {
        pirate1.setPosition(15);
        pirate2.setPosition(15);
        
        controlCase.selectCase(TypeCase.ECHANGE, pirate1, pirate2);
        
        assertEquals(15, pirate1.getPosition());
        assertEquals(15, pirate2.getPosition());
    }
    
    @Test
    void testCaseNormalInerte() {
        int vie = 3;
        int pos = 10;
        int poison = 2;
        
        pirate1.setVie(vie);
        pirate1.setPosition(pos);
        pirate1.setDureeEmpoisonnement(poison);
        
        controlCase.selectCase(TypeCase.NORMAL, pirate1, pirate2);
        
        assertEquals(vie, pirate1.getVie());
        assertEquals(pos, pirate1.getPosition());
        assertEquals(poison, pirate1.getDureeEmpoisonnement());
    }
    
    @Test
    void testTousCasGeres() {
        for (TypeCase typeCase : TypeCase.values()) {
            assertDoesNotThrow(() -> {
                controlCase.selectCase(typeCase, pirate1, pirate2);
            });
        }
    }
}
