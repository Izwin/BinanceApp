package com.izwin.binanceapp.view.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izwin.binanceapp.R
import com.izwin.binanceapp.model.BidsAndAsksModel
import kotlinx.android.synthetic.main.diff_item.view.*

class DiffAdapter(private val layout: Int, private var bidsAndAsks: BidsAndAsksModel) :
    RecyclerView.Adapter<DiffAdapter.DiffViewHolder>() {

    var lastChanges : BidsAndAsksModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiffViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return DiffViewHolder(view)
    }
    override fun onBindViewHolder(holder: DiffViewHolder, position: Int) {
        if (position % 2 == 0) holder.itemView.setBackgroundResource(R.color.foreground)
        else holder.itemView.setBackgroundResource(R.color.backround)

        val bidPrice = bidsAndAsks.bids[position][0].toDouble()
        val askPrice = bidsAndAsks.asks[position][0].toDouble()

        val lastBidPrice : String? = lastChanges?.bids?.get(position)?.get(0)
        val lastAskPrice : String? = lastChanges?.asks?.get(position)?.get(0)

        val bidDiff : Double? = if (lastBidPrice!=null) bidPrice - lastBidPrice.toDouble() else null
        val askDiff : Double? = if (lastAskPrice!=null) askPrice - lastAskPrice.toDouble() else null

        holder.bid_price.text = "%.6f".format(bidPrice)
        holder.asks_price.text = "%.6f".format(askPrice)
        holder.asks_diff.text = if (askDiff!=null) "%.6f".format(askDiff) else "No Data"
        holder.bid_diff.text = if (bidDiff!=null) "%.6f".format(bidDiff) else "No Data"

        if (bidDiff != null) {
            if (bidDiff>0) holder.bid_diff.setTextColor(holder.itemView.resources.getColor(R.color.green))
            if (bidDiff<0) holder.bid_diff.setTextColor(holder.itemView.resources.getColor(R.color.red))
        }
        if (askDiff != null) {
            if (askDiff>0) holder.asks_diff.setTextColor(holder.itemView.resources.getColor(R.color.green))
            if (askDiff<0) holder.asks_diff.setTextColor(holder.itemView.resources.getColor(R.color.red))
        }
    }

    override fun getItemCount() = bidsAndAsks.bids.size // Bids And Asks lists size are equal

    fun editBidsAndAsks(bidsAndAsksModel: BidsAndAsksModel){
        lastChanges = if(this.bidsAndAsks.bids.isNotEmpty()) this.bidsAndAsks else null
        this.bidsAndAsks = bidsAndAsksModel
        notifyDataSetChanged()
    }

    class DiffViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bid_price = itemView.bid_price
        val bid_diff = itemView.bid_diff
        val asks_price = itemView.ask_price
        val asks_diff = itemView.ask_diff
    }
}