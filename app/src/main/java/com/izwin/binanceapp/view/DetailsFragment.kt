package com.izwin.binanceapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.izwin.binanceapp.R
import com.izwin.binanceapp.model.BidsAndAsksModel
import com.izwin.binanceapp.view.adapters.DiffAdapter
import com.izwin.binanceapp.view.listeners.OnSwipeTouchListener
import com.izwin.binanceapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_bids.*

class DetailsFragment : SwipeableFragment(R.layout.fragment_details) {
    val viewmodel by viewModels<MainViewModel>()
    private val adapter = DiffAdapter(R.layout.diff_item, BidsAndAsksModel(listOf(), listOf()))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rec_view.layoutManager = LinearLayoutManager(requireContext())
        rec_view.adapter = adapter

        viewmodel.bidsAndAsksModel.observe(requireActivity()){
            adapter.editBidsAndAsks(it)
        }
        viewmodel.isLoading.observe(requireActivity()){
            progress_bar?.isVisible = it
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val symbol = resources.getStringArray(R.array.drop_down_list)[position].replace("/","")
                viewmodel.changeSymbol(symbol)
            }
        }
        viewmodel.startRequestSending(1500)
    }

    override fun onSwipeLeft() {
        findNavController().navigate(R.id.action_asksFragment_to_detailsFragment)
    }

    override fun onSwipeRight() {
        findNavController().navigate(R.id.action_asksFragment_to_bidsFragment)
    }

    override fun onPause() {
        viewmodel.stopRequestSending()
        super.onPause()
    }
}
