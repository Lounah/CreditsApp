package com.lounah.creditsapp.data.network

import com.lounah.creditsapp.data.entity.CreditOffersJsonResponseModel
import io.reactivex.Single
import retrofit2.http.GET

interface CreditsApi {

    @GET("credits.json")
    fun fetchCreditOffers(): Single<CreditOffersJsonResponseModel>

}