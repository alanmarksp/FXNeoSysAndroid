package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router
import com.alanmarksp.fxneosys.views.authentication.LoginFragment

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ContentFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContentFragment : Fragment() {

    private var router: Router? = null
    private var fragmentContent: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentContent = inflater!!.inflate(R.layout.fragment_content, container, false)
        return fragmentContent
    }

    companion object {

        fun newInstance(router: Router): ContentFragment {
            val fragment = ContentFragment()
            fragment.router = router
            return fragment
        }
    }
}
