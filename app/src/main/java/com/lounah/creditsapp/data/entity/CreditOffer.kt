package com.lounah.creditsapp.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CreditOffersJsonResponseModel(
        @SerializedName("in")
        val result: List<CreditResponse>
)

data class CreditResponse(
        @SerializedName("name")
        val title: String? = "",
        @SerializedName("tab")
        val creditOffers: List<CreditOffer>
)

@Entity(tableName = "credit_offers")
data class CreditOffer(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,

        @SerializedName("description")
        var description: String? = "",

        @SerializedName("company_name")
        var companyName: String? = "",

        @SerializedName("image_url")
        var imageUrl: String? = "",

        @SerializedName("site_url")
        var siteUrl: String? = "",

        @SerializedName("credit_options")
        @Ignore
        var creditOptions: List<CreditOption>? = emptyList(),

        @SerializedName("advanced_info")
        var advancedInfo: String? = "",

        var creditGroupTitle: String? = ""
)

data class CreditOption(
        @SerializedName("credit_type")
        var creditType: String? = "",
        @SerializedName("cost")
        var cost: String? = "",
        @SerializedName("period")
        var period: String? = "",
        @SerializedName("rate")
        var rate: String? = ""
)