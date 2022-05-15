package com.template.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.template.R
import com.template.StageSettings

class LevelAdapter(private val context: Context, private val dataset: ArrayList<StageSettings>) :
    RecyclerView.Adapter<LevelAdapter.LevelViewHolder>() {
    class LevelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val level: TextView = view.findViewById(R.id.levelCardItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.level_button, parent, false)
        return LevelViewHolder(view)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        val item = dataset[position]
        holder.level.text = item.level.toString()
        if (!item.isCompleted) {
            holder.level.background =
                ContextCompat.getDrawable(context, R.color.level_card_background)
        } else {
            holder.level.background =
                ContextCompat.getDrawable(context, R.color.level_done_card_background)
        }
    }

    override fun getItemCount(): Int = dataset.size
}