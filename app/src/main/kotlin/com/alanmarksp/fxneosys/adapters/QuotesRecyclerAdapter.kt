package com.alanmarksp.fxneosys.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Quote

class QuotesRecyclerAdapter(val quotes: List<Quote>): RecyclerView.Adapter<QuotesRecyclerAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.symbolTextView?.text = quotes[position].symbol
        holder?.bidPriceTextView?.text = String.format("%.4f", quotes[position].bid_price)
        holder?.askPriceTextView?.text = String.format("%.4f", quotes[position].ask_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
                .inflate(R.layout.view_quote, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var symbolTextView: TextView? = null
        var bidPriceTextView: TextView? = null
        var askPriceTextView: TextView? = null

        init {
            symbolTextView = itemView?.findViewById(R.id.quote_symbol_text_view)
            bidPriceTextView = itemView?.findViewById(R.id.quote_bid_price_text_view)
            askPriceTextView = itemView?.findViewById(R.id.quote_ask_price_text_view)
        }

    }
}