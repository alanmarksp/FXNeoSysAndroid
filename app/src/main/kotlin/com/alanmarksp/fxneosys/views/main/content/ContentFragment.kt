package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.utils.Constants.ROUTES
import com.alanmarksp.fxneosys.views.Router

class ContentFragment : Fragment(), Router {

    private var router: Router? = null
    private var fragmentContent: View? = null
    private var bottomNavigation: BottomNavigationView? = null
    private val routes: HashMap<String, Fragment> = HashMap()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentContent = inflater!!.inflate(R.layout.fragment_content, container, false)
        initFragment()
        return fragmentContent
    }

    private fun initFragment() {
        bottomNavigation = fragmentContent?.findViewById(R.id.bottom_navigation)
        bottomNavigation?.let { setListeners(it) }
        val positionsFragment: PositionsFragment = PositionsFragment.newInstance(this)
        val quotesFragment: QuotesFragment = QuotesFragment.newInstance(this)
        val pendingOrdersFragment: PendingOrdersFragment = PendingOrdersFragment.newInstance(this)
        val ordersFragment: OrdersFragment = OrdersFragment.newInstance(this)
        val tradingAccountFragment: TradingAccountFragment = TradingAccountFragment.newInstance(this)
        routes[ROUTES.POSITIONS] = positionsFragment
        routes[ROUTES.QUOTES] = quotesFragment
        routes[ROUTES.PENDING_ORDERS] = pendingOrdersFragment
        routes[ROUTES.ORDERS] = ordersFragment
        routes[ROUTES.TRADING_ACCOUNT] = tradingAccountFragment
        activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.dashboard_content_fragment_container, quotesFragment)
                .commit()
    }

    private fun setListeners(bottomNavigation: BottomNavigationView) {
        bottomNavigation.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.action_quotes -> {
                            navigate(ROUTES.QUOTES)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.action_positions -> {
                            navigate(ROUTES.POSITIONS)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.action_pending_orders -> {
                            navigate(ROUTES.PENDING_ORDERS)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.action_orders -> {
                            navigate(ROUTES.ORDERS)
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.action_account -> {
                            navigate(ROUTES.TRADING_ACCOUNT)
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                }
        )
    }

    override fun navigate(route: String) {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.dashboard_content_fragment_container, routes[route])
                .commit()
    }

    companion object {

        fun newInstance(router: Router): ContentFragment {
            val fragment = ContentFragment()
            fragment.router = router
            return fragment
        }
    }
}
