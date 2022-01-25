package com.yatin.perfectserve.domain.api

import com.yatin.perfectserve.domain.core.Either
import com.yatin.perfectserve.domain.core.Failure
import com.yatin.perfectserve.domain.entities.Business

interface BusinessApi {
    fun businesses(location: String): Either<Failure, List<Business>>
}