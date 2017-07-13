package com.alanmarksp.fxneosys.views.main

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.presenters.ShowDashboardPresenter
import com.alanmarksp.fxneosys.retrofit.repositories.TraderRepository
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DashboardFragment : Fragment() {

    private var router: Router? = null
    private var fragmentDashboard: View? = null
    private var dashboardDrawerLayout: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private var drawerToggle: ActionBarDrawerToggle? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentDashboard = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        initFragment()
        getProfile()
        getTradingAccounts()
        return fragmentDashboard
    }

    private fun initFragment() {
        val appCompatActivitity = activity as AppCompatActivity
        setHasOptionsMenu(true)
        toolbar = fragmentDashboard?.findViewById(R.id.toolbar)
        appCompatActivitity.setSupportActionBar(toolbar)
        appCompatActivitity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        dashboardDrawerLayout = fragmentDashboard?.findViewById(R.id.dashboard_drawer_layout)
        drawerToggle = ActionBarDrawerToggle(activity, dashboardDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
        dashboardDrawerLayout?.addDrawerListener(drawerToggle!!)
        drawerToggle?.isDrawerIndicatorEnabled = true
        drawerToggle?.syncState()
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
        val navigationView = fragmentDashboard
                ?.findViewById<NavigationView>(R.id.dashboard_menu_navigation_view)
        val navHeader = navigationView?.getHeaderView(0)
        val headerUsername = navHeader?.findViewById<TextView>(R.id.header_username)
        val headerEmail = navHeader?.findViewById<TextView>(R.id.header_email)
        headerUsername?.text = trader.username
        headerEmail?.text = trader.email
    }

    private fun getTradingAccounts() {

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
}
