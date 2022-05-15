package com.template

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.LinearLayout
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

@SuppressLint("StaticFieldLeak")
lateinit var adapter: CardAdapter
lateinit var gameSecondBinding: ActivityGameSecondBinding

@SuppressLint("StaticFieldLeak")
lateinit var time: Chronometer
private var level: Int = 0
private var currentLevel: Int = -1

class GameActivitySecond : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameSecondBinding =
            ActivityGameSecondBinding.inflate(layoutInflater).also { setContentView(it.root) }
        checkStage()
        level = if (checkStage() == -1) {
            intent.getIntExtra("level", 0)
        } else {
            checkStage()
        }
        time = findViewById(R.id.chronometer)
        cards = arrayListOf()
        initGame()
        var clickable = true


        gameSecondBinding.gameRecycler.addOnItemTouchListener(RecyclerClickListener(
            gameSecondBinding.gameRecycler, object : CardClickListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun cardClick(view: View, position: Int) {
                    if (clickable) {
                        if (!cards[position].cardIsVisible) {
                            cards[position].cardIsVisible = true
                            visibleCards.add(position)
                            val itemOne = visibleCards[0]
                            gameSecondBinding.gameRecycler.adapter?.notifyItemChanged(position)
                            if (visibleCards.size == 2) {
                                val secondItem = visibleCards[1]
                                clickable = false
                                Handler(Looper.getMainLooper()).postDelayed({
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

    private fun checkStage(): Int {
        val level = intent.getIntExtra("stageGame", -1)
        val stage = intent.getIntExtra("stageLevel", -1)
        Log.d("SYSTEMALARM", "level: $level , stage: $stage")
        if (level == -1) {
            gameSecondBinding.gameLevelItem.visibility = View.GONE
            gameSecondBinding.gameLevelTxt.visibility = View.GONE
        } else {
            currentLevel = stage
            gameSecondBinding.gameLevelItem.text = currentLevel.toString()
        }
        return level
    }

    private fun initGame() {
        gameSecondBinding.gameLevelItem.text = currentLevel.toString()
        setGrid()
        showEmoji()
        startTime()
    }

    private fun startTime() {
        time.base = SystemClock.elapsedRealtime()
        time.start()
    }

    private fun showEmoji() {
        for ((x) in cards.withIndex()) {
            cards[x].cardIsVisible = true
            adapter.notifyItemChanged(x)

        }

        Handler(Looper.getMainLooper()).postDelayed({
            for ((x) in cards.withIndex()) {
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
        adapter.setHasStableIds(true)
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

        val nextBtn = dialog.findViewById<ImageButton>(R.id.finishNextBtn)
        val nextLl = dialog.findViewById<LinearLayout>(R.id.finishNextLl)
        val result = dialog.findViewById<TextView>(R.id.finishResultTxt)

        if (currentLevel == -1) {
            timeTxt.text = time
            nextLl.visibility = View.GONE
        } else {
            timeTxt.visibility = View.GONE
            result.text = "Level $currentLevel completed"
            nextBtn.setOnClickListener {
                currentLevel++
                level = when {
                    currentLevel < 9 -> {
                        0
                    }
                    currentLevel < 18 -> {
                        1
                    }
                    currentLevel < 27 -> {
                        2
                    }
                    else -> {
                        3
                    }
                }
                initGame()
                dialog.cancel()
            }
        }


        homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            currentLevel = -1
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
        currentLevel = -1
        cards.clear()
        finish()
    }


}

