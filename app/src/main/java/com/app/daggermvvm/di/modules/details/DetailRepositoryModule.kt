package com.app.daggermvvm.di.modules.details

import com.app.daggermvvm.network.datasource.details.DetailRepository
import com.app.daggermvvm.network.datasource.details.DetailRepositoryImp
import dagger.Binds
import dagger.Module

@Module
interface DetailRepositoryModule {
    @Binds
    fun bindsRepositoryModule(mDetailRepository: DetailRepositoryImp): DetailRepository
}