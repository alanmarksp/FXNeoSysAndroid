package com.alanmarksp.fxneosys.views.main

import android.content.res.Configuration
import android.os.Bundle
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
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router


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
