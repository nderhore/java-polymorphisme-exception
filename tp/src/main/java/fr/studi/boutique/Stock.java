package fr.studi.boutique;

import fr.studi.boutique.exception.StockInsuffisantException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {

    private final Map<String, Integer> quantites = new HashMap<>();

    // Exercice 3 - étape 1
    public int quantite(String reference) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 3 - étape 2
    public void approvisionner(String reference, int quantite) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 3 - étape 3
    public int nombreReferences() {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 3 - étape 4
    public List<String> referencesSousLeSeuil(int seuil) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 4 - étape 1
    public boolean approvisionnerDepuisSaisie(String reference, String saisie) {
        throw new UnsupportedOperationException("A implémenter");
    }

    // Exercice 4 - étape 3
    public void retirer(String reference, int quantite) throws StockInsuffisantException {
        throw new UnsupportedOperationException("A implémenter");
    }
}
