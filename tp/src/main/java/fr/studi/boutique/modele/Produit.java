package fr.studi.boutique.modele;

import java.util.Objects;

public class Produit {

    private final String reference;
    private final String nom;
    private final String categorie;
    private final int prixCentimes;

    public Produit(String reference, String nom, String categorie, int prixCentimes) {
        if (prixCentimes < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        this.reference = reference;
        this.nom = nom;
        this.categorie = categorie;
        this.prixCentimes = prixCentimes;
    }

    public String getReference() {
        return reference;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public int getPrixCentimes() {
        return prixCentimes;
    }

    // Deux produits sont égaux s'ils ont la même référence
    @Override
    public boolean equals(Object autre) {
        if (this == autre) {
            return true;
        }
        if (!(autre instanceof Produit produit)) {
            return false;
        }
        return reference.equals(produit.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @Override
    public String toString() {
        return nom;
    }
}
