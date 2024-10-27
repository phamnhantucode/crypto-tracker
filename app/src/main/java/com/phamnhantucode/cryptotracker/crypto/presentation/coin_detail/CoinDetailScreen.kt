@file:OptIn(ExperimentalLayoutApi::class)

package com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phamnhantucode.cryptotracker.R
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail.components.InfoCard
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListState
import com.phamnhantucode.cryptotracker.crypto.presentation.models.CoinUI
import com.phamnhantucode.cryptotracker.crypto.presentation.models.toDisplayableNumber
import com.phamnhantucode.cryptotracker.ui.theme.CryptoTrackerTheme
import com.phamnhantucode.cryptotracker.ui.theme.greenBackground

@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selectedCoin != null) {
        val coin = state.selectedCoin
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(coin.iconRes),
                contentDescription = coin.name,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = coin.name,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = coin.symbol,
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.Light
            )
            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Center,
            ) {
                InfoCard(
                    icon = ImageVector.vectorResource(R.drawable.stock),
                    title = stringResource(id = R.string.market_cap),
                    formattedText = "$ ${coin.marketCapUsd.format}",
                )
                InfoCard(
                    icon = ImageVector.vectorResource(R.drawable.dollar),
                    title = stringResource(id = R.string.price),
                    formattedText = "$ ${coin.priceUsd.format}",
                )
                val priceChange24Hr =
                    (coin.priceUsd.value * (coin.changePercent24Hr.value / 100)).toDisplayableNumber()
                val isPositive = priceChange24Hr.value > 0.0
                val icon =
                    if (isPositive) ImageVector.vectorResource(R.drawable.trending) else ImageVector.vectorResource(
                        R.drawable.trending_down
                    )
                val contentColor =
                    if (isPositive) if (isSystemInDarkTheme()) Color.Green else greenBackground else MaterialTheme.colorScheme.onError
                InfoCard(
                    icon = icon,
                    title = stringResource(id = R.string.market_cap),
                    formattedText = "$ ${priceChange24Hr.format}",
                    contentColor = contentColor
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinDetailScreenPreview() {
    CryptoTrackerTheme {
        CoinDetailScreen(
            state = CoinListState(selectedCoin = CoinUI.mock(1)),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}