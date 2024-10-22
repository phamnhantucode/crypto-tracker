package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phamnhantucode.cryptotracker.core.domain.util.onError
import com.phamnhantucode.cryptotracker.core.domain.util.onSuccess
import com.phamnhantucode.cryptotracker.crypto.domain.CoinDataSource
import com.phamnhantucode.cryptotracker.crypto.presentation.models.toCoinUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinsViewModel(
    val dataSource: CoinDataSource
) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart {
            loadCoins()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )

    private fun loadCoins() {
        viewModelScope.launch {
            dataSource.getListCoin()
                .onSuccess { coins ->
                    _state.update { state ->
                        state.copy(isLoading = false, coins = coins.map { it.toCoinUI() })
                    }
                }
                .onError { error ->
                    _state.update { it.copy(isLoading = false) }
                }
        }
    }
}