package com.app.daggermvvm.di.components

import com.app.daggermvvm.di.modules.details.DetailRepositoryModule
import com.app.daggermvvm.models.responses.MovieModel
import com.app.daggermvvm.ui.details.DetailsFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DetailRepositoryModule::class])
interface DetailsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance movieItem : MovieModel): DetailsComponent
    }

    fun inject(fragment: DetailsFragment)
}