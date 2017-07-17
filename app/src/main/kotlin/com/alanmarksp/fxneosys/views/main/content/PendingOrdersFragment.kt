package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class PendingOrdersFragment : Fragment() {

    private var router: Router? = null
    private var fragmentPendingOrders: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentPendingOrders = inflater!!.inflate(R.layout.fragment_pending_orders, container, false)
        return fragmentPendingOrders
    }

    companion object {

        fun newInstance(router: Router): PendingOrdersFragment {
            val fragment = PendingOrdersFragment()
            fragment.router = router
            return fragment
        }
    }
}
