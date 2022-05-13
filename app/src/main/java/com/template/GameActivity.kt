package com.template

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.template.databinding.ActivityFinishBinding.inflate
import com.template.databinding.ActivityGameBinding
import com.template.databinding.ItemCardBinding

@SuppressLint("StaticFieldLeak")
lateinit var grid: GridLayout
lateinit var gameBinding: ActivityGameBinding

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameBinding = ActivityGameBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        grid = findViewById<GridLayout>(R.id.game_grid)
        val level = intent.getIntExtra("level", 0)
        setGame(level)
    }

    private fun setGame(level: Int) {
        when (level) {
            0 -> {
                startGame(2, 4)
            }
            1 -> {
                startGame(4, 16)
            }
            2 -> {
                startGame(6, 36)
            }
            3 -> {
                startGame(8, 64)
            }
        }
    }

    private fun startGame(gridSet: Int, cardSet: Int) {
        val gridSettings = GridLayout.LayoutParams()
        if(gridSet == 2 || gridSet ==4 ){
            grid.rowCount = gridSet
            grid.columnCount = gridSet
        } else if (gridSet == 6) {
            grid.rowCount = 4
            grid.columnCount = 9
        } else {
            grid.rowCount = 4
            grid.columnCount = 16
        }

        //val cardSets =
        (0 until cardSet).map {
            val setCard = ItemCardBinding.inflate(layoutInflater)
            setCard.root.id = View.generateViewId()
            setCard.itemCardOpened.text = resources.getString(R.string.opened_emoji)
            setCard.itemCardHidden.text = resources.getString(R.string.hidden_emoji)
            setCard.itemCardOpened.visibility = View.GONE
            setCard.root.tag = it
            gridSettings.setGravity(Gravity.CENTER)
            setCard.root.setOnClickListener { view -> cardSelected(view) }
            grid.addView(setCard.root)
            setCard
        }

    }

    private fun cardSelected(view: View?) {
        val hidden = view?.findViewById<TextView>(R.id.itemCardHidden)
        val opened = view?.findViewById<TextView>(R.id.itemCardOpened)
        val openedTag = opened?.tag

        if (hidden?.visibility == View.VISIBLE) {
            hidden.visibility = View.GONE
            opened?.visibility = View.VISIBLE
        } else {
            hidden?.visibility = View.VISIBLE
            opened?.visibility = View.GONE
        }
    }
}