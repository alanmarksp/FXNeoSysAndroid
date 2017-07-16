package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class TradingAccountFragment : Fragment() {

    private var router: Router? = null
    private var fragmentTradingAccount: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentTradingAccount = inflater!!.inflate(R.layout.fragment_trading_account, container, false)
        return fragmentTradingAccount
    }

    companion object {

        fun newInstance(router: Router): TradingAccountFragment {
            val fragment = TradingAccountFragment()
            fragment.router = router
            return fragment
        }
    }
}
