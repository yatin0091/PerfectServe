package com.yatin.perfectserve.model.core

import com.yatin.perfectserve.domain.core.Either
import com.yatin.perfectserve.domain.core.Failure
import retrofit2.Call

abstract class BaseRepository {

    protected fun <T, R> request(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}