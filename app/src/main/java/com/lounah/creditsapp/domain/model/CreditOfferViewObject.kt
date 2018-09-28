package com.lounah.creditsapp.domain.model

import com.lounah.creditsapp.data.entity.CreditOption

data class CreditOfferViewObject(
        val groupName: String? = "",
        val description: String? = "",
        val companyName: String? = "",
        val advancedInfo: String? = "",
        val creditOptions: List<CreditOption>? = emptyList(),
        val imageUrl: String? = "",
        val siteUrl: String? = ""
)