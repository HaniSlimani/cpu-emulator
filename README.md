# POBJ TME 6 : Micro-émulateur (entraînement au partiel sur machine)

### Support de TME du cours programmation par objets (LU3IN002), Licence 3, Sorbonne Université, Paris, France.

Le sujet, ainsi qu'une archive de projet Eclipse, sont disponibles sur le [Moodle du cours](https://moodle-sciences-25.sorbonne-universite.fr/).

Pour obtenir votre note, vous devez faire un _fork_ du projet GitLab, puis déposer à la racine du projet (i.e., dans le répertoire contenant ce fichier `README.md`) vos sources sous forme d'une archive ZIP nommée `upload.zip`.
Il est inutile de faire un _clone_ de ce projet, d'utiliser `git` ou de synchroniser votre projet Eclipse avec votre projet GitLab : seul le dépôt de l'archive ZIP sur GitLab est demandé.
L'archive ZIP doit contenir un répertoire `pobj`, contenant un sous-répertoire `tme6`, contenant vos sources.
Pour créer l'archive, en supposant que vous utilisez le répertoire _eclipse-workspace_ d'Eclipse par défaut, vous pouvez faire dans un terminal :
```
cd ~/eclipse-workspace/Emulator/src
zip -r upload.zip pobj
```

Pour déposer une première fois l'archive sous GitLab, vous pouvez cliquer dans l'interface Web sur le bouton `+` à droite du nom de répertoire courant (`emulator/`), puis sur `Upload file`.
Pour la remplacer par une nouvelle version, vous pouvez cliquer sur le nom de fichier dans GitLab, puis sur le bouton `Replace`.

Après chaque dépôt de l'archive, le mécanisme d'intégration continue de GitLab relance automatiquement le processus de correction automatique.
Votre rapport de correction est disponible sous forme de page web dans l'onglet `Build > Pipelines` de votre projet.
La ligne la plus en haut correspond à la dernière correction effectuée.
Le rapport  est alors disponible _via_ le bouton *Download artifacts* dans la colonne de droite, sous forme d'archive `zip` contenant un seul fichier `index.html` que vous pouvez ouvrir dans un navigateur.
Notez que l'intégration continue indiquera toujours un succès (icône verte `passed`) quelle que soit votre note finale, même si votre copie n'est pas parfaite, est absente ou obtient la note 0. Il faudra télécharger et le consulter le rapport pour connaître votre note.
