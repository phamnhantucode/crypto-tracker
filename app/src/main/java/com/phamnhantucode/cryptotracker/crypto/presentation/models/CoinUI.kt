package com.phamnhantucode.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.phamnhantucode.cryptotracker.crypto.domain.Coin
import com.phamnhantucode.cryptotracker.core.presentation.util.getDrawableIdForCoin
import java.text.NumberFormat
import java.util.Locale

data class CoinUI(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
) {
    companion object {
        fun mock(index: Int): CoinUI {
            return CoinUI(
                id = "id$index",
                rank = index,
                symbol = "BTC",
                name = "Bitcoin",
                marketCapUsd = DisplayableNumber(1_000_000.0, "$1,000,000.00"),
                priceUsd = DisplayableNumber(50_000.0, "$50,000.00"),
                changePercent24Hr = DisplayableNumber(0.05, "5.00%"),
                iconRes = getDrawableIdForCoin("BTC"),
            )
        }
    }
}

data class DisplayableNumber(
    val value: Double,
    val format: String,
)

fun Coin.toCoinUI(): CoinUI {
    return CoinUI(
        id = id,
        rank = rank,
        symbol = symbol,
        name = name,
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol),
    )
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayableNumber(this, formatter.format(this))
}

