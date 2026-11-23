# TP : Collections et exceptions

Durée : 1 h. Java 25, Maven, JUnit 5.

## Fil rouge

Une petite boutique en ligne :

- un `Panier` qui contient des produits : une **List**
- un `Catalogue` sans doublon : un **Set**
- un `Stock` qui associe une référence à une quantité : une **Map**
- un `ServiceCommande` qui gère les erreurs : les **exceptions**

La classe `Produit` est fournie. Deux produits sont égaux s'ils ont la même référence.

## Démarrage

```
mvn test
```

Au départ presque tout est rouge : les méthodes lèvent `UnsupportedOperationException("A implémenter")`.
Remplacez cette ligne par votre code. Ne modifiez pas les tests.

Lancer un seul fichier de test :

```
mvn -q test -Dtest=PanierTest
```

## Exercice 1 : le panier (List)

Fichier : `Panier.java`. Test : `PanierTest`.

Le champ `produits` est une `ArrayList<Produit>`. Faites les étapes dans l'ordre et lancez le test après chacune.

1. `ajouter(produit)` et `taille()` : utilisez `add` et `size`.
2. `get(position)` : utilisez `get`. Les positions commencent à 0.
3. `totalCentimes()` : parcourez la liste avec une boucle `for (Produit produit : produits)` et additionnez les prix.
4. `contient(reference)` : même boucle, renvoyez `true` dès que la référence correspond. Comparez les textes avec `equals`, pas avec `==`.
5. `retirer(reference)` : retirez tous les produits de cette référence et renvoyez combien ont été retirés.
   On ne peut pas supprimer dans une boucle `for` (regardez le test `supprimerDansUnForEachProvoqueUneErreur`). Utilisez un `Iterator` :

```java
Iterator<Produit> iterateur = produits.iterator();
while (iterateur.hasNext()) {
    Produit produit = iterateur.next();
    // si besoin : iterateur.remove();
}
```

## Exercice 2 : le catalogue (Set)

Fichier : `Catalogue.java`. Test : `CatalogueTest`.

Le champ `produits` est un `HashSet<Produit>`.

1. `ajouter(produit)` et `taille()` : `add` renvoie déjà `true` ou `false`, renvoyez simplement son résultat.
2. Lancez le test `unDoublonNestPasAjoute`. Pourquoi le deuxième clavier est-il refusé alors que son nom et son prix sont différents ? Regardez `equals` et `hashCode` dans `Produit`.
3. `categories()` : créez un `Set<String>`, ajoutez-y la catégorie de chaque produit, renvoyez-le.
   Commencez avec un `HashSet` et lancez le test : l'ordre n'est pas garanti. Remplacez par un `TreeSet`.

## Exercice 3 : le stock (Map)

Fichier : `Stock.java`. Test : `StockTest`.

Le champ `quantites` est une `HashMap<String, Integer>` : référence vers quantité.

1. `quantite(reference)` : renvoyez la valeur associée, ou 0 si la référence est absente. Regardez `getOrDefault`.
2. `approvisionner(reference, quantite)` : lisez la quantité actuelle, ajoutez, puis enregistrez avec `put`.
3. `nombreReferences()` : le nombre de clés.
4. `referencesSousLeSeuil(seuil)` : les références dont la quantité est strictement inférieure au seuil, triées.
   Parcourez la map avec `for (Map.Entry<String, Integer> entree : quantites.entrySet())`, puis `Collections.sort`.

## Exercice 4 : les exceptions

Fichiers : `Stock.java`, `StockInsuffisantException.java`, `ServiceCommande.java`.
Tests : `StockExceptionTest`, puis `ServiceCommandeTest`.

1. `Stock.approvisionnerDepuisSaisie(reference, saisie)` : la quantité arrive sous forme de texte, par exemple `"12"`.
   Convertissez-la avec `Integer.parseInt`. Si le texte n'est pas un nombre, Java lève une `NumberFormatException` :
   attrapez-la avec `try / catch` et renvoyez `false`.
2. `StockInsuffisantException` : construisez le message attendu par le test et passez-le à `super(...)`.
   Exemple : `Stock insuffisant pour CLA-01 : demandé 5, disponible 2`
3. `Stock.retirer(reference, quantite)` : si la quantité demandée dépasse le stock, lancez l'exception avec `throw new StockInsuffisantException(...)`. Sinon, mettez à jour la quantité.
4. `ServiceCommande.commander(produit)` : commande un exemplaire du produit. Le journal doit contenir :

| Cas | Journal |
|---|---|
| Succès | `Début CLA-01`, `Commande validée`, `Fin CLA-01` |
| Stock insuffisant | `Début SOU-01`, `Erreur : <message de l'exception>`, `Fin SOU-01` |

```java
journal.add("Début " + ...);
try {
    // retirer 1 du stock, ajouter au panier, écrire "Commande validée", renvoyer true
} catch (StockInsuffisantException e) {
    // écrire l'erreur, renvoyer false
} finally {
    // écrire "Fin ..." : exécuté dans tous les cas
}
```

## Bonus

Tests : `BonusTest`. Pour les lancer : `mvn test -Dtags.exclus=`

1. `Panier.trierParPrix()` : renvoie une copie de la liste triée du moins cher au plus cher, sans modifier le panier. Regardez `Comparator.comparingInt`.
2. `Catalogue.parCategorie()` : une `Map` catégorie vers liste de produits, catégories triées (`TreeMap`).
3. `ProduitIntrouvableException` et `Catalogue.trouver(reference)` : renvoie le produit ou lève l'exception avec le message `Produit introuvable : XXX-99`.

## Fin

```
mvn test
mvn -q compile
java -cp target/classes fr.studi.boutique.Main
```

## Pour discuter

1. Quand choisir une `List`, un `Set` ou une `Map` ?
2. Que se passe-t-il dans le `Catalogue` si on supprime `equals` et `hashCode` de `Produit` ?
3. Pourquoi `StockInsuffisantException` hérite de `Exception` et pas de `RuntimeException` ? Qu'est-ce que ça change pour celui qui appelle `retirer` ?
4. À quoi sert le `finally` si le `catch` écrit déjà quelque chose dans le journal ?
