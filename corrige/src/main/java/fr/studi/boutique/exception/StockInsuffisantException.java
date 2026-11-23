package fr.studi.boutique.exception;

public class StockInsuffisantException extends Exception {

    public StockInsuffisantException(String reference, int demande, int disponible) {
        super("Stock insuffisant pour " + reference + " : demandé " + demande + ", disponible " + disponible);
    }
}
