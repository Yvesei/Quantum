
# Quantum Computing

## Création d'un Registre Quantique
Pour fabriquer un objet `QReg` avec un seul QBit comme argument :

```scala
val rr : QReg = QReg(1)
// Vous fabriquez une instance de registre quantique avec un seul QBit.
println(rr.render)
// Fabrique une chaîne de caractères qui représente le circuit quantique.
rr.end
// Termine l’utilisation du QBit. Cette ligne est essentielle pour désallouer les ressources utilisées et, si demandé, générer un rapport PDF et une trace d’exécution.
// Sans cette ligne, la simulation ne fonctionnera pas correctement.
```

![Capture d'Écran 2025-01-30 à 19.09.23](images/Screenshot%202025-01-30%20at%2019.09.23.png)

### Structure des Colonnes
- **Colonne `#`** : Indique le numéro du QBit concerné (ici, le QBit numéro 0).
- **Colonne `v`** : Indique la valeur de départ du QBit, ici `0` ou bien le ket `|0>`, qui code son état initial. Par défaut, on part toujours de `|0>`.
- `>-` : La ligne représente la séquence d’application des opérateurs quantiques.

---

![Capture d'Écran 2025-01-30 à 19.13.24](images/Screenshot%202025-01-30%20at%2019.13.24.png)

## Application des Opérateurs
L'opérateur `rr - <()` est utilisé pour appliquer un opérateur sur le registre `rr`.

Sur le schéma ci-dessus :
- La ligne est coupée pour indiquer l’effondrement de la fonction d’onde du QBit #0.
- La ligne se termine par `< 0` pour indiquer une mesure de `0` (ou `|0>`).
- Vous pouvez lire cette valeur avec `rr.?()`.

### Exemple de Code
```scala
val rr : QReg = QReg(1)
println(rr.render)
println("Mesure de l'état")
rr - X() - <()
println(rr.render)
println("La mesure donnée : " + rr.?())
rr.end()
```
L'expression `rr - X() - <()` est équivalente à `rr - X(0) - <(0)`.

---

![Capture d'Écran 2025-01-30 à 19.22.19](images/Screenshot%202025-01-30%20at%2019.22.19.png)

### Rappel sur le Circuit
- Au début, le QBit a l’état initial `|0>`.
- Son amplitude de probabilité pour le ket `|0>` est de `1` (représentée par la barre bleue à gauche).
- Il n’a pas de phase particulière (représentée par la barre jaune).
- En notation d’Euler : `1.e^(i0)` ou `cos(0) + isin(0) = 1`.

