package com.yatin.perfectserve.model.repositories.business

import com.yatin.perfectserve.model.entity.ResponseEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusinessService @Inject constructor(retrofit: Retrofit) : BusinessRemoteAPIs {
    private val remoteApi by lazy { retrofit.create(BusinessRemoteAPIs::class.java) }

    override fun businesses(
        term: String,
        location: String,
        apiKey: String
    ): Call<ResponseEntity> = remoteApi.businesses(term, location)
}