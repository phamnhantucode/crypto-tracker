package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list

import androidx.compose.runtime.Immutable
import com.phamnhantucode.cryptotracker.crypto.presentation.models.CoinUI

@Immutable
data class CoinListState(
    val coins: List<CoinUI> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
    val selectedCoin: CoinUI? = null
)