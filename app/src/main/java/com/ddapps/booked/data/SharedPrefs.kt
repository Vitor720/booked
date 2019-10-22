package com.ddapps.booked.data

import android.content.Context
import android.content.SharedPreferences
import com.ddapps.booked.util.BALANCE_MONEY
import com.ddapps.booked.util.SHOULD_GIVE_MONEY
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import timber.log.Timber

val prefModule = module {
    factory { SharedPrefs(androidContext())}
}

class SharedPrefs(context: Context){
     val preferences: SharedPreferences = context.getSharedPreferences("wallet", Context.MODE_PRIVATE)

     var livePrefs: SharedPreferences.OnSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        when(key){
            BALANCE_MONEY -> getBalance()
            else -> getBalance()
        }
    }

    init {
        if (getShouldGiveCredit()){
            Timber.e("Primeiro login")
            storeBalance(100f)
            storeShouldGiveCredit(!getShouldGiveCredit())
        }  else {
            Timber.e("A carteira já salva tem =  ${getBalance()}")
        }
    }

    fun storeBalance(balance: Float) {
        Timber.e("Balance stored")
        preferences.edit().putFloat(BALANCE_MONEY, balance).apply()
    }

    fun getBalance(): Float {
        Timber.e("getBalance() was called")
        return preferences.getFloat(BALANCE_MONEY, 16f)
    }

    private fun storeShouldGiveCredit(shouldGive: Boolean) {
        preferences.edit().putBoolean(SHOULD_GIVE_MONEY, shouldGive).apply()
    }

    private fun getShouldGiveCredit(): Boolean {
        return preferences.getBoolean(SHOULD_GIVE_MONEY, true)
    }
}