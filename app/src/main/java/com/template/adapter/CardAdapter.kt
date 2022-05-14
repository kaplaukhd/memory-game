package com.template.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.template.CardSettings
import com.template.R

class CardAdapter(
    private val context: Context,
    private val dataset: ArrayList<CardSettings>
) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = dataset[position]

        if (!item.cardIsVisible) {
            holder.card.background =
                ContextCompat.getDrawable(context, R.color.card_background_hidden)
            holder.card.text = context.getString(R.string.hidden_emoji)
        } else {
            holder.card.background =
                ContextCompat.getDrawable(context, R.color.card_background_opened)
            holder.card.text = context.getString(item.cardEmoji)
        }
        if (item.cardIsMatch) {
            holder.card.visibility = View.INVISIBLE
            holder.card.isEnabled = false
        }
    }

    override fun getItemCount(): Int = dataset.size

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card = view.findViewById<TextView>(R.id.itemCardHidden)
    }


}