package com.paige.trysomethingnew.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.ui.home.HomeViewModel
import com.paige.trysomethingnew.ui.viewmodel.SignUpViewModel

class SignUpFragment : BaseFragment() {

    @BindView(R.id.email_text_input_edit_text)
    lateinit var emailTextInputEditText : TextInputEditText

    @BindView(R.id.password_text_input_edit_text)
    lateinit var passwordTextInputEditText : TextInputEditText

    @BindView(R.id.confirm_text_input_edit_text)
    lateinit var confirmTextInputEditText : TextInputEditText

    @BindView(R.id.click_text)
    lateinit var clickText: TextView

    @BindView(R.id.remember_me)
    lateinit var checkBox: CheckBox

    private val signUpViewModel: SignUpViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_sign, container, false)
        ButterKnife.bind(this, root)

        root.setBackgroundColor(Color.GREEN)

        checkBox.visibility = View.GONE

        clickText.text = getString(R.string.sign_up)

        signUpViewModel.result.observe(this, Observer {
            showSnackBar(root, it)
        })

        clickText.setOnClickListener {
            val email = emailTextInputEditText.text.toString()
            val password = passwordTextInputEditText.text.toString()
            val confirmPassword = confirmTextInputEditText.text.toString()

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showSnackBar(it, "Illegal Input")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showSnackBar(it, "Password Mismatch")
                return@setOnClickListener
            }

            signUpViewModel.register(email, password)
        }

        return root
    }

    private fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}