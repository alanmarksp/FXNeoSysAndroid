package com.alanmarksp.fxneosys.views.authentication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.presenters.AuthenticatePresenter
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginFragment : Fragment() {

    private var router: Router? = null
    private var fragmentLogin: View? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentLogin = inflater!!.inflate(R.layout.fragment_login, container, false)
        fragmentLogin?.let { setListeners(it) }
        return fragmentLogin
    }

    private fun setListeners(container: View) {
        val loginButton = container.findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener { login() }
        val registerTextView = container.findViewById<TextView>(R.id.register_text_view)
        registerTextView.setOnClickListener { register() }
    }

    private fun login() {
        val loginUsernameEditText = fragmentLogin
                ?.findViewById<EditText>(R.id.login_username_text_input_edit_text)
        val loginUsername: String? = loginUsernameEditText?.text.toString()
        val loginPasswordEditText = fragmentLogin
                ?.findViewById<EditText>(R.id.login_password_text_input_edit_text)
        val loginPassword: String? = loginPasswordEditText?.text.toString()
        if (loginUsername != null && loginPassword != null) {
            val authenticationPresenter = AuthenticatePresenter()
            authenticationPresenter
                    .login(Authentication(loginUsername, loginPassword))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { response -> loginSuccess(response) },
                            { error -> loginFailure(error) }
                    )
        }
    }

    private fun loginSuccess(response: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun loginFailure(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun register() {
        router?.navigate("register")
    }

    companion object {

        fun newInstance(router: Router): LoginFragment {
            val fragment = LoginFragment()
            fragment.router = router
            return fragment
        }
    }
}
