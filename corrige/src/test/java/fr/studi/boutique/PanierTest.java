package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanierTest {

    private final Produit clavier = new Produit("CLA-01", "Clavier", "Informatique", 4500);
    private final Produit souris = new Produit("SOU-01", "Souris", "Informatique", 2000);

    private Panier panier;

    @BeforeEach
    void creerPanier() {
        panier = new Panier();
    }

    @Test
    void unPanierNeufEstVide() {
        assertEquals(0, panier.taille());
    }

    @Test
    void ajouterAugmenteLaTaille() {
        panier.ajouter(clavier);
        panier.ajouter(souris);

        assertEquals(2, panier.taille());
    }

    @Test
    void getRenvoieLeProduitALaPositionDemandee() {
        panier.ajouter(clavier);
        panier.ajouter(souris);

        assertEquals(clavier, panier.get(0));
        assertEquals(souris, panier.get(1));
    }

    @Test
    void uneListeAccepteLesDoublons() {
        panier.ajouter(clavier);
        panier.ajouter(clavier);

        assertEquals(2, panier.taille());
    }

    @Test
    void leTotalDUnPanierVideVautZero() {
        assertEquals(0, panier.totalCentimes());
    }

    @Test
    void leTotalAdditionneLesPrix() {
        panier.ajouter(clavier);
        panier.ajouter(souris);
        panier.ajouter(clavier);

        assertEquals(11000, panier.totalCentimes());
    }

    @Test
    void contientTrouveUneReferencePresente() {
        panier.ajouter(souris);

        assertTrue(panier.contient("SOU-01"));
        assertFalse(panier.contient("CLA-01"));
    }

    @Test
    void retirerSupprimeToutesLesOccurrences() {
        panier.ajouter(clavier);
        panier.ajouter(souris);
        panier.ajouter(clavier);

        int nombreRetires = panier.retirer("CLA-01");

        assertEquals(2, nombreRetires);
        assertEquals(1, panier.taille());
        assertEquals(souris, panier.get(0));
    }

    @Test
    void retirerUneReferenceAbsenteNeChangeRien() {
        panier.ajouter(souris);

        assertEquals(0, panier.retirer("CLA-01"));
        assertEquals(1, panier.taille());
    }

    @Test
    void laListeExposeeNestPasModifiable() {
        assertThrows(UnsupportedOperationException.class, () -> panier.getProduits().add(clavier));
    }

    @Test
    void supprimerDansUnForEachProvoqueUneErreur() {
        List<String> noms = new ArrayList<>(List.of("Clavier", "Souris", "Écran"));

        assertThrows(ConcurrentModificationException.class, () -> {
            for (String nom : noms) {
                if (nom.equals("Clavier")) {
                    noms.remove(nom);
                }
            }
        });
    }
}
