package com.lounah.creditsapp.presentation.creditdetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.lounah.creditsapp.domain.interactor.CreditOfferInteractor
import com.lounah.creditsapp.domain.model.CreditOfferTitleViewObject
import com.lounah.creditsapp.domain.model.CreditOfferViewObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CreditDetailsViewModel @Inject constructor(private val creditOfferInteractor: CreditOfferInteractor)
    : ViewModel() {

    internal val loadingState = MutableLiveData<Boolean>().apply { postValue(false) }
    internal val creditOffer = MutableLiveData<CreditOfferViewObject>()

    private val disposables = CompositeDisposable()

    internal fun getCreditOfferByCreditOfferURL(creditOfferUrl: String) {
        disposables.add(creditOfferInteractor.fetchCreditOfferByCreditOfferUrl(creditOfferUrl)
                .doOnSubscribe { loadingState.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    creditOffer.postValue(result)
                    loadingState.postValue(false)
                }, { e -> }))
    }
    override fun onCleared() {
        super.onCleared()
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}