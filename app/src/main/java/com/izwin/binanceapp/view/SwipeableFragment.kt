package com.izwin.binanceapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener

abstract class SwipeableFragment(fragmentAsks: Int) : Fragment(fragmentAsks) {

    private val swipeListener = object : OnSwipeTouchListener(requireContext()){
        override fun onSwipeLeft() {
            super.onSwipeRight()
            onSwipeLeft()
        }

        override fun onSwipeRight() {
            super.onSwipeRight()
            onSwipeRight()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.setOnTouchListener(swipeListener)
    }

    abstract fun onSwipeLeft()

    abstract fun onSwipeRight()
}