package com.yatin.perfectserve.domain.core

/**
 * Base Class for handling errors/failures/exceptions.
 */
sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    abstract class FeatureFailure : Failure()
}
