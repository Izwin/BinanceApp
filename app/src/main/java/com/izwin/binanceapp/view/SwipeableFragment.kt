package com.izwin.binanceapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener

abstract class SwipeableFragment(fragmentAsks: Int) : Fragment(fragmentAsks) {

    protected val swipeListener = object : OnSwipeTouchListener(context){
        override fun onSwipeLeft() {
            super.onSwipeLeft()
            this@SwipeableFragment.onSwipeLeft()
        }

        override fun onSwipeRight() {
            super.onSwipeRight()
            this@SwipeableFragment.onSwipeRight()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener(swipeListener)
    }


    abstract fun onSwipeLeft()

    abstract fun onSwipeRight()
}