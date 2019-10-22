package com.ddapps.booked

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ddapps.booked.databinding.MainActivityBinding
import com.ddapps.booked.util.BALANCE_MONEY
import com.ddapps.booked.viewmodels.SalesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private val viewModel: SalesViewModel by viewModel()
    private lateinit var appBarConfiguration: AppBarConfiguration

    var listener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                BALANCE_MONEY -> updateBalance(binding)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.main_activity)
        val navController = this.findNavController(R.id.navHostFragment)
        viewModel.sharedPrefs.preferences.registerOnSharedPreferenceChangeListener(listener)
        binding.viewModel = viewModel
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val actionBar = supportActionBar
        actionBar?.show()
    }

    @SuppressLint("SetTextI18n")
    fun updateBalance(binding: MainActivityBinding){
        val balanceTextView = binding.balanceMoney
        balanceTextView.text = viewModel.displayBalance()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.navHostFragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

    override fun onResume() {
        super.onResume()
        viewModel.sharedPrefs.preferences.registerOnSharedPreferenceChangeListener(listener)
    }

}
