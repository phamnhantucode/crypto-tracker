package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list

import com.phamnhantucode.cryptotracker.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}