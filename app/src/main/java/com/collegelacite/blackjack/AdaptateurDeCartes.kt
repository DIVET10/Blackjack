package com.collegelacite.blackjack

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.collegelacite.cartes.Carte

class AdaptateurDeCartes(private val context: Activity, private val cartes: ArrayList<Carte>)
    : ArrayAdapter<Carte>(context, R.layout.carte_listview, cartes) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.carte_listview, null, true)

        val imageView = rowView.findViewById(R.id.imageView) as ImageView

        var carte = cartes[position]
        carte.intoImageView(imageView, context)

        return rowView
    }
}