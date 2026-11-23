package fr.studi.boutique.exception;

public class ProduitIntrouvableException extends Exception {

    public ProduitIntrouvableException(String reference) {
        super("Produit introuvable : " + reference);
    }
}
