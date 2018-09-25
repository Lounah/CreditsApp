package com.lounah.creditsapp.di.common.modules

import com.lounah.creditsapp.util.AppSchedulersProvider
import com.lounah.creditsapp.util.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Provides
    @Singleton
    fun provideAppScheduler(): SchedulersProvider = AppSchedulersProvider()
}