package com.lounah.creditsapp.domain.interactor

import android.util.Log
import com.lounah.creditsapp.data.entity.CreditOffer
import com.lounah.creditsapp.domain.model.CreditOfferTitleViewObject
import com.lounah.creditsapp.domain.model.CreditOfferViewObject
import com.lounah.creditsapp.domain.repository.CreditOffersRepository
import io.reactivex.Observable
import javax.inject.Inject

class CreditOfferInteractor @Inject constructor(private val creditOfferRepository: CreditOffersRepository) {

    fun fetchCreditOffers(): Observable<List<CreditOfferViewObject>>
            = creditOfferRepository.fetchCreditOffers()
            .map { mapCreditsOfferToViewObject(it) }

    fun fetchCreditOffersByCreditGroup(creditGroup: String): Observable<List<CreditOfferViewObject>>
            = creditOfferRepository.fetchCreditOffers()
            .map { mapCreditsOfferToViewObjectByCreditGroup(it, creditGroup) }

    fun fetchCreditOfferByCreditOfferUrl(creditOfferURL: String): Observable<CreditOfferViewObject>
            = creditOfferRepository.fetchCreditOffers()
            .map { mapCreditsOfferToViewObjectWithSpecifiedURL(it, creditOfferURL) }

    fun fetchCreditOffersTitles(): Observable<List<CreditOfferTitleViewObject>>
            = creditOfferRepository.fetchCreditOffers()
            .map { mapCreditsOfferToTitleViewObject(it) }

    private fun mapCreditsOfferToViewObject(offers: List<CreditOffer>)
            = offers.map { CreditOfferViewObject(advancedInfo = it.advancedInfo, groupName = it.creditGroupTitle, description = it.description, companyName = it.companyName, imageUrl = it.imageUrl, creditOptions = it.creditOptions, siteUrl = it.siteUrl) }

    private fun mapCreditsOfferToViewObjectWithSpecifiedURL(offers: List<CreditOffer>, url: String)
            = offers.map { CreditOfferViewObject(advancedInfo = it.advancedInfo, groupName = it.creditGroupTitle, description = it.description, companyName = it.companyName, imageUrl = it.imageUrl, creditOptions = it.creditOptions, siteUrl = it.siteUrl) }
            .first { it.siteUrl == url }

    private fun mapCreditsOfferToViewObjectByCreditGroup(offers: List<CreditOffer>, creditGroup: String)
            = offers.map { CreditOfferViewObject(advancedInfo = it.advancedInfo, groupName = it.creditGroupTitle, description = it.description, companyName = it.companyName, imageUrl = it.imageUrl, creditOptions = it.creditOptions, siteUrl = it.siteUrl) }
            .filter { it.groupName == creditGroup }

    private fun mapCreditsOfferToTitleViewObject(offers: List<CreditOffer>): List<CreditOfferTitleViewObject> {
        val result = mutableListOf<CreditOfferTitleViewObject>()
        val savedTitles = mutableSetOf<String>()
        offers.forEach { savedTitles.add(it.creditGroupTitle!!) }
        savedTitles.forEach { result.add(CreditOfferTitleViewObject(it)) }
        return result
    }
}