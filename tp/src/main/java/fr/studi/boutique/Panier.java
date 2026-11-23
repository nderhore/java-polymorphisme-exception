package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Panier {

    private final List<Produit> produits = new ArrayList<>();

    public List<Produit> getProduits() {
        return Collections.unmodifiableList(produits);
    }

    // Exercice 1 - étape 1
    public void ajouter(Produit produit) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 1 - étape 1
    public int taille() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 1 - étape 2
    public Produit get(int position) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 1 - étape 3
    public int totalCentimes() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 1 - étape 4
    public boolean contient(String reference) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 1 - étape 5 : utiliser un Iterator
    public int retirer(String reference) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Bonus
    public List<Produit> trierParPrix() {
        throw new UnsupportedOperationException("A implémenter");
    }
}
