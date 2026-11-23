package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Panier {

    private final List<Produit> produits = new ArrayList<>();

    public List<Produit> getProduits() {
        return Collections.unmodifiableList(produits);
    }

    public void ajouter(Produit produit) {
        produits.add(produit);
    }

    public int taille() {
        return produits.size();
    }

    public Produit get(int position) {
        return produits.get(position);
    }

    public int totalCentimes() {
        int total = 0;
        for (Produit produit : produits) {
            total += produit.getPrixCentimes();
        }
        return total;
    }

    public boolean contient(String reference) {
        for (Produit produit : produits) {
            if (produit.getReference().equals(reference)) {
                return true;
            }
        }
        return false;
    }

    public int retirer(String reference) {
        int nombreRetires = 0;
        Iterator<Produit> iterateur = produits.iterator();
        while (iterateur.hasNext()) {
            Produit produit = iterateur.next();
            if (produit.getReference().equals(reference)) {
                iterateur.remove();
                nombreRetires++;
            }
        }
        return nombreRetires;
    }

    public List<Produit> trierParPrix() {
        List<Produit> copie = new ArrayList<>(produits);
        copie.sort(Comparator.comparingInt(Produit::getPrixCentimes));
        return copie;
    }
}
