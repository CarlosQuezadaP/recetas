package com.carlosquezada.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkHelperImpl(
    private val context: Context
) : NetworkHelper {

    // Se verifica el estado de la conexión a internet
    override fun isInternetConnectionAvailable(): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
