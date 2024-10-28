package com.phamnhantucode.cryptotracker.crypto.data.networking.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinPriceDto(
    val priceUsd: Float,
    val time: Long
)
