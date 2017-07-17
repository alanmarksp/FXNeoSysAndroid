package com.alanmarksp.fxneosys.views.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.presenters.ManageProfilePresenter
import com.alanmarksp.fxneosys.retrofit.repositories.TraderRepository
import com.alanmarksp.fxneosys.utils.Constants
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ProfileFragment : Fragment() {

    private var router: Router? = null
    private var fragmentProfile: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentProfile = inflater!!.inflate(R.layout.fragment_profile, container, false)
        initFragment()
        getProfile()
        return fragmentProfile
    }

    private fun initFragment() {
        val appCompatActivity = activity as AppCompatActivity
        setHasOptionsMenu(true)
        val toolbar: Toolbar? = fragmentProfile?.findViewById(R.id.toolbar)
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val profileFab: FloatingActionButton? = fragmentProfile?.findViewById(R.id.profile_fab)
        profileFab?.let { setProfileFabListeners(it) }
    }

    private fun setProfileFabListeners(profileFab: FloatingActionButton) {
        profileFab.setOnClickListener {
            router?.navigate(Constants.ROUTES.EDIT_PROFILE)
        }
    }

    private fun getProfile() {
        val manageProfilePresenter = ManageProfilePresenter()
        manageProfilePresenter.setTraderRepository(TraderRepository())
        manageProfilePresenter
                .getProfile()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { trader -> getProfileSuccess(trader) }
                )
    }

    private fun getProfileSuccess(trader: Trader) {
        val usernameTextView = fragmentProfile?.findViewById<TextView>(R.id.profile_username_text_view)
        val emailTextView = fragmentProfile?.findViewById<TextView>(R.id.profile_email_text_view)
        val firstNameTextView = fragmentProfile?.findViewById<TextView>(R.id.profile_first_name_text_view)
        val lastNameTextView = fragmentProfile?.findViewById<TextView>(R.id.profile_last_name_text_view)
        usernameTextView?.text = trader.username
        emailTextView?.text = trader.email
        firstNameTextView?.text = trader.first_name
        lastNameTextView?.text = trader.last_name
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
