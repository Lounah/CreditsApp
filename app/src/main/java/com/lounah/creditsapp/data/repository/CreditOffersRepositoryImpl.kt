package com.lounah.creditsapp.data.repository

import android.util.Log
import com.lounah.creditsapp.data.database.dao.CreditOffersDao
import com.lounah.creditsapp.data.entity.CreditOffer
import com.lounah.creditsapp.data.entity.CreditOffersJsonResponseModel
import com.lounah.creditsapp.data.network.CreditsApi
import com.lounah.creditsapp.domain.repository.CreditOffersRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CreditOffersRepositoryImpl @Inject constructor(
        private val creditOffersDao: CreditOffersDao,
        private val api: CreditsApi) : CreditOffersRepository {

    override fun fetchCreditOffers(): Observable<List<CreditOffer>> = Observable.concatArray(
            getCreditOffersFromDb().toObservable(), getCreditOffersFromApi().toObservable()
    )

    private fun getCreditOffersFromDb(): Single<List<CreditOffer>> = creditOffersDao.getCreditOffers()

    private fun getCreditOffersFromApi(): Single<List<CreditOffer>> = api.fetchCreditOffers().map { mapJsonCreditOffersToCreditOffers(it) }.doOnSuccess {
        creditOffersDao.insertAll(it)
    }


    private fun mapJsonCreditOffersToCreditOffers(jsonDataModel: CreditOffersJsonResponseModel): List<CreditOffer> {
        val offers = mutableListOf<CreditOffer>()
        jsonDataModel.result.forEach { jsonDataModelResultList -> jsonDataModelResultList.creditOffers.forEach {
            offers.add(CreditOffer(description = it.description, companyName = it.companyName, creditOptions = it.creditOptions, imageUrl = it.imageUrl, advancedInfo = it.advancedInfo, siteUrl = it.siteUrl, creditGroupTitle = jsonDataModelResultList.title))
        } }
        return offers
    }

}