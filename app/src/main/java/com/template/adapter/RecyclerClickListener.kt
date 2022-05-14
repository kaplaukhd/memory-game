package com.template.adapter

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class RecyclerClickListener(recycler: RecyclerView, private val listener: CardClickListener?) :
    RecyclerView.OnItemTouchListener {
    private var detector: GestureDetector =
        GestureDetector(recycler.context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                Log.d("SYSTEMALARM", "touch event")
                return true
            }
        })

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val cView = rv.findChildViewUnder(e.x, e.y)
        if (cView != null && listener != null && detector.onTouchEvent(e)) {
            listener.cardClick(cView, rv.getChildAdapterPosition(cView))
            return true
        }
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}