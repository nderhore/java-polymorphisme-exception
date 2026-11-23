package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceCommande {

    private final Stock stock;
    private final Panier panier = new Panier();
    private final List<String> journal = new ArrayList<>();

    public ServiceCommande(Stock stock) {
        this.stock = stock;
    }

    public Panier getPanier() {
        return panier;
    }

    public List<String> getJournal() {
        return Collections.unmodifiableList(journal);
    }

    // Exercice 4 - étape 4 : try / catch / finally
    public boolean commander(Produit produit) {
        throw new UnsupportedOperationException("A implémenter");
    }
}
