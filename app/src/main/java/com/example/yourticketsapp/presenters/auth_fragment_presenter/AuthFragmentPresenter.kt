package com.example.yourticketsapp.presenters.auth_fragment_presenter

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.data.prefs.REMEMBER_USER
import com.example.yourticketsapp.data.prefs.SharedPreferences
import com.example.yourticketsapp.databinding.FragmentAuthBinding
import com.example.yourticketsapp.ui.main_activity.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthFragmentPresenter @Inject constructor(
    private val showToast: MainContract.MainView
):MainContract.PresenterFragment {

    private var view: Fragment? = null

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private var inputFocusableColor: Int? = null
    private var inputNotFocusableColor: Int? = null

    private var email: String? = null
    private var password: String? = null

    private var sp: SharedPreferences? = null

    fun setColors() {
        inputFocusableColor = ContextCompat.getColor(view!!.requireContext(), R.color.input_focusable)
        inputNotFocusableColor = ContextCompat.getColor(view!!.requireContext(), R.color.input_not_focusable)
    }

    fun initShared() {
        sp = SharedPreferences(view!!.requireContext())
    }

    fun initEmailAndPassword(mEmail: Editable?, mPassword: Editable?){
        email = mEmail.toString()
        password = mPassword.toString()
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

    fun clearFocus(hideKeyboard: () -> Unit) {
        hideKeyboard()
        binding.authEmailInputLayout.clearFocus()
        binding.authPasswordInputLayout.clearFocus()
    }


    fun buttonActionDown(hideKeyboard: () -> Unit, v: View){
        v.background =
            ContextCompat.getDrawable(view!!.requireContext(), R.drawable.button_action_down)
        hideKeyboard()
        when {
            email!!.isEmpty() -> {
                Log.v("lol", "$email")
                showToast.showToast("You have to write your email!")
            }
            password!!.isEmpty() -> showToast.showToast("You have to write your password!")
            else -> {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email.toString(), password.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            showToast.showToast("You logged successfully!")

                            val intent =
                                Intent(view!!.requireContext(), MainActivity::class.java)
                            view!!.startActivity(intent)

                            sp?.setPref(REMEMBER_USER, true)
                        } else showToast.showToast("Something wrong! Chek your email and password!")
                    }

            }
        }
    }
    override fun attachView(fragment: Fragment, mView: View) {
        view = fragment
        _binding = FragmentAuthBinding.bind(mView)
    }

    override fun detachView() {
        view = null
        _binding = null
        inputFocusableColor = null
        inputNotFocusableColor = null
        sp = null
    }
}