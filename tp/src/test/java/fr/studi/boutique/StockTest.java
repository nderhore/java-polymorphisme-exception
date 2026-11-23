package fr.studi.boutique;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StockTest {

    private Stock stock;

    @BeforeEach
    void creerStock() {
        stock = new Stock();
    }

    @Test
    void uneReferenceInconnueAUneQuantiteDeZero() {
        assertEquals(0, stock.quantite("CLA-01"));
    }

    @Test
    void approvisionnerEnregistreLaQuantite() {
        stock.approvisionner("CLA-01", 5);

        assertEquals(5, stock.quantite("CLA-01"));
    }

    @Test
    void approvisionnerDeuxFoisAdditionneLesQuantites() {
        stock.approvisionner("CLA-01", 5);
        stock.approvisionner("CLA-01", 3);

        assertEquals(8, stock.quantite("CLA-01"));
    }

    @Test
    void uneReferenceNEstCompteeQuUneFois() {
        stock.approvisionner("CLA-01", 5);
        stock.approvisionner("SOU-01", 2);
        stock.approvisionner("CLA-01", 1);

        assertEquals(2, stock.nombreReferences());
    }

    @Test
    void lesReferencesSousLeSeuilSontTriees() {
        stock.approvisionner("SOU-01", 1);
        stock.approvisionner("CLA-01", 2);
        stock.approvisionner("LIV-01", 10);

        assertEquals(List.of("CLA-01", "SOU-01"), stock.referencesSousLeSeuil(5));
    }
}
