package com.lounah.creditsapp.presentation.credits

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.lounah.creditsapp.domain.interactor.CreditOfferInteractor
import com.lounah.creditsapp.domain.model.CreditOfferTitleViewObject
import com.lounah.creditsapp.domain.model.CreditOfferViewObject
import com.lounah.creditsapp.util.AppSchedulersProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreditsViewModel @Inject constructor(private val creditOfferInteractor: CreditOfferInteractor)
    : ViewModel() {

    internal val loadingState = MutableLiveData<Boolean>().apply { postValue(false) }
    internal val creditOffers = MutableLiveData<List<CreditOfferViewObject>>()
    internal val creditOffer = MutableLiveData<CreditOfferViewObject>()
    internal val creditOffersTitles = MutableLiveData<List<CreditOfferTitleViewObject>>()

    private val disposables = CompositeDisposable()

    internal fun getCreditOffers() {
        disposables.add(creditOfferInteractor.fetchCreditOffers()
                .doOnSubscribe { loadingState.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    creditOffers.postValue(result)
                    loadingState.postValue(false)
                }, { e -> }))
    }

    internal fun getCreditOffersByCreditGroup(creditGroup: String) {
        disposables.add(creditOfferInteractor.fetchCreditOffersByCreditGroup(creditGroup)
                .doOnSubscribe { loadingState.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    creditOffers.postValue(result)
                    loadingState.postValue(false)
                }, { e -> loadingState.postValue(false)}))
    }

    internal fun getCreditOfferByCreditOfferURL(creditOfferUrl: String) {
        disposables.add(creditOfferInteractor.fetchCreditOfferByCreditOfferUrl(creditOfferUrl)
                .doOnSubscribe { loadingState.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    creditOffer.postValue(result)
                    loadingState.postValue(false)
                }, { e -> loadingState.postValue(false)}))
    }

    internal fun getCreditOffersTitles() {
        disposables.add(creditOfferInteractor.fetchCreditOffersTitles()
                .doOnSubscribe { loadingState.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    creditOffersTitles.postValue(result)
                    loadingState.postValue(false)
                }, { e -> loadingState.postValue(false)}))
    }

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}