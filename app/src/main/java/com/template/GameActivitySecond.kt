package com.template

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.template.adapter.CardAdapter
import com.template.adapter.CardClickListener
import com.template.adapter.RecyclerClickListener
import kotlin.collections.ArrayList
import com.template.databinding.ActivityGameSecondBinding
import java.util.*
import kotlin.random.Random

lateinit var cards: ArrayList<CardSettings>
private var emojies = GetEmoji().getEmoji()
private var visibleCards = arrayListOf<Int>()
lateinit var lm: GridLayoutManager
lateinit var adapter: CardAdapter
lateinit var gameSecondBinding: ActivityGameSecondBinding
lateinit var time: Chronometer
private var level: Int = 0

class GameActivitySecond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameSecondBinding =
            ActivityGameSecondBinding.inflate(layoutInflater).also { setContentView(it.root) }
        time = findViewById<Chronometer>(R.id.chronometer)
        level = intent.getIntExtra("level", 0)
        cards = arrayListOf<CardSettings>()
        initGame()
        var clickable = true


        gameSecondBinding.gameRecycler.addOnItemTouchListener(RecyclerClickListener(
            gameSecondBinding.gameRecycler, object : CardClickListener {
                override fun cardClick(view: View, position: Int) {
                    Log.d("SYSTEMALARM", "${time.text}")
                    if (clickable) {
                        if (!cards[position].cardIsVisible) {
                            cards[position].cardIsVisible = true
                            visibleCards.add(position)
                            val itemOne = visibleCards[0]
                            gameSecondBinding.gameRecycler.adapter?.notifyItemChanged(position)
                            if (visibleCards.size == 2) {
                                val secondItem = visibleCards[1]
                                clickable = false
                                Handler().postDelayed({
                                    if (cards[itemOne].cardEmoji == cards[secondItem].cardEmoji) {
                                        cards[itemOne].cardIsMatch = true
                                        cards[secondItem].cardIsMatch = true
                                    } else {
                                        cards[itemOne].cardIsVisible = false
                                        cards[secondItem].cardIsVisible = false
                                    }
                                    adapter.notifyDataSetChanged()
                                    visibleCards.clear()
                                    clickable = true
                                    checkGameEnd()
                                }, 800L)
                            }
                        }
                    }
                }
            }))
    }

    private fun initGame() {
        setGrid()
        showEmoji()
        startTime()
    }

    private fun startTime() {
        time.base = SystemClock.elapsedRealtime()
        time.start()
    }

    private fun showEmoji() {
        for ((x, i) in cards.withIndex()) {
            cards[x].cardIsVisible = true
            adapter.notifyItemChanged(x)

        }

        Handler().postDelayed({
            for ((x, i) in cards.withIndex()) {
                cards[x].cardIsVisible = false
                adapter.notifyItemChanged(x)
            }
        }, 3000)

    }

    private fun setGrid() {
        when (level) {
            0 -> {
                lm = GridLayoutManager(this, 2)
                setCard(4)
            }
            1 -> {
                lm = GridLayoutManager(this, 4)
                setCard(16)
            }
            2 -> {
                lm = GridLayoutManager(this, 9)
                setCard(36)
            }
            3 -> {
                lm = GridLayoutManager(this, 16)
                setCard(64)
            }
        }
    }

    private fun setCard(cardSet: Int) {
        var pair = 0
        while (pair < cardSet) {
            val randImg = emojies[Random.nextInt(emojies.size)].emoji
            if (countOf(CardSettings(randImg)) < 2) {
                cards.add(CardSettings(randImg))
                cards.add(CardSettings(randImg))
                pair += 2
            }
        }

        cards.shuffle()
        adapter = CardAdapter(this, cards)
        gameSecondBinding.gameRecycler.setHasFixedSize(true)
        gameSecondBinding.gameRecycler.layoutManager = lm
        gameSecondBinding.gameRecycler.adapter = adapter
    }

    private fun countOf(item: CardSettings): Int {
        return Collections.frequency(cards, item)
    }

    fun checkGameEnd() {
        val lastTime = time.text
        var i = 0
        for (q in cards) {
            if (cards[i].cardIsMatch) i++
        }

        if (i == cards.size) {
            showFinishDialog(lastTime.toString())
            time.stop()
            cards.clear()
        }
    }

    private fun showFinishDialog(time: String) {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.finish_dialog)

        val timeTxt = dialog.findViewById<TextView>(R.id.finishTimeTxt)
        val homeBtn = dialog.findViewById<ImageButton>(R.id.finishBtnHome)
        val restartBtn = dialog.findViewById<ImageButton>(R.id.finishBtnRestart)

        timeTxt.text = time

        homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            dialog.cancel()
        }
        restartBtn.setOnClickListener {
            initGame()
            dialog.cancel()
        }
        dialog.show()

    }

    override fun onBackPressed() {
        cards.clear()
        finish()
    }


}

