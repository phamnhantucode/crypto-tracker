@file:OptIn(ExperimentalLayoutApi::class)

package com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phamnhantucode.cryptotracker.R
import com.phamnhantucode.cryptotracker.crypto.presentation.LineChart
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
                .padding(top = 16.dp)
                .verticalScroll(rememberScrollState()),

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
            AnimatedVisibility(visible = coin.coinHistory.isNotEmpty()) {
                var selectedDataPoint by remember {
                    mutableStateOf<DataPoint?>(null)
                }

                var xLabelWidth by remember {
                    mutableFloatStateOf(0f)
                }

                var yLabelWidth by remember {
                    mutableFloatStateOf(0f)
                }

                var totalChartWidth by remember {
                    mutableFloatStateOf(0f)
                }

                val amountOfXLabelPossible = if (xLabelWidth > 0) {
                    ((totalChartWidth - yLabelWidth) / xLabelWidth).toInt()
                } else 0
                Log.e("sd", "CoinDetailScreen: $amountOfXLabelPossible")

                val startIndex = (coin.coinHistory.lastIndex - amountOfXLabelPossible + 1)
                    .coerceAtLeast(0)

                LineChart(
                    dataPoints = coin.coinHistory,
                    style = ChartStyle(
                        chartLineColor = MaterialTheme.colorScheme.secondary,
                        selectedColor = MaterialTheme.colorScheme.primary,
                        unselectedColor = MaterialTheme.colorScheme.secondary.copy(
                            alpha = 0.2f
                        ),
                        helperLineThicknessPx = 5f,
                        axisLineThicknessPx = 5f,
                        horizontalPadding = 8.dp,
                        labelFontSize = 14.sp,
                        minYLabelSpacing = 25.dp,
                        verticalPadding = 8.dp,
                        xAxisLabelSpacing = 8.dp
                    ),
                    visibleDataPointIndices = startIndex..coin.coinHistory.lastIndex,
                    unit = "$",
                    modifier = modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .onSizeChanged {
                            totalChartWidth = it.width.toFloat()
                        },
                    selectedDataPoint = selectedDataPoint,
                    onSelectedDataPointChanged = { selectedDataPoint = it },
                    onXLabelWidthChanged = { xLabelWidth = it },
                    onYLabelWidthChanged = { yLabelWidth = it
                        Log.e("TAG", "CoinDetailScreen: ylabelWidth: $xLabelWidth", )
                    }
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