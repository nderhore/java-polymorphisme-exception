package fr.studi.boutique;

import fr.studi.boutique.exception.StockInsuffisantException;
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

    public boolean commander(Produit produit) {
        journal.add("Début " + produit.getReference());
        try {
            stock.retirer(produit.getReference(), 1);
            panier.ajouter(produit);
            journal.add("Commande validée");
            return true;
        } catch (StockInsuffisantException e) {
            journal.add("Erreur : " + e.getMessage());
            return false;
        } finally {
            journal.add("Fin " + produit.getReference());
        }
    }
}
