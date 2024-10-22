package com.phamnhantucode.cryptotracker.crypto.data.networking

import com.phamnhantucode.cryptotracker.core.data.networking.constructUrl
import com.phamnhantucode.cryptotracker.core.data.networking.safeCall
import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError
import com.phamnhantucode.cryptotracker.core.domain.util.Result
import com.phamnhantucode.cryptotracker.core.domain.util.map
import com.phamnhantucode.cryptotracker.crypto.data.mappers.toCoin
import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinDto
import com.phamnhantucode.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.phamnhantucode.cryptotracker.crypto.domain.Coin
import com.phamnhantucode.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getListCoin(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(constructUrl(RequestPaths.listCoin))
        }.map {coinsResponseDto ->
            coinsResponseDto.data.map { coinDto: CoinDto -> coinDto.toCoin() }
        }
    }
}