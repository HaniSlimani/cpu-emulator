# CPU Emulator

Émulateur d'architecture matérielle modulaire en Java, développé dans le cadre du cours de **Programmation par Objets** (LU3IN002), Licence 3 Informatique, **Sorbonne Université**.

Le projet implémente l'ensemble des composants d'un système émulé — microprocesseur, mémoires (RAM/ROM), décodeur d'adresses, périphériques, boucle d'émulation — selon une architecture modulaire reposant sur des interfaces et des patrons de conception classiques.

En bonus, ces composants sont réutilisés pour faire fonctionner un émulateur de la borne d'arcade **Asteroids** (Atari, 1979) basé sur un processeur **MOS 6502**.

## Contexte

- Cours : LU3IN002 — Programmation par Objets
- Niveau : Licence 3 Informatique, Sorbonne Université
- Travail réalisé en binôme avec [@akkaboutaina](https://github.com/akkaboutaina)
- Sujet original disponible sur le Moodle du cours (TME 6)

## Composants implémentés

### Mémoires (`pobj.tme6.memory`)
- `RAM` — Mémoire vive de taille fixe, implémentant `IMemory` puis étendue à `ICopyableMemory`
- `ROM` — Mémoire morte héritant de `RAM`, en lecture seule
- `AddressMask` — Masque d'adresses (motif **Décorateur**) pour adapter un espace d'adressage
- `MemorySlot` — Couple (adresse de base, mémoire) pour le mappage mémoire
- `AddressDecoder` — Décodeur d'adresses (motif **Composite**) redirigeant lectures/écritures vers la bonne mémoire

### Microprocesseur (`pobj.tme6.cpu`)
- `CPUState` — État du processeur : registre `A`, compteur de programme `PC`, mémoire associée
- `CPU` — Microprocesseur implémentant le cycle **Fetch–Decode–Execute**
- `op/` — Implémentation des opcodes : `OpSet`, `OpAdd`, `OpLoad`, `OpStore`, `OpJump` (interface générique `IOpCode<T>`)

### Périphériques (`pobj.tme6.device`)
- `PeriodicDevice` — Classe abstraite pour les périphériques à action périodique (motif **Template Method**)
- `Screen` — Périphérique d'affichage couplant une mémoire vidéo et un affichage console toutes les 100 unités de temps
- `Clock` + `Alarm` — Horloge programmable avec liste d'alarmes triée

### Émulateur (`pobj.tme6`)
- `EmulatorLoop` — Boucle d'émulation orchestrant le CPU et les périphériques
- `Emulator` — Assemblage complet d'une machine émulée (CPU + RAM + ROM + Screen via décodeur d'adresses)

### Bonus : Asteroids (`pobj.tme6.extra`)
Émulateur fonctionnel du jeu d'arcade **Asteroids (Atari, 1979)** réutilisant les composants mémoire ci-dessus. Le processeur **MOS 6502** et le **Digital Vector Generator** d'Atari sont fournis par l'énoncé ; les briques `RAM`, `ROM`, `AddressMask`, `AddressDecoder` et `EmulatorLoop` que j'ai implémentées sont celles qui permettent à l'ensemble de fonctionner. Le rendu graphique utilise **JavaFX**.

## Stack technique

- **Java 11+** (requis pour JavaFX dans le bonus Asteroids)
- **JUnit** pour les tests unitaires
- Patrons de conception : **Décorateur**, **Composite**, **Template Method**
- Programmation par interfaces et héritage

## Structure du projet
src/pobj/tme6/
├── cpu/              # Processeur, état, opcodes
│   └── op/           # Implémentations des instructions
├── memory/           # RAM, ROM, masques, décodeur
├── device/           # Écran, horloge, alarmes
├── extra/            # Bonus Asteroids (6502 + DVG fournis)
├── test/             # Tests unitaires JUnit
├── notation/         # Tests d'évaluation automatique
├── Emulator.java     # Assemblage de la machine émulée
└── EmulatorLoop.java # Boucle d'émulation

## Compilation et exécution

Le projet est conçu pour être ouvert dans **Eclipse** (cf. consignes du TME). Pour une compilation manuelle :

```bash
cd src
find . -name "*.java" | xargs javac
```

Pour lancer l'émulateur de base :

```bash
java -cp . pobj.tme6.test.EmulatorMain
```

Pour lancer la démo Asteroids (nécessite JavaFX configuré) :

```bash
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.graphics \
     -cp . pobj.tme6.extra.AsteroidsMain
```

## Compétences mises en œuvre

- Conception orientée objet : héritage, interfaces, généricité
- Application de patrons de conception classiques (Décorateur, Composite, Template Method)
- Architecture modulaire et composable
- Tests unitaires avec JUnit
- Travail collaboratif avec Git

## Auteurs

- [Hani Slimani](https://github.com/HaniSlimani)
- [Akka Boutaina](https://github.com/akkaboutaina)
