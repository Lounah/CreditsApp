package com.lounah.creditsapp.presentation.credits

import android.support.v7.util.DiffUtil
import com.lounah.creditsapp.domain.model.CreditOfferViewObject

class CreditOfferDiffUtilCallback(private val oldOffers: List<CreditOfferViewObject>,
                                  private val newOffers: List<CreditOfferViewObject>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldOffers.size
    }

    override fun getNewListSize(): Int {
        return newOffers.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOffers[oldItemPosition].siteUrl.equals(newOffers[newItemPosition].siteUrl)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldOffers[oldItemPosition].description.equals(newOffers[newItemPosition].description)
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}