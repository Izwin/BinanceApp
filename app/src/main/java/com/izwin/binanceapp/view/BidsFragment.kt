package com.izwin.binanceapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.izwin.binanceapp.R
import com.izwin.binanceapp.view.adapters.BidsAndAsksAdapter
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener
import com.izwin.binanceapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bids.*
import kotlin.math.log


class BidsFragment : SwipeableFragment(R.layout.fragment_bids) {
    val viewmodel by viewModels<MainViewModel>()
    private val adapter = BidsAndAsksAdapter(R.layout.bid_item, ArrayList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rec_view.setOnTouchListener(swipeListener)

        rec_view.layoutManager = LinearLayoutManager(requireContext())
        rec_view.adapter = adapter

        viewmodel.bidsAndAsksModel.observe(requireActivity()){
            adapter.editList(it.bids)
        }
        viewmodel.isLoading.observe(requireActivity()){
            progress_bar?.isVisible = it
        }
        viewmodel.startRequestSending(1500)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val symbol = resources.getStringArray(R.array.drop_down_list)[position].replace("/","")
                viewmodel.changeSymbol(symbol)
            }
        }
    }

    override fun onSwipeLeft() {
        findNavController().navigate(R.id.action_bidsFragment_to_asksFragment)
    }

    override fun onSwipeRight() {
    }

    override fun onPause() {
        viewmodel.stopRequestSending()
        super.onPause()
    }
}