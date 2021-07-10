package com.izwin.binanceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.izwin.binanceapp.R
import com.izwin.binanceapp.view.adapters.BinanceAdapter
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener
import com.izwin.binanceapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bids.*


class BidsFragment : Fragment(R.layout.fragment_bids) {
    val viewmodel by viewModels<MainViewModel>()

    val adapter = BinanceAdapter(R.layout.bid_item_layout, ArrayList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        constraint_layout.setOnTouchListener(object : OnSwipeTouchListener(requireContext()){
            override fun onSwipeRight() {
                super.onSwipeRight()
            }
        })

        rec_view.layoutManager = LinearLayoutManager(requireContext())
        rec_view.adapter = adapter

        viewmodel.bidsAndAsksModel.observe(requireActivity()){
            progress_bar.isVisible = true
            adapter.editList(it.bids)
            progress_bar.isVisible = false
        }
        viewmodel.startRequestSending(2000)
    }
}