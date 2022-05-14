package com.template.adapter

import android.view.View

interface CardClickListener {
    fun cardClick(view: View, position: Int){}
}