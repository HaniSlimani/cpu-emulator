# CPU Emulator

Émulateur d'architecture matérielle modulaire en Java. Le projet implémente l'ensemble des composants d'un système émulé — microprocesseur, mémoires (RAM / ROM), décodeur d'adresses, périphériques d'entrée-sortie, boucle d'émulation — selon une architecture extensible reposant sur des interfaces et des patrons de conception classiques.

L'ensemble est ensuite réutilisé pour faire tourner un émulateur fonctionnel de la borne d'arcade **Asteroids** (Atari, 1979) avec rendu graphique vectoriel.

## Aperçu

- **Architecture modulaire** : chaque composant matériel (CPU, mémoires, périphériques) est isolé derrière une interface, ce qui permet de composer plusieurs machines différentes à partir des mêmes briques.
- **Cycle Fetch–Decode–Execute** complet, avec un jeu d'instructions extensible.
- **Mappage mémoire** dynamique via un décodeur d'adresses (motif Composite) et masques d'adresses (motif Décorateur).
- **Périphériques périodiques** synchronisés sur la boucle d'émulation (motif Template Method).
- **Tests unitaires** systématiques avec JUnit pour chaque composant.

## Composants implémentés

### Mémoires (`pobj.tme6.memory`)
- `RAM` — Mémoire vive de taille fixe, implémentant `IMemory` puis étendue à `ICopyableMemory` pour le snapshotting.
- `ROM` — Mémoire morte héritant de `RAM`, en lecture seule.
- `AddressMask` — Masque d'adresses (motif **Décorateur**) pour adapter un espace d'adressage à la taille réelle de la mémoire physique.
- `MemorySlot` — Couple (adresse de base, mémoire) pour le mappage mémoire.
- `AddressDecoder` — Décodeur d'adresses (motif **Composite**) redirigeant lectures et écritures vers la mémoire correspondante en fonction de l'adresse demandée.

### Microprocesseur (`pobj.tme6.cpu`)
- `CPUState` — État du processeur : registre d'accumulateur `A`, compteur de programme `PC`, mémoire associée.
- `CPU` — Microprocesseur implémentant le cycle **Fetch–Decode–Execute**.
- `op/` — Implémentation des instructions sous forme d'opcodes : `OpSet`, `OpAdd`, `OpLoad`, `OpStore`, `OpJump`, via une interface générique `IOpCode<T>` réutilisable pour d'autres processeurs.

### Périphériques (`pobj.tme6.device`)
- `PeriodicDevice` — Classe abstraite pour les périphériques à action périodique (motif **Template Method**).
- `Screen` — Périphérique d'affichage couplant une mémoire vidéo de 10 mots à un affichage console rafraîchi toutes les 100 unités de temps.
- `Clock` + `Alarm` — Horloge programmable maintenant une liste d'alarmes triée par date pour exécution efficace à chaque tick.

### Émulateur (`pobj.tme6`)
- `EmulatorLoop` — Boucle d'émulation orchestrant l'exécution du CPU et le tick des périphériques.
- `Emulator` — Assemblage complet d'une machine émulée : CPU + RAM + ROM + Screen connectés via le décodeur d'adresses.

### Démo : Asteroids (`pobj.tme6.extra`)
Émulateur fonctionnel du jeu d'arcade **Asteroids (Atari, 1979)** construit au-dessus des composants ci-dessus. Le projet intègre une émulation open-source du processeur **MOS 6502** et du **Digital Vector Generator** d'Atari ; les composants mémoire et la boucle d'émulation que j'ai conçus servent de socle à l'ensemble. Le rendu graphique utilise **JavaFX**.

## Stack technique

- **Java 11+** (requis pour JavaFX)
- **JUnit** pour les tests unitaires
- Patrons de conception : **Décorateur**, **Composite**, **Template Method**
- Programmation par interfaces, héritage, généricité

## Structure du projet

```
src/pobj/tme6/
├── cpu/              # Processeur, état, opcodes
│   └── op/           # Implémentations des instructions
├── memory/           # RAM, ROM, masques, décodeur d'adresses
├── device/           # Écran, horloge, alarmes
├── extra/            # Démo Asteroids (intègre 6502 et DVG)
├── test/             # Tests unitaires JUnit
├── notation/         # Tests d'évaluation supplémentaires
├── Emulator.java     # Assemblage de la machine émulée
└── EmulatorLoop.java # Boucle d'émulation
```

## Compilation et exécution

Compilation manuelle :

```bash
cd src
find . -name "*.java" | xargs javac
```

Lancer l'émulateur de base :

```bash
java -cp . pobj.tme6.test.EmulatorMain
```

Lancer la démo Asteroids (nécessite JavaFX configuré) :

```bash
java --module-path /chemin/vers/javafx/lib \
     --add-modules javafx.controls,javafx.graphics \
     -cp . pobj.tme6.extra.AsteroidsMain
```

## Compétences mises en œuvre

- Conception orientée objet : héritage, interfaces, généricité
- Application de patrons de conception classiques
- Architecture modulaire et composable autour d'interfaces stables
- Tests unitaires systématiques avec JUnit
- Travail collaboratif avec Git

## Crédits

Projet développé en collaboration avec [@akkaboutaina](https://github.com/akkaboutaina).

L'émulation MOS 6502 et le rendu DVG utilisés dans la démo Asteroids sont basés sur des implémentations open-source intégrées au projet.
