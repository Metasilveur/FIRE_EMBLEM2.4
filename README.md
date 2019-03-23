Projet Mobile
========

## Sommaire
### Partie 1 
* [Pourquoi la vie]
* [Ah ok]
### Partie 2
* [Pourquoi la vie]
* [Ah ok]

## Présentation
Pour ce projet mobile, j'ai choisi de créer une application sur un jeu vidéo RPG (jeu de rôle) appellé Fire Emblem. L'application a deux buts distincts : d'une part afficher l'ensemble des personnages ainsi que leurs détails (statistiques, compétences, origines...), et d'une autre part, proposer un mini jeux de combats, qui va permettre de faire combattre deux personnages (que je détaillerai un peu plus tard).


## Consignes et contraintes du projet
Ce projet présentait plusieurs consignes à respecter, parmi lesquels : 

* Utiliser une recycler view : 
Dans ce projet, j'utilise plusieurs recycler view : 
<br> ► Une pour l'affichage des personnages.
<br> ► Une pour l'affichage des compétences liées aux personnages.
<br> ► Une pour l'historique du jeu de combat (sur lequel je reviendrai plus tard).

* Faire un appel à une API Rest :
Dans mon cas, il s'agit d'une API rest d'un jeu vidéo que j'ai recopié, modifié puis hébergé sur mon github.

* Avoir une architecture logicielle (MVC ou MVVM):
J'ai opté pour l'architecture MVC (modèle - vue - controlleur)

* Notifications Push :
Les notifications push se font via FireBase. 

* Autres fonctionnalités : 
En plus des indications à respecter, j'ai rajouter quelques fonctionnalités parmi lesquels :
<br> ► un affichage graphique qui dépend des caractéristiques du personnage.
<br> ► Un système de tri de personnage en fonction de leur type de déplacement.
<br> ► Une animation d'écran de titre.
<br> ► Un mode de jeu de combat automatique animé, qui permet de faire combattre deux personnages au choix.
<br> ► Un système d'historique, affichant le déroulement complet du combat.
<br> ► Une possibilité de changer les préférences sonores ou graphiques de l'application.

## Fonctionnement détaillé de l'application

### Première activité : écran de titre 
Lors du démarage de l'application, une animation se lance, puis s'arrête. On peut ensuite appuyer n'importe où sur l'écran pour passer à l'activité suivante.

### Seconde activité : écran de sélection de mode
On passe ensuité à l'écran de sélection, qui nous permet de choisir entre trois modes :
- Un mode de combat automatique.
- L'affichage de tous les personnages récupérés par l'API rest.
- Un mode de modification des options du jeux.

### Mode de combat automatique
Il s'agit d'une des fonctionnalités supplémentaires que j'ai décidé de rajouter dans mon projet. Ce mode se décompose en trois activités :
<br> ► Une première activité où l'on nous demande de choisir deux combattants parmi ceux de la liste que l'on nous affiche. Les personnages sélectionnés sont affiché normalement, tandis que les autres sont transparents. On a également la possibilité d'appuer sur un personnage sélectionner pour le "désélectionner".

<br> ► Une seconde activité, où l'on affiche l'animation du combat entre les deux personnages que l'on choisit. Le combat est automatique et se fait en tour par tour : il s'arrête lorsque les points de vies d'une des deux combattants passent à 0. À ce moment, on nous propose un bouton qui nous permet de visualiser l'historique du combat.

<br> ► Une troisième acitivité, qui est justemment l'historique du combat précédent. Il affiche donc, via une recycle view, le déroulé de chaque tour, avec le numéro de tour, les dégats infligés, et les points de vie finaux.



<p align="center">
  <img width="150" height="300" src="https://image.noelshack.com/fichiers/2019/12/6/1553363970-capture.png">
</p>
