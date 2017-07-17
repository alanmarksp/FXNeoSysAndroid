package com.alanmarksp.fxneosys.views.main

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.TextView
import android.widget.Toast
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.models.TradingAccount
import com.alanmarksp.fxneosys.presenters.ShowDashboardPresenter
import com.alanmarksp.fxneosys.retrofit.repositories.TraderRepository
import com.alanmarksp.fxneosys.retrofit.repositories.TradingAccountRepository
import com.alanmarksp.fxneosys.utils.Constants.ROUTES
import com.alanmarksp.fxneosys.views.Router
import com.alanmarksp.fxneosys.views.main.content.ContentFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DashboardFragment : Fragment(), Router {

    private var router: Router? = null
    private var fragmentDashboard: View? = null
    private var dashboardDrawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var drawerToggle: ActionBarDrawerToggle? = null
    private var navigationView: NavigationView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentDashboard = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        initFragment()
        getProfile()
        getTradingAccounts()
        return fragmentDashboard
    }

    private fun initFragment() {
        val appCompatActivity = activity as AppCompatActivity
        setHasOptionsMenu(true)
        toolbar = fragmentDashboard?.findViewById(R.id.toolbar)
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initNavigationDrawer()
        val contentFragment: Fragment = ContentFragment.newInstance(this)
        activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.dashboard_fragment_container, contentFragment)
                .commit()
    }

    private fun initNavigationDrawer() {
        dashboardDrawerLayout = fragmentDashboard?.findViewById(R.id.dashboard_drawer_layout)
        drawerToggle = ActionBarDrawerToggle(activity, dashboardDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        dashboardDrawerLayout?.addDrawerListener(drawerToggle!!)
        drawerToggle?.isDrawerIndicatorEnabled = true
        drawerToggle?.syncState()
        navigationView = fragmentDashboard
                ?.findViewById<NavigationView>(R.id.dashboard_menu_navigation_view)
        navigationView?.let { setNavigationViewListeners(it) }
    }

    private fun setNavigationViewListeners(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener(
                NavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.nav_settings -> {
                            navigate(ROUTES.PROFILE)
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                }
        )
    }

    private fun getProfile() {
        val showDashBoardPresenter = ShowDashboardPresenter()
        showDashBoardPresenter.setTraderRepository(TraderRepository())
        showDashBoardPresenter
                .getProfile()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { trader -> getProfileSuccess(trader) }
                )
    }

    private fun getProfileSuccess(trader: Trader) {
        val navHeader = navigationView?.getHeaderView(0)
        val headerUsername = navHeader?.findViewById<TextView>(R.id.header_username)
        val headerEmail = navHeader?.findViewById<TextView>(R.id.header_email)
        headerUsername?.text = trader.username
        headerEmail?.text = trader.email
    }

    private fun getTradingAccounts() {
        val showDashBoardPresenter = ShowDashboardPresenter()
        showDashBoardPresenter.setTradingAccountRepository(TradingAccountRepository())
        showDashBoardPresenter
                .getTradingAccounts()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { tradingAccounts -> getTradingAccountsSuccess(tradingAccounts) },
                        { error -> errorS(error)}
                )
    }

    private fun getTradingAccountsSuccess(tradingAccounts: List<TradingAccount>) {
        val menu = navigationView?.menu
        if (tradingAccounts.isNotEmpty()) {
            tradingAccounts
                    .map { menu?.add(R.id.trading_accounts_group, it.id, Menu.NONE, "Account #${it.id}") }
                    .forEach { it?.icon = ContextCompat.getDrawable(activity, R.drawable.ic_label_outline) }
        }
        else {
            menu?.add(R.id.trading_accounts_group, Menu.NONE, Menu.NONE, "You don't have Accounts")
        }
    }

    private fun errorS(error: Throwable) {
        throw error
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                dashboardDrawerLayout?.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        drawerToggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToggle?.onConfigurationChanged(newConfig)
    }

    companion object {

        fun newInstance(router: Router): DashboardFragment {
            val fragment = DashboardFragment()
            fragment.router = router
            return fragment
        }
    }

    override fun navigate(route: String) {
        router?.navigate(route)
    }
}
