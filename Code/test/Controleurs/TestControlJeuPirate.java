package Controleurs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;
import Entite.Plateau;
import java.util.Map;
//Code par Maksym, édité et testé par Elouan
class TestControlJeuPirate {
    
    private ControlJeuPirate controlJeu;
    
    @BeforeEach
    void setUp() {
        controlJeu = new ControlJeuPirate();
    }
    
    @Test
    void testInstancierJeu() {
        controlJeu.instancierJeu("Barbe-Noire", "Capitaine");
        assertNotNull(controlJeu.getMapPirate());
        assertNotNull(controlJeu.getPlateau());
    }
    
    @Test
    void testCreationDeuxPirates() {
        controlJeu.instancierJeu("Pirate1", "Pirate2");
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        assertEquals(2, pirates.size());
        assertNotNull(pirates.get(0));
        assertNotNull(pirates.get(1));
    }
    
    @Test
    void testNomsDesPirates() {
        String nom1 = "BlackBeard";
        String nom2 = "CaptainHook";
        controlJeu.instancierJeu(nom1, nom2);
        
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        assertEquals(nom1, pirates.get(0).getNom());
        assertEquals(nom2, pirates.get(1).getNom());
    }
    
    @Test
    void testIndicePirateDepart() {
        controlJeu.instancierJeu("P1", "P2");
        assertEquals(0, controlJeu.getIndicePirate());
    }
    
    @Test
    void testRunTrue() {
        controlJeu.instancierJeu("P1", "P2");
        assertTrue(controlJeu.getRun());
    }
    
    @Test
    void testTaillePlateau() {
        controlJeu.instancierJeu("P1", "P2");
        Plateau plateau = controlJeu.getPlateau();
        assertNotNull(plateau);
        assertEquals(31, plateau.getPlateau().size());
    }
    
    @Test
    void testCaseDepart() {
        controlJeu.instancierJeu("P1", "P2");
        Plateau plateau = controlJeu.getPlateau();
        assertEquals(Entite.TypeCase.DEPART, plateau.getPlateau().get(0));
    }
    
    @Test
    void testCaseArrivee() {
        controlJeu.instancierJeu("P1", "P2");
        Plateau plateau = controlJeu.getPlateau();
        assertEquals(Entite.TypeCase.ARRIVEE, plateau.getPlateau().get(plateau.getPlateau().size()-1));
    }
    
    @Test
    void testIncrementIndicePirate0To1() {
        controlJeu.instancierJeu("P1", "P2");
        assertEquals(0, controlJeu.getIndicePirate());
        controlJeu.incrementIndicePirate();
        assertEquals(1, controlJeu.getIndicePirate());
    }
    
    @Test
    void testIncrementIndicePirate1To0() {
        controlJeu.instancierJeu("P1", "P2");
        controlJeu.incrementIndicePirate();
        controlJeu.incrementIndicePirate();
        assertEquals(0, controlJeu.getIndicePirate());
    }
    
    @Test
    void testRotationRepetee() {
        controlJeu.instancierJeu("P1", "P2");
        for (int i = 0; i < 10; i++) {
            controlJeu.incrementIndicePirate();
            int attendu = (i + 1) % 2;
            assertEquals(attendu, controlJeu.getIndicePirate());
        }
    }
    
    @Test
    void testRotation20Fois() {
        controlJeu.instancierJeu("P1", "P2");
        for (int i = 0; i < 20; i++) {
            controlJeu.incrementIndicePirate();
        }
        assertEquals(0, controlJeu.getIndicePirate());
    }
    
    @Test
    void testGetMapPirate() {
        controlJeu.instancierJeu("P1", "P2");
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        assertNotNull(pirates);
        assertTrue(pirates.containsKey(0));
        assertTrue(pirates.containsKey(1));
    }
    
    @Test
    void testGetPlateau() {
        controlJeu.instancierJeu("P1", "P2");
        Plateau plateau = controlJeu.getPlateau();
        assertNotNull(plateau);
        assertNotNull(plateau.getPlateau());
    }
    
