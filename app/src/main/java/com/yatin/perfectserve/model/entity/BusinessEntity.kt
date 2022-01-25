package com.yatin.perfectserve.model.entity

import com.google.gson.annotations.SerializedName
import com.yatin.perfectserve.domain.entities.Business

data class BusinessEntity(
    private val name: String,
    @SerializedName("image_url")
    private val imageUrl: String?,
    private val rating: Double
) {
    fun toBusiness() = Business(name, imageUrl, rating)
}
