package com.carlosquezada.core.domain

import java.io.IOException

sealed class NetworkException(errorMessage: String?) : IOException(errorMessage) {

    class NotFound(errorMessage: String?) : NetworkException(errorMessage)

    class ServerError(errorMessage: String?) : NetworkException(errorMessage)
}
