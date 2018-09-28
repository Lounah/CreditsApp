package com.lounah.creditsapp.domain.repository

import com.lounah.creditsapp.data.entity.CreditOffer
import io.reactivex.Observable
import io.reactivex.Single

interface CreditOffersRepository {
    fun fetchCreditOffers() : Observable<List<CreditOffer>>
}