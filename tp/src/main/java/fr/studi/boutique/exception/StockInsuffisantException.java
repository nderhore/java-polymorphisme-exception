package fr.studi.boutique.exception;

public class StockInsuffisantException extends Exception {

    // Exercice 4 - étape 2 : construire le message et le passer à super(...)
    public StockInsuffisantException(String reference, int demande, int disponible) {
        super("A implémenter");
    }
}
