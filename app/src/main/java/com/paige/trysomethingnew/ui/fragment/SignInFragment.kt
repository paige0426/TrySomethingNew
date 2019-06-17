package com.paige.trysomethingnew.ui.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.biometric.BiometricPrompt
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import butterknife.BindView
import butterknife.ButterKnife
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.ui.activity.MainActivity
import com.paige.trysomethingnew.ui.viewmodel.SignInViewModel
import timber.log.Timber
import java.util.concurrent.Executors

class SignInFragment : BaseFragment() {

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

    private val signInViewModel: SignInViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_sign, container, false)
        ButterKnife.bind(this, root)

        root.setBackgroundColor(Color.BLUE)

        confirmTextInputEditText.visibility = View.GONE
        clickText.text = getString(R.string.sign_in)

        signInViewModel.result.observe(this, Observer {
            showSnackBar(root, it)
        })

        clickText.setOnClickListener {
            val email = emailTextInputEditText.text.toString()
            val password = passwordTextInputEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                showSnackBar(it, "Illegal Input")
                return@setOnClickListener
            }

            val shouldRemember = checkBox.isChecked

            signInViewModel.login(email, password, shouldRemember)
        }

        val rememberedEmail = signInViewModel.getRememberEmail()

        if (rememberedEmail != null) {
            loginWithBiometric(rememberedEmail)
        }

        return root
    }

    private fun loginWithBiometric(rememberedEmail: String) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("This is title")
            .setSubtitle("This is subtitle")
            .setDescription("This is description")
            .setNegativeButtonText("Cancel")
            .build()

        val biometricPrompt = BiometricPrompt(this.activity!!, Executors.newSingleThreadExecutor(),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Timber.e("Fingerprint error")
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Timber.i("Fingerprint succeed")

                    signInViewModel.loginWithFingerPrint(rememberedEmail) {
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Timber.i("Fingerprint failed")
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }

    private fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }
}