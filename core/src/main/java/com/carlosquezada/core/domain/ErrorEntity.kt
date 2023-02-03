package com.carlosquezada.core.domain


abstract class ErrorEntity  {

    object NotFound : ErrorEntity()

    object AccessDenied : ErrorEntity()

    object ServiceUnavailable : ErrorEntity()

    object NoInternetConnection : ErrorEntity()

    object Unknown : ErrorEntity()
}
