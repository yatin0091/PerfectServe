package com.yatin.perfectserve.domain.usecase

import com.yatin.perfectserve.domain.api.BusinessApi
import com.yatin.perfectserve.domain.core.BaseUseCase
import com.yatin.perfectserve.domain.core.Either
import com.yatin.perfectserve.domain.core.Failure
import com.yatin.perfectserve.domain.entities.Business
import javax.inject.Inject

class GetBusinessUseCase @Inject constructor(private val businessApi: BusinessApi) :
    BaseUseCase<List<Business>, GetBusinessUseCase.Params>() {

    data class Params(val location: String)

    override suspend fun run(params: Params): Either<Failure, List<Business>> =
        businessApi.businesses(params.location)
}