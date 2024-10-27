package com.phamnhantucode.cryptotracker.core.presentation.util

import android.content.Context
import com.phamnhantucode.cryptotracker.R
import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val resId = when (this) {
        NetworkError.NO_INTERNET_CONNECTION -> R.string.no_internet_connection
        NetworkError.UNKNOWN -> R.string.unknown_error
        NetworkError.TIMEOUT -> R.string.timeout_error
        NetworkError.SERVER_UNAVAILABLE -> R.string.server_unavailable
        NetworkError.SERVER_ERROR -> R.string.server_error
        NetworkError.BAD_REQUEST -> R.string.bad_request
        NetworkError.UNAUTHORIZED -> R.string.unauthorized
        NetworkError.FORBIDDEN -> R.string.forbidden
        NetworkError.NOT_FOUND -> R.string.not_found
        NetworkError.METHOD_NOT_ALLOWED -> R.string.method_not_allowed
        NetworkError.CONFLICT -> R.string.conflict
        NetworkError.GONE -> R.string.gone
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.INTERNAL_SERVER_ERROR -> R.string.internal_server_error
        NetworkError.SERVICE_UNAVAILABLE -> R.string.service_unavailable
        NetworkError.GATEWAY_TIMEOUT -> R.string.gateway_timeout
        NetworkError.NETWORK_AUTHENTICATION_REQUIRED -> R.string.network_authentication_required
        NetworkError.NETWORK_ERROR -> R.string.network_error
        NetworkError.SERIALIZATION_ERROR -> R.string.serialization_error
    }
    return context.getString(resId)
}