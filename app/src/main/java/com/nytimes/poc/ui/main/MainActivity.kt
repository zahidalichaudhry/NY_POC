package com.nytimes.poc.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nytimes.poc.R
import com.nytimes.poc.arch.base.BaseActivity
import com.nytimes.poc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
  private lateinit var navController: NavController
  private val viewModel: MainViewModel by viewModels()

  override fun getLayoutRes(): Int {
    return R.layout.activity_main
  }

  override fun getFragmentForNav(): Int {
    return R.id.fragment

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    navController = Navigation.findNavController(this, R.id.fragment)
    observeDataContextViewModelEvents(viewModel)
    viewModel.obEvents.observe(this) {
      when (val event = it.getEventIfNotHandled()) {
        else -> {}
      }
    }
    navController.addOnDestinationChangedListener { controller, destination, arguments ->
      if (destination.label.toString().isNotEmpty()) {
        viewModel.title.value = (destination.label.toString())
      }
    }
    binding.viewModel = viewModel
    binding.lifecycleOwner = this  }
}