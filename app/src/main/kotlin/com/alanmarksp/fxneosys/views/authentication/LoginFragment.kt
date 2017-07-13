package com.alanmarksp.fxneosys.views.authentication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.alanmarksp.fxneosys.R
import com.alanmarksp.fxneosys.local.repositories.TokenRepository
import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.presenters.AuthenticatePresenter
import com.alanmarksp.fxneosys.retrofit.repositories.AuthenticationRepository
import com.alanmarksp.fxneosys.utils.Constants.ROUTES
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException


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
            if (loginUsername != "" && loginPassword != "") {
                performLogin(loginUsername, loginPassword)
            } else {
                val message = getString(R.string.login_empty_fields_message)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin(loginUsername: String, loginPassword: String) {
        val sharePreferences = activity.getSharedPreferences(
                getString(R.string.com_alanmarksp_fxneosys_SHARED_PREFERENCES_KEY),
                Context.MODE_PRIVATE
        )
        val authenticationPresenter = AuthenticatePresenter(
                AuthenticationRepository(),
                TokenRepository(sharePreferences)
        )
        authenticationPresenter
                .login(Authentication(loginUsername, loginPassword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { loginSuccess() },
                        { error -> loginFailure(error) }
                )
    }

    private fun loginSuccess() {
        router?.navigate(ROUTES.MAIN)
    }

    private fun loginFailure(error: Throwable) {
        if (error is HttpException) {
            val message = getString(R.string.login_failure_message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun register() {
        router?.navigate(ROUTES.REGISTER)
    }

    companion object {

        fun newInstance(router: Router): LoginFragment {
            val fragment = LoginFragment()
            fragment.router = router
            return fragment
        }
    }
}
