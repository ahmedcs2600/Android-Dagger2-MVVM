package com.app.daggermvvm

import android.app.Application
import com.app.daggermvvm.di.components.ApplicationComponent
import com.app.daggermvvm.di.components.DaggerApplicationComponent

class App : Application() {

    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ApplicationComponent {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}