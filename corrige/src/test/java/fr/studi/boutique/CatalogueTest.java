package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogueTest {

    private final Produit clavier = new Produit("CLA-01", "Clavier", "Informatique", 4500);
    private final Produit souris = new Produit("SOU-01", "Souris", "Informatique", 2000);
    private final Produit livre = new Produit("LIV-01", "Apprendre Java", "Livre", 3900);
    private final Produit cable = new Produit("CAB-01", "Câble HDMI", "Accessoire", 900);

    private Catalogue catalogue;

    @BeforeEach
    void creerCatalogue() {
        catalogue = new Catalogue();
    }

    @Test
    void ajouterUnNouveauProduitRenvoieVrai() {
        assertTrue(catalogue.ajouter(clavier));
        assertEquals(1, catalogue.taille());
    }

    @Test
    void deuxProduitsDifferentsSontAjoutes() {
        catalogue.ajouter(clavier);
        catalogue.ajouter(souris);

        assertEquals(2, catalogue.taille());
    }

    @Test
    void unDoublonNestPasAjoute() {
        catalogue.ajouter(clavier);
        Produit memeReference = new Produit("CLA-01", "Clavier sans fil", "Informatique", 5900);

        assertFalse(catalogue.ajouter(memeReference));
        assertEquals(1, catalogue.taille());
    }

    @Test
    void unCatalogueVideNAAucuneCategorie() {
        assertTrue(catalogue.categories().isEmpty());
    }

    @Test
    void chaqueCategorieNApparaitQuUneFois() {
        catalogue.ajouter(clavier);
        catalogue.ajouter(souris);
        catalogue.ajouter(livre);

        assertEquals(2, catalogue.categories().size());
    }

    @Test
    void lesCategoriesSontTrieesParOrdreAlphabetique() {
        catalogue.ajouter(livre);
        catalogue.ajouter(clavier);
        catalogue.ajouter(cable);

        assertEquals(List.of("Accessoire", "Informatique", "Livre"), List.copyOf(catalogue.categories()));
    }
}