    @Test
    void testModificationPirateViaMap() {
        controlJeu.instancierJeu("P1", "P2");
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        pirates.get(0).setVie(2);
        assertEquals(2, pirates.get(0).getVie());
        assertEquals(2, controlJeu.getMapPirate().get(0).getVie());
    }
    
    @Test
    void testModificationPositionViaMap() {
        controlJeu.instancierJeu("P1", "P2");
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        pirates.get(1).setPosition(15);
        assertEquals(15, controlJeu.getMapPirate().get(1).getPosition());
    }
    
    @Test
    void testEtatInitialCoherent() {
        controlJeu.instancierJeu("P1", "P2");
        assertNotNull(controlJeu.getMapPirate());
        assertNotNull(controlJeu.getPlateau());
        assertEquals(0, controlJeu.getIndicePirate());
        assertTrue(controlJeu.getRun());
        
        Map<Integer, Pirate> pirates = controlJeu.getMapPirate();
        assertEquals(5, pirates.get(0).getVie());
        assertEquals(5, pirates.get(1).getVie());
        assertEquals(1, pirates.get(0).getPosition());
        assertEquals(1, pirates.get(1).getPosition());
    }
    
    @Test
    void testJeuxIndependants() {
        ControlJeuPirate jeu1 = new ControlJeuPirate();
        ControlJeuPirate jeu2 = new ControlJeuPirate();
        
        jeu1.instancierJeu("P1", "P2");
        jeu2.instancierJeu("P3", "P4");
        
        assertNotEquals(jeu1.getMapPirate(), jeu2.getMapPirate());
        assertNotEquals(jeu1.getPlateau(), jeu2.getPlateau());
    }
    
    @Test
    void testNomsSpeciaux() {
        assertDoesNotThrow(() -> {
            controlJeu.instancierJeu("Pirate@123", "PirateÀÉ");
        });
    }
    
    @Test
    void testNomsVides() {
        assertDoesNotThrow(() -> {
            controlJeu.instancierJeu("", "");
        });
    }
    
    @Test
    void testNomsTresLongs() {
        String nomLong = "A".repeat(100);
        assertDoesNotThrow(() -> {
            controlJeu.instancierJeu(nomLong, nomLong);
        });
    }
    
    @Test
    void testInstanciationsMultiples() {
        for (int i = 0; i < 10; i++) {
            ControlJeuPirate jeu = new ControlJeuPirate();
            jeu.instancierJeu("P1_" + i, "P2_" + i);
            assertNotNull(jeu.getMapPirate());
        }
    }
    
    @Test
    void testScenarioComplet() {
        controlJeu.instancierJeu("Barbe-Noire", "Capitaine");
        assertTrue(controlJeu.getRun());
        
        assertEquals(0, controlJeu.getIndicePirate());
        Pirate pirateActuel = controlJeu.getMapPirate().get(0);
        assertEquals("Barbe-Noire", pirateActuel.getNom());
        
        pirateActuel.setPosition(10);
        assertEquals(10, controlJeu.getMapPirate().get(0).getPosition());
        
        controlJeu.incrementIndicePirate();
        assertEquals(1, controlJeu.getIndicePirate());
        Pirate autrePirate = controlJeu.getMapPirate().get(1);
        assertEquals("Capitaine", autrePirate.getNom());
    }
    
    @Test
    void testTourComplet() {
        controlJeu.instancierJeu("P1", "P2");
        
        Pirate p0 = controlJeu.getMapPirate().get(0);
        p0.setPosition(5);
        controlJeu.incrementIndicePirate();
        
        assertEquals(1, controlJeu.getIndicePirate());
        Pirate p1 = controlJeu.getMapPirate().get(1);
        p1.setPosition(7);
        controlJeu.incrementIndicePirate();
        
        assertEquals(0, controlJeu.getIndicePirate());
        assertEquals(5, controlJeu.getMapPirate().get(0).getPosition());
        assertEquals(7, controlJeu.getMapPirate().get(1).getPosition());
    }
}
