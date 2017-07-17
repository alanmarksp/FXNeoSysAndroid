package com.alanmarksp.fxneosys.views.main

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Trader
import com.alanmarksp.fxneosys.presenters.ManageProfilePresenter
import com.alanmarksp.fxneosys.retrofit.repositories.TraderRepository
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditProfileFragment : Fragment() {

    private var router: Router? = null
    private var fragmentEditProfile: View? = null
    private var emailEditText: TextInputEditText? = null
    private var firstNameEditText: TextInputEditText? = null
    private var lastNameEditText: TextInputEditText? = null
    private var trader: Trader? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentEditProfile = inflater!!.inflate(R.layout.fragment_edit_profile, container, false)
        initFragment()
        getProfile()
        return fragmentEditProfile
    }

    private fun initFragment() {
        val appCompatActivity = activity as AppCompatActivity
        setHasOptionsMenu(true)
        val toolbar: Toolbar? = fragmentEditProfile?.findViewById(R.id.toolbar)
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val editProfileFab: FloatingActionButton? = fragmentEditProfile?.findViewById(R.id.edit_profile_fab)
        editProfileFab?.let { setEditProfileFabListeners(it) }
        emailEditText = fragmentEditProfile?.findViewById<TextInputEditText>(R.id.edit_profile_email_text_input_edit_text)
        firstNameEditText = fragmentEditProfile?.findViewById<TextInputEditText>(R.id.edit_profile_first_name_text_input_edit_text)
        lastNameEditText = fragmentEditProfile?.findViewById<TextInputEditText>(R.id.edit_profile_last_name_text_input_edit_text)
    }

    private fun setEditProfileFabListeners(editProfileFab: FloatingActionButton) {
        editProfileFab.setOnClickListener {
            trader?.email = emailEditText?.text.toString()
            trader?.first_name = firstNameEditText?.text.toString()
            trader?.last_name = lastNameEditText?.text.toString()
            updateProfile(trader!!)
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
        this.trader = trader
        emailEditText?.setText(trader.email)
        firstNameEditText?.setText(trader.first_name)
        lastNameEditText?.setText(trader.last_name)
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

    private fun updateProfile(trader: Trader) {
        val manageProfilePresenter = ManageProfilePresenter()
        manageProfilePresenter.setTraderRepository(TraderRepository())
        manageProfilePresenter
                .updateProfile(trader)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { updateProfileSuccess() }
                )
    }

    private fun updateProfileSuccess() {
        activity.supportFragmentManager
                .popBackStack()
    }

    companion object {

        fun newInstance(router: Router): EditProfileFragment {
            val fragment = EditProfileFragment()
            fragment.router = router
            return fragment
        }
    }
}
