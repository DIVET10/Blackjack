package com.collegelacite.blackjack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.collegelacite.blackjack.databinding.ActivityMainBinding
import com.collegelacite.cartes.Carte
import com.collegelacite.cartes.PaquetDeCartes

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var paquet: PaquetDeCartes
    private var cartesEnMain: ArrayList<Carte> = ArrayList<Carte>();

    private lateinit var adaptateur: AdaptateurDeCartes

    private var partieTerminée = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        paquet = PaquetDeCartes()

        adaptateur = AdaptateurDeCartes(this, cartesEnMain)
        binding.listView.adapter = adaptateur
    }

    public fun dealOnClick(view : View) {
        if (!partieTerminée) {
            Toast.makeText(this, "Terminer la partie en cours", Toast.LENGTH_SHORT).show()
            return;
        }

        cartesEnMain.clear()
        adaptateur.notifyDataSetChanged()

        binding.parieurTextView.text = "0"
        binding.maisonTextView.text = "0"

        partieTerminée = false;

        hitOnClick(view)
    }

    public fun hitOnClick(view : View) {
        if (partieTerminée) {
            Toast.makeText(this, "La partie est terminée", Toast.LENGTH_SHORT).show()
            return;
        }

        if (pigerCarte()) {
            binding.parieurTextView.text = "" + pointage

            if (pointage > 21) {
                Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show()

                partieTerminée = true
            }
        }
    }

    public fun stayOnClick(view : View) {
        if (partieTerminée) {
            Toast.makeText(this, "La partie est terminée", Toast.LENGTH_SHORT).show()
            return;
        }

        val pointageParieur = pointage

        cartesEnMain.clear()
        adaptateur.notifyDataSetChanged()

        while (pointage < 17) {
            if (!pigerCarte()) break;
        }

        binding.maisonTextView.text = "" + pointage

        var maisonGagnante = (pointage == 21) || (pointage in pointageParieur..20)

        if (maisonGagnante)
            Toast.makeText(this, "Vous avez perdu", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this, "Vous avez gagné", Toast.LENGTH_SHORT).show()

        partieTerminée = true
    }

    private fun pigerCarte() : Boolean {
        var c = paquet.piger();
        if (c != null) {
            cartesEnMain.add(c)
            adaptateur.notifyDataSetChanged()

            binding.listView.setSelection(adaptateur.count - 1)

            return true
        }
        else
            return false
    }

    val pointage: Int
        get()  {
            var points = mutableListOf<Int>();
            for (carte in cartesEnMain) {
                val v = when (carte.valeur) {
                    in 2..10 -> carte.valeur
                    in 11..13 -> 10
                    14 -> 1
                    else -> 0
                }

               points.add(v)
            }

            if (points.sum() <= 21 && points.size == 5)
                return 21

            // Il ne faut pas excéder 21
            if (points.sum() > 21)
                return points.sum();

            Log.i("TOTO", "" + points.toString())
            // Transformer les As de 1 à 11 sans excéder 21
            while (points.contains(1)) {
                if (points.sum() + 10 <= 21) {
                    points.remove(1);
                    points.add(11)
                }
                else
                    break
            }

            return points.sum()
        }
}