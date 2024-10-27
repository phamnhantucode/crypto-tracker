package com.phamnhantucode.cryptotracker.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val priceUsd : Double,
    val time: ZonedDateTime
)