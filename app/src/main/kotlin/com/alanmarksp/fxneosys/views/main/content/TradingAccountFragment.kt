package com.alanmarksp.fxneosys.views.main.content

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.TradingAccount
import com.alanmarksp.fxneosys.presenters.ShowDashboardPresenter
import com.alanmarksp.fxneosys.retrofit.repositories.TradingAccountRepository
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TradingAccountFragment : Fragment() {

    private var router: Router? = null
    private var fragmentTradingAccount: View? = null
    private var tradingAccountLinearLayout: LinearLayoutCompat? = null
    private var idTextView: TextView? = null
    private var fundsTextView: TextView? = null
    private var realizedProfitTextView: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentTradingAccount = inflater!!.inflate(R.layout.fragment_trading_account, container, false)
        tradingAccountLinearLayout = fragmentTradingAccount
                ?.findViewById(R.id.trading_account_linear_layout)
        idTextView = fragmentTradingAccount
                ?.findViewById(R.id.trading_account_id_text_view)
        fundsTextView = fragmentTradingAccount
                ?.findViewById(R.id.trading_account_funds_text_view)
        realizedProfitTextView = fragmentTradingAccount
                ?.findViewById(R.id.trading_account_realized_profit_text_view)
        return fragmentTradingAccount
    }

    override fun onResume() {
        super.onResume()
        val showDashboardPresenter: ShowDashboardPresenter = ShowDashboardPresenter()
        showDashboardPresenter.setTradingAccountRepository(TradingAccountRepository())
        showDashboardPresenter
                .getSelectedTradingAccount()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        { tradingAccount -> getSelectedTradingAccountSuccess(tradingAccount) }
                )
    }

    override fun onPause() {
        super.onPause()
        tradingAccountLinearLayout?.visibility = View.GONE
    }

    private fun getSelectedTradingAccountSuccess(tradingAccount: TradingAccount) {
        idTextView?.text = String.format("%09d", tradingAccount.id)
        fundsTextView?.text = String.format("%.2f", tradingAccount.funds)
        realizedProfitTextView?.text = String.format("%.2f", tradingAccount.realized_profit)
        tradingAccountLinearLayout?.visibility = View.VISIBLE
    }

    companion object {

        fun newInstance(router: Router): TradingAccountFragment {
            val fragment = TradingAccountFragment()
            fragment.router = router
            return fragment
        }
    }
}
