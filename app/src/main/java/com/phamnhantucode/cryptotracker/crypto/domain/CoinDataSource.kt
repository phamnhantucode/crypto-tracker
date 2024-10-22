package com.phamnhantucode.cryptotracker.crypto.domain

import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError
import com.phamnhantucode.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getListCoin(): Result<List<Coin>, NetworkError>
}