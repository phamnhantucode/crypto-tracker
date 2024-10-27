package com.phamnhantucode.cryptotracker.core.data.networking

import com.phamnhantucode.cryptotracker.BuildConfig

fun constructUrl(
    baseUrl: String,
): String {
    return when {
        baseUrl.contains(BuildConfig.BASE_URL) -> baseUrl
        baseUrl.startsWith('/') -> "${BuildConfig.BASE_URL}${baseUrl.drop(1)}"
        else -> "${BuildConfig.BASE_URL}$baseUrl"
    }
}