package fr.studi.boutique;

import fr.studi.boutique.exception.ProduitIntrouvableException;
import fr.studi.boutique.modele.Produit;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@Tag("bonus")
class BonusTest {

    private final Produit clavier = new Produit("CLA-01", "Clavier", "Informatique", 4500);
    private final Produit souris = new Produit("SOU-01", "Souris", "Informatique", 2000);
    private final Produit livre = new Produit("LIV-01", "Apprendre Java", "Livre", 3900);

    @Test
    void trierParPrixRenvoieUneCopieTriee() {
        Panier panier = new Panier();
        panier.ajouter(clavier);
        panier.ajouter(souris);
        panier.ajouter(livre);

        assertEquals(List.of(souris, livre, clavier), panier.trierParPrix());
        assertEquals(clavier, panier.get(0));
    }

    @Test
    void parCategorieRegroupeLesProduits() {
        Catalogue catalogue = new Catalogue();
        catalogue.ajouter(livre);
        catalogue.ajouter(clavier);
        catalogue.ajouter(souris);

        Map<String, List<Produit>> resultat = catalogue.parCategorie();

        assertEquals(List.of("Informatique", "Livre"), List.copyOf(resultat.keySet()));
        assertEquals(Set.of(clavier, souris), Set.copyOf(resultat.get("Informatique")));
        assertEquals(List.of(livre), resultat.get("Livre"));
    }

    @Test
    void trouverRenvoieLeProduit() throws ProduitIntrouvableException {
        Catalogue catalogue = new Catalogue();
        catalogue.ajouter(clavier);

        assertEquals(clavier, catalogue.trouver("CLA-01"));
    }

    @Test
    void trouverUneReferenceInconnueLeveUneException() {
        Catalogue catalogue = new Catalogue();

        ProduitIntrouvableException exception = assertThrows(ProduitIntrouvableException.class,
                () -> catalogue.trouver("XXX-99"));

        assertEquals("Produit introuvable : XXX-99", exception.getMessage());
    }

    @Test
    void unPrixNegatifEstRefuse() {
        assertThrows(IllegalArgumentException.class, () -> new Produit("A", "B", "C", -1));
    }
}
