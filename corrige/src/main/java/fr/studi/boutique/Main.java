package fr.studi.boutique;

import fr.studi.boutique.modele.Produit;

public class Main {

    void main() {
        Produit clavier = new Produit("CLA-01", "Clavier", "Informatique", 4500);
        Produit souris = new Produit("SOU-01", "Souris", "Informatique", 2000);
        Produit livre = new Produit("LIV-01", "Apprendre Java", "Livre", 3900);

        Catalogue catalogue = new Catalogue();
        catalogue.ajouter(clavier);
        catalogue.ajouter(souris);
        catalogue.ajouter(livre);
        IO.println("Catégories : " + catalogue.categories());

        Stock stock = new Stock();
        stock.approvisionner("CLA-01", 3);
        stock.approvisionner("SOU-01", 1);
        stock.approvisionner("LIV-01", 5);

        ServiceCommande service = new ServiceCommande(stock);
        service.commander(clavier);
        service.commander(souris);
        service.commander(souris);
        service.commander(livre);

        IO.println("Journal :");
        for (String ligne : service.getJournal()) {
            IO.println("  " + ligne);
        }

        Panier panier = service.getPanier();
        IO.println("Panier : " + panier.taille() + " article(s), " + panier.totalCentimes() + " centimes");
        IO.println("A réapprovisionner (moins de 2) : " + stock.referencesSousLeSeuil(2));
    }
}
