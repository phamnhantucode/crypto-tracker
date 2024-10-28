package com.phamnhantucode.cryptotracker.crypto.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phamnhantucode.cryptotracker.core.domain.util.onError
import com.phamnhantucode.cryptotracker.core.domain.util.onSuccess
import com.phamnhantucode.cryptotracker.crypto.domain.CoinDataSource
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail.DataPoint
import com.phamnhantucode.cryptotracker.crypto.presentation.models.CoinUI
import com.phamnhantucode.cryptotracker.crypto.presentation.models.toCoinUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class CoinsViewModel(
    private val dataSource: CoinDataSource
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

    private val _event = Channel<CoinListEvent>()
    val event = _event.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        viewModelScope.launch {
            when (action) {
                is CoinListAction.ClickCoin -> {
                    selectedCoin(action.coin)
                }
            }
        }
    }

    private fun selectedCoin(coin: CoinUI) {
        viewModelScope.launch {
            _state.update {
                it.copy(selectedCoin = coin)
            }
            dataSource.getCoinPrice(
                coinId = coin.id,
                start = ZonedDateTime.now().minusDays(7),
                end = ZonedDateTime.now(),
            ).onSuccess { coinPrices ->
                _state.update {
                    it.copy(
                        selectedCoin = coin.copy(
                            coinHistory = coinPrices
                                .sortedBy { it.time }
                                .map {
                                    DataPoint(
                                        x = it.time.toEpochSecond().toFloat(),
                                        y = it.priceUsd,
                                        xLabel = DateTimeFormatter
                                            .ofPattern("ha\nM/d")
                                            .format(it.time)
                                    )
                                }
                        )
                    )
                }
            }.onError { error ->
                _event.send(CoinListEvent.Error(error))
            }
        }
    }

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
                    _event.send(CoinListEvent.Error(error))
                }
        }
    }
}