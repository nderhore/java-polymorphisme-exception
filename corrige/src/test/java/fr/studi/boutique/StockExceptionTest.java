package fr.studi.boutique;

import fr.studi.boutique.exception.StockInsuffisantException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockExceptionTest {

    private Stock stock;

    @BeforeEach
    void creerStock() {
        stock = new Stock();
    }

    @Test
    void uneSaisieValideEstEnregistree() {
        assertTrue(stock.approvisionnerDepuisSaisie("CLA-01", "12"));
        assertEquals(12, stock.quantite("CLA-01"));
    }

    @Test
    void uneSaisieInvalideEstRefuseeSansPlanter() {
        assertFalse(stock.approvisionnerDepuisSaisie("CLA-01", "douze"));
        assertEquals(0, stock.quantite("CLA-01"));
    }

    @Test
    void lExceptionContientUnMessageClair() {
        StockInsuffisantException exception = new StockInsuffisantException("CLA-01", 5, 2);

        assertEquals("Stock insuffisant pour CLA-01 : demandé 5, disponible 2", exception.getMessage());
    }

    @Test
    void retirerDiminueLeStock() throws StockInsuffisantException {
        stock.approvisionner("CLA-01", 5);

        stock.retirer("CLA-01", 3);

        assertEquals(2, stock.quantite("CLA-01"));
    }

    @Test
    void onPeutRetirerToutLeStock() throws StockInsuffisantException {
        stock.approvisionner("CLA-01", 2);

        stock.retirer("CLA-01", 2);

        assertEquals(0, stock.quantite("CLA-01"));
    }

    @Test
    void retirerTropLeveUneExceptionEtNeChangeRien() {
        stock.approvisionner("CLA-01", 2);

        StockInsuffisantException exception = assertThrows(StockInsuffisantException.class,
                () -> stock.retirer("CLA-01", 5));

        assertEquals("Stock insuffisant pour CLA-01 : demandé 5, disponible 2", exception.getMessage());
        assertEquals(2, stock.quantite("CLA-01"));
    }
}
