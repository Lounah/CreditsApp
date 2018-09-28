package com.lounah.creditsapp.di.creditsapp

import com.lounah.creditsapp.data.repository.CreditOffersRepositoryImpl
import com.lounah.creditsapp.domain.repository.CreditOffersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class CreditsAppBindingModule {


    @Singleton
    @Binds
    abstract fun bindCreditOfferRepository(impl: CreditOffersRepositoryImpl): CreditOffersRepository


}