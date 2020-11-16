package com.app.daggermvvm.di.modules.home

import com.app.daggermvvm.network.datasource.home.HomeRepository
import com.app.daggermvvm.network.datasource.home.HomeRepositoryImp
import dagger.Binds
import dagger.Module

@Module
interface HomeRepositoryModule {
    @Binds
    fun bindsHomeModule(repository: HomeRepositoryImp): HomeRepository
}