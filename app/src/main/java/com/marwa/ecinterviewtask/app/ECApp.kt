package com.marwa.ecinterviewtask.app

import android.annotation.SuppressLint
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import com.marwa.ecinterviewtask.di.dataSourceModule
import com.marwa.ecinterviewtask.di.networkModule
import com.marwa.ecinterviewtask.di.preferencesModule
import com.marwa.ecinterviewtask.di.repositoryModule
import com.marwa.ecinterviewtask.di.useCaseModule
import com.marwa.ecinterviewtask.di.viewModelModule



class ECApp : Application() {
    @SuppressLint("HardwareIds")
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin(this)
    }

    private fun startKoin(app: Application) {
        org.koin.core.context.startKoin {
            androidLogger(Level.ERROR)
            androidContext(app)
            modules(
                viewModelModule,
                useCaseModule,
                dataSourceModule,
                repositoryModule,
                preferencesModule,
                networkModule,
            )
        }
    }
}
