package com.phamnhantucode.cryptotracker.crypto.domain

import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError
import com.phamnhantucode.cryptotracker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getListCoin(): Result<List<Coin>, NetworkError>
    suspend fun getCoinPrice(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
        interval: String = "h6"
    ): Result<List<CoinPrice>, NetworkError>
}