package fr.studi.boutique;

import fr.studi.boutique.exception.ProduitIntrouvableException;
import fr.studi.boutique.modele.Produit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Catalogue {

    private final Set<Produit> produits = new HashSet<>();

    public boolean ajouter(Produit produit) {
        return produits.add(produit);
    }

    public int taille() {
        return produits.size();
    }

    public Set<String> categories() {
        Set<String> categories = new TreeSet<>();
        for (Produit produit : produits) {
            categories.add(produit.getCategorie());
        }
        return categories;
    }

    public Map<String, List<Produit>> parCategorie() {
        Map<String, List<Produit>> resultat = new TreeMap<>();
        for (Produit produit : produits) {
            String categorie = produit.getCategorie();
            if (!resultat.containsKey(categorie)) {
                resultat.put(categorie, new ArrayList<>());
            }
            resultat.get(categorie).add(produit);
        }
        return resultat;
    }

    public Produit trouver(String reference) throws ProduitIntrouvableException {
        for (Produit produit : produits) {
            if (produit.getReference().equals(reference)) {
                return produit;
            }
        }
        throw new ProduitIntrouvableException(reference);
    }
}
