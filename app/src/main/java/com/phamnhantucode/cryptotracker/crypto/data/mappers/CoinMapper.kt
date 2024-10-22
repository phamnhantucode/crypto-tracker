package com.phamnhantucode.cryptotracker.crypto.data.mappers

import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinDto
import com.phamnhantucode.cryptotracker.crypto.domain.Coin

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        name = name,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        rank = rank,
        symbol = symbol,
        changePercent24Hr = changePercent24Hr
    )
}