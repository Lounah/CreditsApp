package com.lounah.creditsapp.di.common

import com.lounah.creditsapp.MainActivity
import com.lounah.creditsapp.di.creditsapp.CreditsAppFragmentInjectorBuilders
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesBuildersModule {
    @ContributesAndroidInjector(modules = [
        CreditsAppFragmentInjectorBuilders::class]
    )
    fun provideMainActivity(): MainActivity

}