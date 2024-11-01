package com.phamnhantucode.cryptotracker.core.domain.util

enum class NetworkError: Error {
    NO_INTERNET_CONNECTION,
    UNKNOWN,
    TIMEOUT,
    SERVER_UNAVAILABLE,
    SERVER_ERROR,
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    METHOD_NOT_ALLOWED,
    CONFLICT,
    GONE,
    TOO_MANY_REQUESTS,
    INTERNAL_SERVER_ERROR,
    SERVICE_UNAVAILABLE,
    GATEWAY_TIMEOUT,
    NETWORK_AUTHENTICATION_REQUIRED,
    NETWORK_ERROR,
    SERIALIZATION_ERROR
}