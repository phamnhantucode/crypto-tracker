package com.phamnhantucode.cryptotracker.core.data.networking

import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError
import com.phamnhantucode.cryptotracker.core.domain.util.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T>responseToResult(
    response: HttpResponse,
): Result<T, NetworkError> {
    return when(response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        400 -> Result.Error(NetworkError.BAD_REQUEST)
        401 -> Result.Error(NetworkError.UNAUTHORIZED)
        403 -> Result.Error(NetworkError.FORBIDDEN)
        404 -> Result.Error(NetworkError.NOT_FOUND)
        500 -> Result.Error(NetworkError.INTERNAL_SERVER_ERROR)
        503 -> Result.Error(NetworkError.SERVICE_UNAVAILABLE)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN)
    }
}