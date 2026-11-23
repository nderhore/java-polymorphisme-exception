# Collections et exceptions en Java

Projet d'exercices sur le framework Collections (`List`, `Set`, `Map`) et la gestion des exceptions (`try` / `catch` / `finally`, exceptions personnalisées).

Fil rouge : une petite boutique en ligne avec un panier, un catalogue, un stock et un service de commande.

## Contenu du dépôt

```
.
├── tp/         les exercices à compléter (consignes dans tp/README.md)
└── corrige/    la correction
```

Les deux dossiers sont des projets Maven indépendants avec les mêmes tests.

## Prérequis

| Outil | Version | Téléchargement |
|---|---|---|
| JDK (Java) | 25 minimum | [Eclipse Temurin 25](https://adoptium.net/temurin/releases/?version=25) ou [Oracle JDK 25](https://www.oracle.com/java/technologies/downloads/#java25) |
| Maven | 3.9 minimum | [maven.apache.org](https://maven.apache.org/download.cgi) |
| Git | récent | [git-scm.com](https://git-scm.com/downloads) |
| Un IDE | au choix | [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) ou [VS Code](https://code.visualstudio.com/) avec l'[Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) |

Avec IntelliJ IDEA, Maven est intégré : l'installer séparément n'est utile que pour lancer les commandes dans un terminal.

## Installation

### 1. Installer le JDK 25

**Windows**
1. Télécharger l'installeur `.msi` de [Temurin 25](https://adoptium.net/temurin/releases/?version=25&os=windows).
2. Pendant l'installation, cocher **Set JAVA_HOME variable** et **Add to PATH**.

**macOS**
- Télécharger le `.pkg` de [Temurin 25](https://adoptium.net/temurin/releases/?version=25&os=mac) et l'installer,
- ou avec [Homebrew](https://brew.sh/) : `brew install --cask temurin@25`

**Linux (Debian, Ubuntu)**
- Suivre la [procédure Adoptium](https://adoptium.net/installation/linux/), puis `sudo apt install temurin-25-jdk`
- ou avec [SDKMAN!](https://sdkman.io/) : `sdk install java 25-tem`

### 2. Installer Maven

**Windows**
1. Télécharger l'archive **Binary zip** sur [maven.apache.org](https://maven.apache.org/download.cgi).
2. La décompresser, par exemple dans `C:\outils\maven`.
3. Ajouter `C:\outils\maven\bin` à la variable d'environnement `Path` (voir plus bas).

**macOS** : `brew install maven`

**Linux** : `sudo apt install maven` ou `sdk install maven`

### 3. Configurer les variables d'environnement

Nécessaire seulement si `java -version` ou `mvn -v` ne répond pas dans un nouveau terminal.

**Windows**
1. Menu Démarrer, chercher **Modifier les variables d'environnement système**, puis **Variables d'environnement**.
2. Créer la variable `JAVA_HOME` avec le dossier du JDK, par exemple `C:\Program Files\Eclipse Adoptium\jdk-25`.
3. Dans `Path`, ajouter `%JAVA_HOME%\bin` et le dossier `bin` de Maven.
4. Fermer et rouvrir le terminal.

**macOS / Linux** : ajouter dans `~/.zshrc` ou `~/.bashrc`

```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 25)    # macOS
# export JAVA_HOME=/usr/lib/jvm/temurin-25-jdk-amd64  # Linux, adapter le chemin
export PATH="$JAVA_HOME/bin:$PATH"
```

puis `source ~/.zshrc` (ou `~/.bashrc`).

### 4. Vérifier l'installation

```bash
java -version
mvn -v
git --version
```

`java -version` doit afficher `25` (ou plus). `mvn -v` doit afficher une ligne `Java version: 25...` : si une autre version apparaît, `JAVA_HOME` pointe vers un autre JDK.

## Récupérer le projet

```bash
git clone https://github.com/nderhore/java-polymorphisme-exception.git
cd java-polymorphisme-exception
```

Ou, sans Git : bouton **Code** puis **Download ZIP** sur la page GitHub, et décompresser l'archive.

## Ouvrir le projet dans l'IDE

**IntelliJ IDEA**
1. **File > Open**, sélectionner le dossier `tp` (pas la racine du dépôt), puis **Trust Project**.
2. **File > Project Structure > Project** : vérifier que **SDK** vaut 25. Sinon, **Add SDK > JDK** et choisir le dossier du JDK 25.
3. Attendre la fin de l'import Maven (barre de progression en bas).

**VS Code**
1. Installer l'[Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack).
2. **File > Open Folder**, sélectionner le dossier `tp`.
3. Les tests apparaissent dans l'onglet **Testing** (icône en forme de fiole).

## Lancer les tests

Depuis le dossier `tp` :

```bash
mvn test                      # tous les tests
mvn -q test -Dtest=PanierTest # un seul fichier de test
mvn test -Dtags.exclus=       # tous les tests, bonus compris
```

Au départ, les tests sont rouges : c'est normal, les méthodes sont à écrire. Le but est de les faire passer au vert un par un.

Dans l'IDE, on peut aussi cliquer sur le triangle vert à côté d'une classe ou d'une méthode de test.

## Lancer le programme

```bash
mvn -q compile
java -cp target/classes fr.studi.boutique.Main
```

## Exercices

Les consignes détaillées sont dans [tp/README.md](tp/README.md).

1. `Panier` : une `List`
2. `Catalogue` : un `Set`
3. `Stock` : une `Map`
4. Exceptions : `try` / `catch` / `finally` et exception personnalisée
5. Bonus : tri, regroupement par catégorie, seconde exception

## Problèmes fréquents

| Message | Cause | Solution |
|---|---|---|
| `'java' n'est pas reconnu...` / `command not found: mvn` | Le `PATH` n'est pas configuré | Voir l'étape 3, puis rouvrir le terminal |
| `release version 25 not supported` | Maven utilise un JDK plus ancien | Faire pointer `JAVA_HOME` vers le JDK 25 et vérifier avec `mvn -v` |
| `No POM in this directory` | Commande lancée à la racine du dépôt | Se placer dans `tp` ou `corrige` avec `cd tp` |
| `UnsupportedOperationException: A implémenter` | La méthode n'est pas encore écrite | Normal : c'est l'exercice |
| Classes en rouge dans IntelliJ | Import Maven non terminé ou mauvais SDK | Clic droit sur `pom.xml` > **Maven > Reload project**, vérifier le SDK |
