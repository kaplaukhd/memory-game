package com.template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.template.databinding.ActivityLevelBinding

lateinit var levelBinding: ActivityLevelBinding

class LevelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        levelBinding = ActivityLevelBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}