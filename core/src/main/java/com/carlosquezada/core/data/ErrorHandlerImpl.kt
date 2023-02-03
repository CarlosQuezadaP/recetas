package com.carlosquezada.core.data


import com.carlosquezada.core.domain.ErrorEntity
import com.carlosquezada.core.domain.ErrorHandler
import io.ktor.client.plugins.*
import io.ktor.http.*
import java.io.IOException

class ErrorHandlerImpl() : ErrorHandler {

    override fun getError(throwable: Throwable): ErrorEntity {
        throwable.printStackTrace()
        return when (throwable) {
            is IOException -> ErrorEntity.NoInternetConnection
            is ResponseException -> {
                when (throwable.response.status) {
                    HttpStatusCode.NotFound -> ErrorEntity.NotFound
                    HttpStatusCode.Forbidden -> ErrorEntity.AccessDenied
                    HttpStatusCode.ServiceUnavailable -> ErrorEntity.ServiceUnavailable
                    else -> ErrorEntity.Unknown
                }
            }
            else -> ErrorEntity.Unknown
        }
    }
}
