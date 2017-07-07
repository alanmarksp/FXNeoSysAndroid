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
import com.alanmarksp.fxneosys.models.Authentication
import com.alanmarksp.fxneosys.presenters.AuthenticatePresenter
import com.alanmarksp.fxneosys.retrofit.repositories.AuthenticationRepository
import com.alanmarksp.fxneosys.views.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class RegisterFragment : Fragment() {

    private var router: Router? = null
    private var fragmentRegister: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentRegister = inflater!!.inflate(R.layout.fragment_register, container, false)
        fragmentRegister?.let { setListeners(it) }
        return fragmentRegister
    }

    private fun setListeners(container: View) {
        val registerButton = container.findViewById<Button>(R.id.register_button)
        registerButton.setOnClickListener { register() }
        val loginTextView = container.findViewById<TextView>(R.id.login_text_view)
        loginTextView.setOnClickListener { login() }
    }

    private fun register() {
        val registerUsernameEditText = fragmentRegister
                ?.findViewById<EditText>(R.id.register_username_text_input_edit_text)
        val registerUsername: String? = registerUsernameEditText?.text.toString()
        val registerPasswordEditText = fragmentRegister
                ?.findViewById<EditText>(R.id.register_password_text_input_edit_text)
        val registerPassword: String? = registerPasswordEditText?.text.toString()
        val registerConfirmationPasswordEditText = fragmentRegister
                ?.findViewById<EditText>(R.id.register_password_confirmation_text_input_edit_text)
        val registerConfirmationPassword: String? = registerConfirmationPasswordEditText?.text.toString()
        if (registerUsername != null &&
                registerPassword != null &&
                registerConfirmationPassword != null) {
            if (registerUsername != "" &&
                    registerPassword != "" &&
                    registerConfirmationPassword != "") {
                if (registerPassword == registerConfirmationPassword) {
                    performRegister(registerUsername, registerPassword)
                } else {
                    val message = getString(R.string.register_different_passwords_message)
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            } else {
                val message = getString(R.string.register_empty_fields)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performRegister(registerUsername: String, registerPassword: String) {
        val authenticationPresenter = AuthenticatePresenter(AuthenticationRepository())
        authenticationPresenter
                .register(Authentication(registerUsername, registerPassword))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { registerSuccess() },
                        { error -> registerFailure(error) }
                )
    }

    private fun registerSuccess() {
        router?.navigate("main")
    }

    private fun registerFailure(error: Throwable) {
        if (error is HttpException) {
            val message = getString(R.string.register_failure_message)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun login() {
        router?.navigate("login")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        fun newInstance(router: Router): RegisterFragment {
            val fragment = RegisterFragment()
            fragment.router = router
            return fragment
        }
    }
}
