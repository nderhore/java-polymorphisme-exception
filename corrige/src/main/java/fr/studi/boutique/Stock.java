package fr.studi.boutique;

import fr.studi.boutique.exception.StockInsuffisantException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stock {

    private final Map<String, Integer> quantites = new HashMap<>();

    public int quantite(String reference) {
        return quantites.getOrDefault(reference, 0);
    }

    public void approvisionner(String reference, int quantite) {
        int quantiteActuelle = quantite(reference);
        quantites.put(reference, quantiteActuelle + quantite);
    }

    public int nombreReferences() {
        return quantites.size();
    }

    public List<String> referencesSousLeSeuil(int seuil) {
        List<String> resultat = new ArrayList<>();
        for (Map.Entry<String, Integer> entree : quantites.entrySet()) {
            if (entree.getValue() < seuil) {
                resultat.add(entree.getKey());
            }
        }
        Collections.sort(resultat);
        return resultat;
    }

    public boolean approvisionnerDepuisSaisie(String reference, String saisie) {
        try {
            int quantite = Integer.parseInt(saisie);
            approvisionner(reference, quantite);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void retirer(String reference, int quantite) throws StockInsuffisantException {
        int disponible = quantite(reference);
        if (quantite > disponible) {
            throw new StockInsuffisantException(reference, quantite, disponible);
        }
        quantites.put(reference, disponible - quantite);
    }
}
