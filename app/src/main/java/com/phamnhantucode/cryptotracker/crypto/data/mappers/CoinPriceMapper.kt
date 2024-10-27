package com.phamnhantucode.cryptotracker.crypto.data.mappers

import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinPriceDto
import com.phamnhantucode.cryptotracker.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
            priceUsd = priceUsd,
            time = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.systemDefault())
        )
}