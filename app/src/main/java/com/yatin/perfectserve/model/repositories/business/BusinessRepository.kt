package com.yatin.perfectserve.model.repositories.business

import com.yatin.perfectserve.domain.api.BusinessApi
import com.yatin.perfectserve.domain.core.Either
import com.yatin.perfectserve.domain.core.Either.Left
import com.yatin.perfectserve.domain.core.Failure
import com.yatin.perfectserve.domain.entities.Business
import com.yatin.perfectserve.model.core.BaseRepository
import com.yatin.perfectserve.model.core.NetworkHandler
import com.yatin.perfectserve.model.entity.ResponseEntity
import javax.inject.Inject

class BusinessRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val businessService: BusinessService
) : BusinessApi, BaseRepository() {


    override fun businesses(location: String): Either<Failure, List<Business>> {
        return when (networkHandler.isNetworkAvailable()) {
            true -> request(
                //hardcoded to search restaurants
                businessService.businesses("restaurants", location),
                { it.businesses.map { businessEntity -> businessEntity.toBusiness() } },
                ResponseEntity(0, emptyList())
            )
            false -> Left(Failure.NetworkConnection)
        }
    }
}