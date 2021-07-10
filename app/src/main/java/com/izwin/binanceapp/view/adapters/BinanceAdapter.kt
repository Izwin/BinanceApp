package com.izwin.binanceapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bid_item_layout.view.*

class BinanceAdapter(val layout: Int,val slotInfoList: ArrayList<List<String>>) :
    RecyclerView.Adapter<BinanceAdapter.BinanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinanceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return BinanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BinanceViewHolder, position: Int) {
        if (position%2==0) holder.itemView.setBackgroundColor(android.graphics.Color.parseColor("#D5D5D5"))
        else{holder.itemView.setBackgroundColor(android.graphics.Color.WHITE)}
        val slot = slotInfoList[position]

        val price = slot[0].toDouble()
        val amount = slot[1].toDouble()
        val total = price * amount

        holder.amount.text = "%.4f".format(amount)
        holder.price.text = "%.5f".format(price)
        holder.total.text = "%.5f".format(total)
    }

    override fun getItemCount() = slotInfoList.size

    fun editList(list: List<List<String>>){
        slotInfoList.clear()
        slotInfoList.addAll(list)
        notifyItemRangeChanged(0,list.size)
    }

    class BinanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val total = itemView.total
        val amount = itemView.amount
        val price = itemView.price
    }
}