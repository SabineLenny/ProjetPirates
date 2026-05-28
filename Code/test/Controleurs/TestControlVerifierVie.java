package Controleurs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;
// Code par Maksym, édité et testé par Elouan
class TestControlVerifierVie {
    
    private ControlVerifierVie controlVie;
    private Pirate pirate;
    
    @BeforeEach
    void setUp() {
        controlVie = new ControlVerifierVie();
        pirate = new Pirate("TestPirate");
    }
    
    @Test
    void testVerifierVieVivant5() {
        pirate.setVie(5);
        assertTrue(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testVerifierVieVivant1() {
        pirate.setVie(1);
        assertTrue(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testVerifierVieMort0() {
        pirate.setVie(0);
        assertFalse(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testVerifierVieMortNegatif() {
        pirate.setVie(-5);
        assertFalse(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testVerifierVieChaqueLevelVivant() {
        for (int vie = 1; vie <= 5; vie++) {
            pirate.setVie(vie);
            assertTrue(controlVie.verifierVie(pirate));
        }
    }
    
    @Test
    void testVerifierVieMortPlusieursFois() {
        int[] viesMortes = {0, -1, -5, -100};
        for (int vie : viesMortes) {
            pirate.setVie(vie);
            assertFalse(controlVie.verifierVie(pirate));
        }
    }
    
    @Test
    void testAffichageNonVide() {
        pirate.setVie(3);
        String affichage = controlVie.affichageVie(pirate);
        assertNotNull(affichage);
        assertFalse(affichage.isEmpty());
    }
    
    @Test
    void testAffichageVivant5() {
        pirate.setVie(5);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("Personne n'est encore mort"));
    }
    
    @Test
    void testAffichageVivant1() {
        pirate.setVie(1);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("Personne n'est encore mort"));
    }
    
    @Test
    void testAffichageMort0() {
        pirate.setVie(0);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
        assertTrue(affichage.contains(pirate.getNom()));
    }
    
    @Test
    void testAffichageMortNegatif() {
        pirate.setVie(-3);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
    }
    
    @Test
    void testAffichageContientNomMort() {
        pirate.setNom("BlackBeard");
        pirate.setVie(0);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("BlackBeard"));
    }
    
    @Test
    void testAffichageChaqueLevelVivant() {
        for (int vie = 1; vie <= 5; vie++) {
            pirate.setVie(vie);
            String affichage = controlVie.affichageVie(pirate);
            assertTrue(affichage.contains("Personne n'est encore mort"));
        }
    }
    
    @Test
    void testCoherenceVivant() {
        pirate.setVie(3);
        
        boolean estVivant = controlVie.verifierVie(pirate);
        String affichage = controlVie.affichageVie(pirate);
        
        if (estVivant) {
            assertTrue(affichage.contains("Personne n'est encore mort"));
        }
    }
    
    @Test
    void testCoherence_Mort() {
        pirate.setVie(0);
        
        boolean estVivant = controlVie.verifierVie(pirate);
        String affichage = controlVie.affichageVie(pirate);
        
        if (!estVivant) {
            assertTrue(affichage.contains("est mort"));
        }
    }
    
    @Test
    void testCoherenceAllLevels() {
        for (int vie = -1; vie <= 5; vie++) {
            pirate.setVie(vie);
            
            boolean estVivant = controlVie.verifierVie(pirate);
            String affichage = controlVie.affichageVie(pirate);
            
            if (estVivant) {
                assertTrue(affichage.contains("Personne n'est encore mort"));
            } else {
                assertTrue(affichage.contains("est mort"));
            }
        }
    }
    
    @Test
    void testFormatMortSautLigne() {
        pirate.setVie(0);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("\n"));
    }
    
    @Test
    void testFormatVivantSautLigne() {
        pirate.setVie(5);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("\n"));
    }
    
    @Test
    void testVerifierVieTresElevee() {
        pirate.setVie(1000);
        assertTrue(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testVerifierVieTresNegative() {
        pirate.setVie(-1000);
        assertFalse(controlVie.verifierVie(pirate));
    }
    
    @Test
    void testAffichageNomVide() {
        pirate.setNom("");
        pirate.setVie(0);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
    }
    
    @Test
    void testAffichageNomTresLong() {
        pirate.setNom("A".repeat(100));
        pirate.setVie(0);
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
    }
    
    @Test
    void testVerificationApresBombe() {
        pirate.setVie(5);
        assertTrue(controlVie.verifierVie(pirate));
        
        pirate.setVie(2);
        assertTrue(controlVie.verifierVie(pirate));
        
        pirate.setVie(0);
        assertFalse(controlVie.verifierVie(pirate));
        
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
    }
    
    @Test
    void testVerificationApresSoin() {
        pirate.setVie(2);
        assertTrue(controlVie.verifierVie(pirate));
        
        pirate.setVie(3);
        assertTrue(controlVie.verifierVie(pirate));
        
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("Personne n'est encore mort"));
    }
    
    @Test
    void testSimulationJeuComplet() {
        pirate.setVie(5);
        
        pirate.setVie(pirate.getVie() - 2);
        assertTrue(controlVie.verifierVie(pirate));
        
        pirate.setVie(pirate.getVie() + 1);
        assertTrue(controlVie.verifierVie(pirate));
        
        pirate.setVie(0);
        assertFalse(controlVie.verifierVie(pirate));
        
        String affichage = controlVie.affichageVie(pirate);
        assertTrue(affichage.contains("est mort"));
    }
}
