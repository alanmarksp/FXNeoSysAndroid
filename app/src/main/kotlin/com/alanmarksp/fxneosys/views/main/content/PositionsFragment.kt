package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.views.Router

class PositionsFragment : Fragment() {

    private var router: Router? = null
    private var fragmentPositions: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentPositions = inflater!!.inflate(R.layout.fragment_positions, container, false)
        return fragmentPositions
    }

    companion object {

        fun newInstance(router: Router): PositionsFragment {
            val fragment = PositionsFragment()
            fragment.router = router
            return fragment
        }
    }
}
