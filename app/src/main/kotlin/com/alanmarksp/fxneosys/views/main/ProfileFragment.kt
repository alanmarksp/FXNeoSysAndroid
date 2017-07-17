package com.alanmarksp.fxneosys.views.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router
import android.support.v4.app.NavUtils
import android.view.MenuItem


class ProfileFragment : Fragment() {

    private var router: Router? = null
    private var fragmentProfile: View? = null
    private var toolbar: Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentProfile = inflater!!.inflate(R.layout.fragment_profile, container, false)
        initFragment()
        return fragmentProfile
    }

    private fun initFragment() {
        val appCompatActivitity = activity as AppCompatActivity
        setHasOptionsMenu(true)
        toolbar = fragmentProfile?.findViewById(R.id.toolbar)
        appCompatActivitity.setSupportActionBar(toolbar)
        appCompatActivitity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity.supportFragmentManager
                        .popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        fun newInstance(router: Router): ProfileFragment {
            val fragment = ProfileFragment()
            fragment.router = router
            return fragment
        }
    }
}
