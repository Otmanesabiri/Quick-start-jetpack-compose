# TP4 - Découverte de Jetpack Compose

## Introduction

Ce TP avait pour objectif de découvrir **Jetpack Compose**, l'outil moderne de développement d'interfaces utilisateur pour Android. Contrairement au système traditionnel de vues XML, Compose utilise une approche déclarative en Kotlin pour créer des UI riches et interactives.

## Objectifs du TP

1. Comprendre les principes fondamentaux de Jetpack Compose
2. Créer des composants d'interface utilisateur réutilisables
3. Implémenter l'architecture MVVM avec Compose
4. Gérer les états et les interactions utilisateur
5. Développer une simulation de feux de circulation

## Composants développés

### 1. Composants de base
- **Accueil** : Un simple composant affichant un message de bienvenue
- **AccueilMultiple** : Affichage dynamique d'une liste de noms
- **AccueilRow** : Disposition horizontale des éléments d'interface

### 2. Feu tricolore
Un composant de feu tricolore avec 3 états (rouge, orange, vert) qui change d'état séquentiellement à chaque clic sur un bouton.

```kotlin
data class Feu3State(val rouge: Boolean = true, val orange: Boolean = false, val vert: Boolean = false)

@Composable
fun Feu3View(state: Feu3State) {
    // Affiche un feu tricolore dont l'état est contrôlé par le ViewModel
}
```

### 3. Carrefour
Une extension du feu tricolore qui simule un carrefour à 4 feux (Nord, Sud, Est, Ouest) avec des phases synchronisées.

```kotlin
data class CarrefourState(
    val feuNord: Feu3State = Feu3State(rouge = true),
    val feuSud: Feu3State = Feu3State(rouge = true),
    val feuEst: Feu3State = Feu3State(rouge = true),
    val feuOuest: Feu3State = Feu3State(rouge = true),
    val phase: Int = 0
)
```

## Architecture MVVM

Ce projet a été construit selon l'architecture **Model-View-ViewModel (MVVM)** :

1. **Model** : Les classes de données `Feu3State` et `CarrefourState`
2. **View** : Les composants Compose comme `Feu3View` et `CarrefourView`
3. **ViewModel** : Les classes `Feu3ViewModel` et `CarrefourViewModel` qui gèrent la logique métier et l'état de l'interface

## Concepts clés appris

- **Composants Composables** : Création de fonctions annotées avec `@Composable`
- **Prévisualisation** : Utilisation de `@Preview` pour prévisualiser les interfaces sans lancer l'émulateur
- **État** : Gestion de l'état avec `mutableStateOf` et `remember`
- **Disposition** : Utilisation de `Column`, `Row` pour organiser les éléments
- **Styles** : Application de styles avec les modificateurs (`modifier`)
- **ViewModel** : Intégration des ViewModels avec Compose via `viewModel()`

## Difficultés rencontrées

- Configuration des dépendances de Jetpack Compose avec des versions compatibles
- Gestion des erreurs de package et de référence
- Organisation du code pour maintenir une séparation propre des préoccupations

## Conclusion

Ce TP a permis une première approche pratique de Jetpack Compose, montrant comment cette technologie simplifie considérablement le développement d'interfaces utilisateur sur Android. Les concepts d'UI déclarative, de composants réutilisables et de gestion d'état sont fondamentaux pour maîtriser ce framework moderne.

## Captures d'écran

![image](https://github.com/user-attachments/assets/50aabf4b-7df1-4def-99e2-29ff38d6e30e)
