package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class OrdersFragment : Fragment() {

    private var router: Router? = null
    private var fragmentOrders: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentOrders = inflater!!.inflate(R.layout.fragment_orders, container, false)
        return fragmentOrders
    }

    companion object {

        fun newInstance(router: Router): OrdersFragment {
            val fragment = OrdersFragment()
            fragment.router = router
            return fragment
        }
    }
}
