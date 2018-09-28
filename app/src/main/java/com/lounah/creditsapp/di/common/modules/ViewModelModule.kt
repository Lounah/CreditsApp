package com.lounah.creditsapp.di.common.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lounah.creditsapp.di.common.ViewModelKey
import com.lounah.creditsapp.presentation.common.AppViewModelFactory
import com.lounah.creditsapp.presentation.creditdetails.CreditDetailsViewModel
import com.lounah.creditsapp.presentation.credits.CreditsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CreditsViewModel::class)
    abstract fun bindSearchFilterstViewModel(viewModel: CreditsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreditDetailsViewModel::class)
    abstract fun bindCreditDetailsViewModel(viewModel: CreditDetailsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}