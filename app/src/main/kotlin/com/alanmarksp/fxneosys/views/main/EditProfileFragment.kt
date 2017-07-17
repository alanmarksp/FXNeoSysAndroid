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
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class EditProfileFragment : Fragment() {

    private var router: Router? = null
    private var fragmentEditProfile: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentEditProfile = inflater!!.inflate(R.layout.fragment_edit_profile, container, false)
        initFragment()
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
    }

    private fun setEditProfileFabListeners(editProfileFab: FloatingActionButton) {
        editProfileFab.setOnClickListener {
            activity.supportFragmentManager
                    .popBackStack()
        }
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

        fun newInstance(router: Router): EditProfileFragment {
            val fragment = EditProfileFragment()
            fragment.router = router
            return fragment
        }
    }
}
