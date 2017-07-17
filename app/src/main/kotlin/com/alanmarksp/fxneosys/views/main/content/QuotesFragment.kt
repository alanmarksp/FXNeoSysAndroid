package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.adapters.QuotesRecyclerAdapter
import com.alanmarksp.fxneosys.models.Quote
import com.alanmarksp.fxneosys.views.Router
import android.support.v7.widget.DividerItemDecoration



class QuotesFragment : Fragment() {

    private var router: Router? = null
    private var fragmentQuotes: View? = null
    private var quotesRecyclerView: RecyclerView? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var quotesRecyclerAdapter: QuotesRecyclerAdapter? = null
    private var quotes: MutableList<Quote> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentQuotes = inflater!!.inflate(R.layout.fragment_quotes, container, false)
        initFragment()
        return fragmentQuotes
    }

    private fun initFragment() {
        quotesRecyclerView = fragmentQuotes?.findViewById(R.id.quotes_recycler_view)
        linearLayoutManager = LinearLayoutManager(activity)
        quotesRecyclerView?.layoutManager = linearLayoutManager!!
        quotesRecyclerAdapter = QuotesRecyclerAdapter(quotes)
        quotesRecyclerView?.adapter = quotesRecyclerAdapter
        val dividerItemDecoration = DividerItemDecoration(quotesRecyclerView?.context,
                linearLayoutManager?.orientation!!)
        quotesRecyclerView?.addItemDecoration(dividerItemDecoration)
        quotes.add(Quote("EURUSD", 1.0000, 1.0002))
        quotes.add(Quote("EURUSD", 1.0000, 1.0002))
    }

    companion object {

        fun newInstance(router: Router): QuotesFragment {
            val fragment = QuotesFragment()
            fragment.router = router
            return fragment
        }
    }
}
