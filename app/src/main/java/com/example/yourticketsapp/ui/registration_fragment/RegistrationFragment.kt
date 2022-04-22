package com.example.yourticketsapp.ui.registration_fragment

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.data.models.User
import com.example.yourticketsapp.data.prefs.REMEMBER_USER
import com.example.yourticketsapp.databinding.FragmentRegistrationBinding
import com.example.yourticketsapp.presenters.reg_fragment_presenter.RegFragmentPresenter
import com.example.yourticketsapp.ui.main_activity.MainActivity
import com.example.yourticketsapp.utils.Firebase
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegistrationFragment : BaseFragment(R.layout.fragment_registration),  MainContract.MainView  {

    @Inject
    lateinit var presenter: MainContract.PresenterFragment

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentRegistrationBinding.bind(view)

        presenter = RegFragmentPresenter(this)
        presenter.attachView(this, view)
        (presenter as RegFragmentPresenter).initShared()
        (presenter as RegFragmentPresenter).setColors()


        binding.regEmailInput.setOnFocusChangeListener { it, _ ->
            (presenter as RegFragmentPresenter).changeColor(
                it,
                binding.regEmailInputLayout,
                binding.regEmailInput.text!!
            )
        }

        binding.regPasswordInput.setOnFocusChangeListener { it, _ ->
            (presenter as RegFragmentPresenter).changeColor(
                it,
                binding.regPasswordInputLayout,
                binding.regPasswordInput.text!!
            )
        }

        binding.regConfirmPasswordInput.setOnFocusChangeListener { it, _ ->
            (presenter as RegFragmentPresenter).changeColor(
                it,
                binding.regConfirmPasswordInputLayout,
                binding.regConfirmPasswordInput.text!!
            )
        }

        binding.regContainer.setOnClickListener {
            (presenter as RegFragmentPresenter).clearFocus { hideKeyboard() }
        }

        binding.regSubmitBtn.setOnTouchListener(View.OnTouchListener { v, event ->
            v.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.button_action_down)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (presenter as RegFragmentPresenter).initEmailAndPassword(
                        binding.regEmailInput.text!!,
                        binding.regPasswordInput.text!!,
                        binding.regConfirmPasswordInput.text!!
                    )
                    (presenter as RegFragmentPresenter).regButtonDown({hideKeyboard()}, v)
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    v.background = ContextCompat.getDrawable(requireContext(), R.drawable.radius)
                    return@OnTouchListener true
                }
            }
            false
        })

        binding.toAuthFragmentBtn.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_registrationFragment_to_authFragment)
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_authFragment_to_registrationFragment)
                }
            })
    }

    fun onSuccessReg() {
        toast("You were registered successfully!")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showToast(message: String) {
        toast(message)
    }

}