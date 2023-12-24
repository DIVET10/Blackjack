package com.collegelacite.cartes

import android.content.Context
import android.graphics.drawable.Drawable

import android.widget.ImageView




// Classe implantant une carte à jouer
class Carte(val valeur: Int, val famille: Famille) {
    // Familles de cartes
    enum class Famille {
        Pique, Coeur, Carreau, Trèfle
    };

    // Retourne une chaîne décrivant textuellement la carte
    var descriptionTextuelle: String = ""
        get() {
            var s: String // chaîne construite
            s = when (valeur) {
                2 -> "Deux"
                3 -> "Trois"
                4 -> "Quatre"
                5 -> "Cinq"
                6 -> "Six"
                7 -> "Sept"
                8 -> "Huit"
                9 -> "Neuf"
                10 -> "Dix"
                11 -> "Valet"
                12 -> "Dame"
                13 -> "Roi"
                14 -> "As"
                else -> Integer.toString(valeur) // ne devrait jamais se produire
            }

            // On y ajoute ensuite la famille
            s += " de "
            s += when (famille) {
                Famille.Pique -> " Pique"
                Famille.Coeur -> " Coeur"
                Famille.Carreau -> " Carreau"
                Famille.Trèfle -> " Trèfle"
                else -> " ???" // ça ne devrait pas se produire!
            }
            return s
        }

    // Retourne une chaîne décrivant la carte de façon abrégée
    var descriptionAbrégée: String = ""
        get() {
            var s: String // chaîne construite

            // On commence par la valeur de la carte
            s = if (valeur == 11) "V" else if (valeur == 12) "D" else if (valeur == 13) "R" else if (valeur == 14) "A" else Integer.toString(valeur)
            when (famille) {
                Famille.Pique -> s += '\u2660'
                Famille.Coeur -> s += '\u2764'
                Famille.Carreau -> s += '\u2666'
                Famille.Trèfle -> s += '\u2663'
                else -> s += " ???" // ça ne devrait pas se produire!
            }
            return s
        }

    // Retourne le nom de l'image ressource correspondant à la carte dans drawable
    var nomImage: String = ""
        get(){
            var s: String
            when (famille) {
                Famille.Pique -> s = "pique"
                Famille.Coeur -> s = "coeur"
                Famille.Carreau -> s = "carreau"
                Famille.Trèfle -> s = "trefle"
                else -> s = "???" // ça ne devrait pas se produire!
            }

            return s + "_" + Integer.toString(valeur)
        }

    // Retourne une chaîne décrivant la carte
    override fun toString(): String {
        return descriptionTextuelle + " (" + descriptionAbrégée + ")"
    }

    // Fonction permettant d'insérer l'image de la carte dans un
    // ImageView fourni. L'image doit être dans res/drawable.
    fun intoImageView(iv: ImageView, contexte: Context) {
        val uri = "@drawable/" + this.nomImage
        val imageResource: Int =
            contexte.getResources().getIdentifier(uri, null, contexte.getPackageName())
        val res: Drawable? = contexte.getDrawable(imageResource)
        iv.setImageDrawable(res)
    }

}