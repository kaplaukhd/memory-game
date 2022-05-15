package com.template

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.util.rangeTo
import androidx.recyclerview.widget.GridLayoutManager
import com.template.adapter.CardClickListener
import com.template.adapter.LevelAdapter
import com.template.adapter.RecyclerClickListener
import com.template.databinding.ActivityLevelBinding
import java.util.logging.Level

lateinit var levelBinding: ActivityLevelBinding

@SuppressLint("StaticFieldLeak")
lateinit var levelAdapter: LevelAdapter
lateinit var layoutManager: GridLayoutManager
lateinit var dataset: ArrayList<StageSettings>

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        levelBinding = ActivityLevelBinding.inflate(layoutInflater).also { setContentView(it.root) }
        dataset = GetStageLevel().getLevel()
        layoutManager = GridLayoutManager(this, 9)
        levelAdapter = LevelAdapter(this, dataset)
        levelBinding.levelRecycler.adapter = levelAdapter

        levelBinding.levelRecycler.addOnItemTouchListener(RecyclerClickListener(levelBinding.levelRecycler,
            object : CardClickListener {
                override fun cardClick(view: View, position: Int) {
                    val intent = Intent(applicationContext, GameActivitySecond::class.java)
                    when {
                        position < 8 -> {
                            intent.putExtra("stageGame", 0)
                            intent.putExtra("stageLevel", position + 1)
                        }
                        position < 18 -> {
                            intent.putExtra("stageGame", 1)
                            intent.putExtra("stageLevel", position + 1)
                        }
                        position < 27 -> {
                            intent.putExtra("stageGame", 2)
                            intent.putExtra("stageLevel", position + 1)
                        }
                        else -> {
                            intent.putExtra("stageGame", 3)
                            intent.putExtra("stageLevel", position + 1)
                        }
                    }
                    startActivity(intent)
                }
            }))
    }

    override fun onBackPressed() {
        finish()
        intent.removeExtra("stageGame")
    }
}