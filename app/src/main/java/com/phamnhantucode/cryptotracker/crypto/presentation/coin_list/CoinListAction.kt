package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list

import com.phamnhantucode.cryptotracker.crypto.presentation.models.CoinUI

sealed interface CoinListAction {
    data class ClickCoin(val coin: CoinUI): CoinListAction
}