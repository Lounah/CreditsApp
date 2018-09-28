package com.lounah.creditsapp.di.common

import com.lounah.creditsapp.presentation.main.MainActivity
import com.lounah.creditsapp.di.creditsapp.CreditsAppFragmentInjectorBuilders
import com.lounah.creditsapp.presentation.creditdetails.CreditDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivitiesBuildersModule {
    @ContributesAndroidInjector(modules = [
        CreditsAppFragmentInjectorBuilders::class]
    )
    fun provideMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [
        CreditsAppFragmentInjectorBuilders::class]
    )
    fun provideCreditDetailsActivity(): CreditDetailsActivity

}