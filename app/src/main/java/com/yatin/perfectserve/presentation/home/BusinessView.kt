package com.yatin.perfectserve.presentation.home

import android.os.Parcelable
import com.yatin.perfectserve.domain.entities.Business
import kotlinx.parcelize.Parcelize

@Parcelize
data class BusinessView(val name: String, val imageUrl: String?, val rating: Double) : Parcelable

fun Business.toBusinessView() = BusinessView(
    name, imageUrl, rating
)