package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class QuotesFragment : Fragment() {

    private var router: Router? = null
    private var fragmentQuotes: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentQuotes = inflater!!.inflate(R.layout.fragment_quotes, container, false)
        return fragmentQuotes
    }

    companion object {

        fun newInstance(router: Router): QuotesFragment {
            val fragment = QuotesFragment()
            fragment.router = router
            return fragment
        }
    }
}
