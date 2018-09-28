package com.lounah.creditsapp.di.creditsapp

import com.lounah.creditsapp.presentation.credits.CreditOffersListFragment
import com.lounah.creditsapp.presentation.credits.CreditsFragment
import com.lounah.creditsapp.presentation.history.CreditsHistoryFragment
import com.lounah.creditsapp.presentation.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CreditsAppFragmentInjectorBuilders {
    @ContributesAndroidInjector
    fun provideCreditsFragment(): CreditsFragment

    @ContributesAndroidInjector
    fun provideCreditsListFragment(): CreditOffersListFragment

    @ContributesAndroidInjector
    fun provideSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    fun provideCreditsHistoryFragment(): CreditsHistoryFragment
}