package com.phamnhantucode.cryptotracker.crypto.data.networking

import com.phamnhantucode.cryptotracker.core.data.networking.constructUrl
import com.phamnhantucode.cryptotracker.core.data.networking.safeCall
import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError
import com.phamnhantucode.cryptotracker.core.domain.util.Result
import com.phamnhantucode.cryptotracker.core.domain.util.map
import com.phamnhantucode.cryptotracker.crypto.data.mappers.toCoin
import com.phamnhantucode.cryptotracker.crypto.data.mappers.toCoinPrice
import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinDto
import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.phamnhantucode.cryptotracker.crypto.domain.Coin
import com.phamnhantucode.cryptotracker.crypto.domain.CoinDataSource
import com.phamnhantucode.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
) : CoinDataSource {
    override suspend fun getListCoin(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(constructUrl(RequestPaths.listCoin))
        }.map { coinsResponseDto ->
            coinsResponseDto.data.map { coinDto: CoinDto -> coinDto.toCoin() }
        }
    }

    override suspend fun getCoinPrice(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
        interval: String
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endMillis = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(constructUrl(RequestPaths.coinHistory(coinId))) {
                parameter("interval", interval)
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { coinHistoryDto ->
            coinHistoryDto.data.map { coinPriceDto -> coinPriceDto.toCoinPrice() }
        }
    }
}