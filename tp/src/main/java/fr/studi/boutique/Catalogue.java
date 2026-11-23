package fr.studi.boutique;

import fr.studi.boutique.exception.ProduitIntrouvableException;
import fr.studi.boutique.modele.Produit;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Catalogue {

    private final Set<Produit> produits = new HashSet<>();

    // Exercice 2 - étape 1
    public boolean ajouter(Produit produit) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 2 - étape 1
    public int taille() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 2 - étape 3
    public Set<String> categories() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Bonus
    public Map<String, List<Produit>> parCategorie() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Bonus
    public Produit trouver(String reference) throws ProduitIntrouvableException {
        throw new UnsupportedOperationException("A implémenter");
    }
}
