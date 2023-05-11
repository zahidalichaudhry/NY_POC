package com.nytimes.poc.arch.base

import com.nytimes.poc.utils.KeyboardUtils.activateHideKeyboardUponTouchingScreen
import android.app.KeyguardManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.kaopiz.kprogresshud.KProgressHUD
import com.nytimes.poc.managers.AlertManager

abstract class BaseFragment<DB : ViewDataBinding>(@LayoutRes private val layoutID: Int) :
    Fragment() {

    lateinit var binding: DB
    lateinit var ctx: FragmentActivity
    var mView: View? = null
    private var dailog: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.ctx = requireActivity()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(LayoutInflater.from(requireContext()), layoutID, null, false)
        activateHideKeyboardUponTouchingScreen(binding.root, requireActivity())
        if (mView == null) {
            mView = binding.root
            initializeComponents()
        }
        startObservingFragmentEvents()
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    protected abstract fun initializeComponents()
    protected abstract fun startObservingFragmentEvents()
    fun showLoader(progressText: String = "") {
        if (dailog == null) {
            dailog = KProgressHUD.create(requireContext())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        } else {
            dailog?.show()
        }
    }

    fun hideLoader() {
        dailog?.dismiss()
        dailog = null

    }

    fun observeDataContextViewModelEvents(viewModel: BaseViewModel<*>) {
        viewModel.obDataEvent.observe(viewLifecycleOwner) {
            it.getEventIfNotHandled()?.let { event ->
                when (event) {
                    is BaseDataEvents.ShowLoader -> showLoader(event.progressText)
                    is BaseDataEvents.HideLoader -> hideLoader()
                    is BaseDataEvents.Exception -> showException(event.message)
                    is BaseDataEvents.Error -> showError(event.message)
                    is BaseDataEvents.Toast -> showToast(event.message)
                    is BaseDataEvents.Success -> showSuccess(event.message)
                    BaseDataEvents.Back -> popStackBack()
                    else -> {}
                }
            }

        }
    }

    fun showException(message: String) {
        AlertManager.showExceptionMessage(requireActivity(), message)
        hideLoader()
    }


    fun showError(message: String) {
        AlertManager.showErrorMessage(requireActivity(), message)
        hideLoader()
    }


    protected fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        AlertManager.showToast(requireActivity(), message)
    }

    protected fun showSuccess(message: String) {
        hideLoader()
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        AlertManager.showSuccessMessage(requireActivity(), message)
    }

    protected fun navigateTo(@IdRes fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }

    protected fun navigateTo(direction: NavDirections) {
        findNavController().navigate(direction)
    }


    protected fun navigateTo(@IdRes fragmentId: Int, bundle: Bundle) {
        findNavController().navigate(fragmentId, bundle)
    }


    protected fun popStackBack() {
        if (findNavController().backQueue.size <= 2) with(requireActivity()) { finish() }
        else findNavController().popBackStack()
    }


    fun checkBiometricSupport(): Boolean {
        val keyguardManager: KeyguardManager =
            requireContext().getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (!keyguardManager.isKeyguardSecure) {
            return false
        }
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.USE_BIOMETRIC
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }
        return if (requireContext().packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    override fun onStop() {
        dailog?.dismiss()
        super.onStop()
    }



}