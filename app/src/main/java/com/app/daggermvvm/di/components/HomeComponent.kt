package com.app.daggermvvm.di.components

import com.app.daggermvvm.di.modules.home.HomeRepositoryModule
import com.app.daggermvvm.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(modules = [HomeRepositoryModule::class])
interface HomeComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(fragment: HomeFragment)
}