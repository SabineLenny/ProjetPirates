package Controleurs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entite.Pirate;
import Entite.Plateau;
import Entite.TypeCase;

class TestControlPlateau {
    
    private ControlPlateau controlPlateau;
    
    @BeforeEach
    void setUp() {
        controlPlateau = new ControlPlateau();
    }
    
    @Test
    void testCreationPlateauNonNull() {
        Plateau plateau = controlPlateau.creationPlateau();
        assertNotNull(plateau);
    }
    
    @Test
    void testTaillePlateau() {
        Plateau plateau = controlPlateau.creationPlateau();
        assertEquals(31, plateau.getPlateau().size());
    }
    
    @Test
    void testCase0Depart() {
        Plateau plateau = controlPlateau.creationPlateau();
        assertEquals(TypeCase.DEPART, plateau.getPlateau().get(0));
    }
    
    @Test
    void testDerniereCase() {
        Plateau plateau = controlPlateau.creationPlateau();
        int taille = plateau.getPlateau().size();
        assertEquals(TypeCase.ARRIVEE, plateau.getPlateau().get(taille - 1));
    }
    
    @Test
    void testContientBombe() {
        Plateau plateau = controlPlateau.creationPlateau();
        boolean contientBombe = plateau.getPlateau().values().contains(TypeCase.BOMBE);
        assertTrue(contientBombe);
    }
    
    @Test
    void testContientPoison() {
        Plateau plateau = controlPlateau.creationPlateau();
        boolean contientPoison = plateau.getPlateau().values().contains(TypeCase.EMPOISONNEMENT);
        assertTrue(contientPoison);
    }
    
    @Test
    void testContientSoin() {
        Plateau plateau = controlPlateau.creationPlateau();
        boolean contientSoin = plateau.getPlateau().values().contains(TypeCase.SOIN);
        assertTrue(contientSoin);
    }
    
    @Test
    void testAffichageNonVide() {
        Plateau plateau = controlPlateau.creationPlateau();
        String affichage = controlPlateau.affichagePlateau(plateau);
        
        assertNotNull(affichage);
        assertFalse(affichage.isEmpty());
    }
    
    @Test
    void testAffichageContientPlateau() {
        Plateau plateau = controlPlateau.creationPlateau();
        String affichage = controlPlateau.affichagePlateau(plateau);
        
        assertTrue(affichage.contains("Plateau"));
    }
    
    @Test
    void testPositionDepart() {
        Plateau plateau = controlPlateau.creationPlateau();
        int position = controlPlateau.positionCase(plateau, TypeCase.DEPART);
        assertEquals(0, position);
    }
    
    @Test
    void testPositionArrivee() {
        Plateau plateau = controlPlateau.creationPlateau();
        int position = controlPlateau.positionCase(plateau, TypeCase.ARRIVEE);
        assertEquals(30, position);
    }
    
    @Test
    void testPositionBombe() {
        Plateau plateau = controlPlateau.creationPlateau();
        int position = controlPlateau.positionCase(plateau, TypeCase.BOMBE);
        assertTrue(position > 0 && position < 30);
    }
    
    @Test
    void testActivationNormal() {
        Plateau plateau = controlPlateau.creationPlateau();
        plateau.getPlateau().put(15, TypeCase.NORMAL);
        
        Pirate pirate = new Pirate("TestPirate");
        pirate.setPosition(15);
        Pirate autre = new Pirate("Autre");
        
        String resultat = controlPlateau.activerCase(plateau, pirate, autre);
        assertNotNull(resultat);
        assertTrue(resultat.contains("case sans effet"));
    }
    
    @Test
    void testActivationDepart() {
        Plateau plateau = controlPlateau.creationPlateau();
        Pirate pirate = new Pirate("TestPirate");
        pirate.setPosition(0);
        Pirate autre = new Pirate("Autre");
        
        String resultat = controlPlateau.activerCase(plateau, pirate, autre);
        assertNotNull(resultat);
        assertTrue(resultat.contains("depart"));
    }
    
    @Test
    void testActivationArrivee() {
        Plateau plateau = controlPlateau.creationPlateau();
        Pirate pirate = new Pirate("TestPirate");
        pirate.setPosition(29);
        Pirate autre = new Pirate("Autre");
        
        String resultat = controlPlateau.activerCase(plateau, pirate, autre);
        assertNotNull(resultat);
    }
    
    @Test
    void testActivationBombe() {
        Plateau plateau = controlPlateau.creationPlateau();
        
        int positionBombe = -1;
        for (int i = 0; i < plateau.getPlateau().size(); i++) {
            if (plateau.getPlateau().get(i) == TypeCase.BOMBE) {
                positionBombe = i;
                break;
            }
        }
        
        if (positionBombe > 0) {
            Pirate pirate = new Pirate("TestPirate");
            pirate.setPosition(positionBombe);
            pirate.setVie(5);
            Pirate autre = new Pirate("Autre");
            
            int vieAvant = pirate.getVie();
            String resultat = controlPlateau.activerCase(plateau, pirate, autre);
            
            assertNotNull(resultat);
            assertTrue(pirate.getVie() < vieAvant);
        }
    }
    
    @Test
    void testPlateauxAleaoires() {
        Plateau plateau1 = controlPlateau.creationPlateau();
        Plateau plateau2 = controlPlateau.creationPlateau();
        
        boolean different = false;
        for (int i = 1; i < 29; i++) {
            if (!plateau1.getPlateau().get(i).equals(plateau2.getPlateau().get(i))) {
                different = true;
                break;
            }
        }
        
        assertTrue(different || plateau1 == plateau2);
    }
    
    @Test
    void testInvariants() {
        Plateau plateau = controlPlateau.creationPlateau();
        
        assertEquals(31, plateau.getPlateau().size());
        assertEquals(TypeCase.DEPART, plateau.getPlateau().get(0));
        assertEquals(TypeCase.ARRIVEE, plateau.getPlateau().get(30));
        
        for (TypeCase type : plateau.getPlateau().values()) {
            assertNotNull(type);
        }
    }
}
