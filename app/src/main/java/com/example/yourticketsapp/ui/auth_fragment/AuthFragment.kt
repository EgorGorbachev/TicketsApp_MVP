package com.example.yourticketsapp.ui.auth_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.example.yourticketsapp.R
import com.example.yourticketsapp.contract.MainContract
import com.example.yourticketsapp.core.BaseFragment
import com.example.yourticketsapp.databinding.FragmentAuthBinding
import com.example.yourticketsapp.presenters.auth_fragment_presenter.AuthFragmentPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class AuthFragment : BaseFragment(R.layout.fragment_auth), MainContract.MainView {

    @Inject
    lateinit var presenter: MainContract.PresenterFragment

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAuthBinding.bind(view)

        presenter = AuthFragmentPresenter(this)
        presenter.attachView(this, view)
        (presenter as AuthFragmentPresenter).setColors()
        (presenter as AuthFragmentPresenter).initShared()
        (presenter as AuthFragmentPresenter).initEmailAndPassword(
            binding.authEmailInput.text,
            binding.authPasswordInput.text
        )

        binding.authEmailInput.setOnFocusChangeListener { it, _ ->
            (presenter as AuthFragmentPresenter).changeColor(
                it,
                binding.authEmailInputLayout,
                binding.authEmailInput.text!!
            )
        }

        binding.authPasswordInput.setOnFocusChangeListener { it, _ ->
            (presenter as AuthFragmentPresenter).changeColor(
                it,
                binding.authPasswordInputLayout,
                binding.authPasswordInput.text!!
            )
        }

        binding.authContainer.setOnClickListener {
            (presenter as AuthFragmentPresenter).clearFocus { hideKeyboard() }
        }

        binding.authSubmitBtn.setOnTouchListener(OnTouchListener { v, event ->

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    (presenter as AuthFragmentPresenter).initEmailAndPassword(
                        binding.authEmailInput.text,
                        binding.authPasswordInput.text
                    )
                    (presenter as AuthFragmentPresenter).buttonActionDown({ hideKeyboard() }, v)
                    return@OnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    v.background = ContextCompat.getDrawable(requireContext(), R.drawable.radius)
                    return@OnTouchListener true
                }
            }
            false
        })

        binding.toRegistrationFragmentBtn.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_authFragment_to_registrationFragment)
        }

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    minimizeApp()
                }
            })
    }

    override fun showToast(message: String) {
        toast(message)
    }


}