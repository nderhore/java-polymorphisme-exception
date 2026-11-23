package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceCommandeTest {

    private final Produit clavier = new Produit("CLA-01", "Clavier", "Informatique", 4500);
    private final Produit souris = new Produit("SOU-01", "Souris", "Informatique", 2000);

    private Stock stock;
    private ServiceCommande service;

    @BeforeEach
    void preparer() {
        stock = new Stock();
        stock.approvisionner("CLA-01", 5);
        service = new ServiceCommande(stock);
    }

    @Test
    void uneCommandeReussieRemplitLePanier() {
        assertTrue(service.commander(clavier));

        assertEquals(1, service.getPanier().taille());
        assertEquals(4, stock.quantite("CLA-01"));
        assertEquals(List.of("Début CLA-01", "Commande validée", "Fin CLA-01"), service.getJournal());
    }

    @Test
    void uneCommandeSansStockEstRefusee() {
        assertFalse(service.commander(souris));

        assertEquals(0, service.getPanier().taille());
        assertEquals(List.of(
                "Début SOU-01",
                "Erreur : Stock insuffisant pour SOU-01 : demandé 1, disponible 0",
                "Fin SOU-01"), service.getJournal());
    }

    @Test
    void leJournalSeTermineToujoursParFin() {
        service.commander(clavier);
        service.commander(souris);
        service.commander(clavier);

        List<String> journal = service.getJournal();
        assertEquals(9, journal.size());
        assertEquals("Fin CLA-01", journal.get(2));
        assertEquals("Fin SOU-01", journal.get(5));
        assertEquals("Fin CLA-01", journal.get(8));
        assertEquals(2, service.getPanier().taille());
    }
}
