package com.example.yourticketsapp.presenters.reg_fragment_presenter

import android.app.ActivityOptions
import android.content.Intent
import android.text.Editable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.data.models.User
import com.example.yourticketsapp.data.prefs.REMEMBER_USER
import com.example.yourticketsapp.data.prefs.SharedPreferences
import com.example.yourticketsapp.databinding.FragmentRegistrationBinding
import com.example.yourticketsapp.ui.main_activity.MainActivity
import com.example.yourticketsapp.ui.registration_fragment.RegistrationFragment
import com.example.yourticketsapp.utils.Firebase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegFragmentPresenter @Inject constructor(
    private val showToast: MainContract.MainView
) : MainContract.PresenterFragment {

    private var view: Fragment? = null

    private var sp: SharedPreferences? = null

    private var inputFocusableColor: Int? = null
    private var inputNotFocusableColor: Int? = null

    private var email: String? = null
    private var password: String? = null
    private var confirmPassword: String? = null

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    fun initShared() {
        sp = SharedPreferences(view!!.requireContext())
    }

    fun setColors() {
        inputFocusableColor =
            ContextCompat.getColor(view!!.requireContext(), R.color.input_focusable)
        inputNotFocusableColor =
            ContextCompat.getColor(view!!.requireContext(), R.color.input_not_focusable)
    }

    fun initEmailAndPassword(mEmail: Editable?, mPassword: Editable?, mConfirmPassword: Editable?) {
        email = mEmail.toString()
        password = mPassword.toString()
        confirmPassword = mConfirmPassword.toString()
    }

    fun changeColor(viewEl: View, layout: TextInputLayout, text: Editable) {
        if (viewEl.isFocused) {
            layout.boxBackgroundColor = inputFocusableColor!!
        } else {
            changeInputColor(text, layout)
        }
    }

    private fun changeInputColor(text: Editable, view: TextInputLayout) {
        view.boxBackgroundColor = if (text.toString().trim().isNotEmpty()) {
            inputFocusableColor!!
        } else {
            inputNotFocusableColor!!
        }
    }

    fun regButtonDown(hideKeyboard: () -> Unit, v: View) {
        hideKeyboard()
        when {
            email!!.isEmpty() -> showToast.showToast("You should write email!")
            password!!.isEmpty() -> showToast.showToast("You should write password!")
            password!!.length < 6 -> showToast.showToast("Password must be 6 or more symbols!")
            confirmPassword!!.isEmpty() -> showToast.showToast("You should repeat password!")
            confirmPassword.toString() != password.toString() -> showToast.showToast("Your passwords don`t match!")
            else -> {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email.toString().trim(),
                    password.toString().trim()
                ).addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            val fireStore = Firebase()
                            fireStore.registerUser(
                                view!! as RegistrationFragment,
                                User(firebaseUser.uid, firebaseUser.email!!)
                            )
                            showToast.showToast("you registered successfully !")

                            sp?.setPref(REMEMBER_USER, true)

                            val intent =
                                Intent(view!!.requireContext(), MainActivity::class.java)
                            view!!.startActivity(
                                intent,
                                ActivityOptions.makeSceneTransitionAnimation(
                                    view!!.requireActivity()
                                ).toBundle()
                            )
                        } else showToast.showToast(task.exception.toString())
                    }
                )
            }
        }
    }

    fun clearFocus(hideKeyboard: () -> Unit) {
        hideKeyboard()
        binding.regEmailInputLayout.clearFocus()
        binding.regPasswordInputLayout.clearFocus()
        binding.regConfirmPasswordInputLayout
    }

    override fun attachView(fragment: Fragment, mView: View) {
        view = fragment
        _binding = FragmentRegistrationBinding.bind(mView)
    }

    override fun detachView() {
        view = null
        _binding = null
        inputFocusableColor = null
        inputNotFocusableColor = null
        sp = null
    }
}