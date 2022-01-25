package com.yatin.perfectserve.model.repositories.business

import com.yatin.perfectserve.model.entity.ResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface BusinessRemoteAPIs {

    companion object {
        private const val PARAM_LOCATION = "location"
        private const val SEARCH = "search"
        private const val PARAM_TERM = "term"
        private const val API_KEY =
            "Bearer O5H9A0D9qSnN2Q-MQerhCuUljXnNlRaYGZdxv0HM5SLfznvtTDj_lwhMg-_RF7Tq-pwB7-KeNvoFRDoEL0Or7xndhButRGOohZn2l8nanLQAIIe2MISSmvw525SmYXYx"
    }

    @GET(SEARCH)
    fun businesses(
        @Query(PARAM_TERM) term: String,
        @Query(PARAM_LOCATION) location: String,
        @Header("Authorization") apiKey: String = API_KEY
    ): Call<ResponseEntity>
}