package com.lounah.creditsapp.data.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.lounah.creditsapp.data.entity.CreditOffer
import io.reactivex.Single

@Dao
interface CreditOffersDao : BaseDao<CreditOffer> {
    @Query("SELECT * FROM credit_offers")
    fun getCreditOffers(): Single<List<CreditOffer>>

    @Query("DELETE FROM credit_offers")
    fun clearData()
}