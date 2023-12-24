package com.collegelacite.cartes

import java.util.Collections

// Classe implantant un paquet de cartes à jouer
class PaquetDeCartes {
    // Cartes dans le paquet
    private val cartes = ArrayList<Carte?>()

    // Constructeur par défaut : construit un paquet de 52 cartes
    init {
        for (valeur in 2..14) {
            cartes.add(Carte(valeur, Carte.Famille.Pique))
            cartes.add(Carte(valeur, Carte.Famille.Coeur))
            cartes.add(Carte(valeur, Carte.Famille.Carreau))
            cartes.add(Carte(valeur, Carte.Famille.Trèfle))
        }

        // Les cartes sont brassées
        Collections.shuffle(cartes)
    }

    // Retourne le nombre de cartes dans le paquet
    val taille: Int
        get() = cartes.size

    // Extrait la carte du dessus du paquet et la retourne (null si le paquet est vide)
    fun piger(): Carte? {
        return if (cartes.size > 0) {
            cartes.removeAt(0)
        } else null
    }

}