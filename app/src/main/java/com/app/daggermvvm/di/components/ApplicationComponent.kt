package com.app.daggermvvm.di.components

import android.content.Context
import com.app.daggermvvm.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,SubComponentsModule::class])
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun homeComponent(): HomeComponent.Factory

    fun detailsComponent(): DetailsComponent.Factory
}

@Module(subcomponents = [HomeComponent::class,DetailsComponent::class])
object SubComponentsModule
