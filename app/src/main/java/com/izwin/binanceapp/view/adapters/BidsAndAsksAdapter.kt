package com.izwin.binanceapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izwin.binanceapp.R
import kotlinx.android.synthetic.main.bid_item.view.*

class BidsAndAsksAdapter(private val layout: Int, private val slotInfoList: ArrayList<List<String>>) :
    RecyclerView.Adapter<BidsAndAsksAdapter.BinanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinanceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return BinanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: BinanceViewHolder, position: Int) {
        if (position % 2 == 0) holder.itemView.setBackgroundResource(R.color.foreground)
        else holder.itemView.setBackgroundResource(R.color.backround)

        val slot = slotInfoList[position]

        val price = slot[0].toDouble()
        val amount = slot[1].toDouble()
        val total = price * amount

        holder.amount.text = "%.6f".format(amount)
        holder.price.text = "%.6f".format(price)
        holder.total.text = "%.6f".format(total)

    }

    override fun getItemCount() = slotInfoList.size

    fun editList(list: List<List<String>>) {
        slotInfoList.clear()
        slotInfoList.addAll(list)
        notifyDataSetChanged()
        }


    class BinanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val total = itemView.total
        val amount = itemView.amount
        val price = itemView.price
    }
}