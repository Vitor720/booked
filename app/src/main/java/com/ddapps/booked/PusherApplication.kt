package com.ddapps.booked

import android.app.Application
import com.ddapps.booked.data.network.networkModule
import com.ddapps.booked.data.prefModule
import com.ddapps.booked.data.shelfModule
import com.ddapps.booked.ui.main.fragments.salesFragmentModule
import com.ddapps.booked.viewmodels.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PusherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // TIMBER LOG DEBUGGER
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@PusherApplication)
            modules( listOf(prefModule, networkModule, viewModelModule, salesFragmentModule,
                shelfModule
//                , liveDataPref
            ))
        }
    }
}