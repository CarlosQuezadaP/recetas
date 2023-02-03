package com.carlosquezada.core.domain

interface ErrorHandler {

    fun getError(throwable: Throwable): ErrorEntity
}
