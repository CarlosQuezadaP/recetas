package com.carlosquezada.core.di

import android.util.Log
import com.carlosquezada.core.NetworkHelper
import com.carlosquezada.core.NetworkHelperImpl
import com.carlosquezada.core.data.ErrorHandlerImpl
import com.carlosquezada.core.domain.ErrorHandler
import com.carlosquezada.core.domain.NetworkException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.net.HttpURLConnection

private const val TIME_OUT = 60_000L

val networkModule = module {
    single<ErrorHandler> {
        ErrorHandlerImpl()
    }
    single<NetworkHelper> {
        NetworkHelperImpl(get())
    }
    single {
        httpClient
    }
}

val httpClient = HttpClient(CIO) {

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }

    engine {
        requestTimeout = TIME_OUT
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.v("Logger Ktor ->", message)
            }
        }
        level = LogLevel.ALL
    }

    install(ResponseObserver) {
        onResponse { response ->
            Log.d("Http status:", "${response.status.value}")
        }
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.Json)
    }

}.apply {
    plugin(HttpSend).intercept { request ->
        val originalCall = execute(request)

        when (originalCall.response.status.value) {
            HttpURLConnection.HTTP_NOT_FOUND -> throw NetworkException.NotFound("Not found error")
            HttpURLConnection.HTTP_INTERNAL_ERROR -> throw NetworkException.ServerError("Internal error")
            !in 100..399 -> execute(request)
            else -> {
                originalCall
            }
        }

    }
}