package com.nytimes.poc.arch.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.kaopiz.kprogresshud.KProgressHUD
import com.nytimes.poc.managers.AlertManager

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {


    lateinit var binding: DB

    private var dailog: KProgressHUD? = null

    private val destinationListener =
        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            onDestinationChanges(
                controller = controller,
                destination = destination,
                arguments = arguments
            )
        }

    /**
     * Getting Layout Instance
     */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getFragmentForNav(): Int


    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
    }

    override fun onResume() {
        super.onResume()
        this.findNavController(getFragmentForNav())
            .addOnDestinationChangedListener(destinationListener)
    }

    override fun onPause() {
        super.onPause()
        this.findNavController(getFragmentForNav())
            .removeOnDestinationChangedListener(destinationListener)
    }

    fun showLoader(progressText: String = "") {
        if (dailog == null) {
            dailog = KProgressHUD.create(this)
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
    }

    fun observeDataContextViewModelEvents(viewModel: BaseContextViewModel<*>) {
        viewModel.obDataEvent.observe(this) {
            it.getEventIfNotHandled()?.let { event ->
                when (event) {
                    is BaseDataEvents.ShowLoader -> showLoader(event.progressText)
                    BaseDataEvents.HideLoader -> hideLoader()
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertManager.showExceptionMessage(this, message)
    }


    fun showError(message: String) {
        AlertManager.showErrorMessage(this, message)
    }


    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertManager.showToast(this, message)
    }

    protected fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertManager.showSuccessMessage(this, message)
    }

    protected fun navigateTo(@IdRes fragmentId: Int) {
        this.findNavController(getFragmentForNav()).navigate(fragmentId)
    }


    protected fun navigateTo(@IdRes fragmentId: Int, bundle: Bundle) {
        this.findNavController(getFragmentForNav()).navigate(fragmentId, bundle)
    }


    protected fun popStackBack(): Boolean {
        return this.findNavController(getFragmentForNav()).popBackStack()
    }

    protected fun navController(): NavController {
        return this.findNavController(getFragmentForNav())
    }

    open fun onDestinationChanges(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
    }

    fun stackCount(): Int = this.findNavController(getFragmentForNav()).backQueue.size

    fun onExit() {
        finish()
    }

}
