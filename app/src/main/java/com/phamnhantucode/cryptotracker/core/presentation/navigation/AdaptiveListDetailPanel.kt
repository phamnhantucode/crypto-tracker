@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package com.phamnhantucode.cryptotracker.core.presentation.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.phamnhantucode.cryptotracker.core.presentation.util.ObserveEvents
import com.phamnhantucode.cryptotracker.core.presentation.util.toString
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListAction
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AdaptiveListDetailPanel(
    modifier: Modifier,
    viewModel: CoinsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    ObserveEvents(events = viewModel.event) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.error.toString(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    state = state,
                    onAction = { action ->
                        viewModel.onAction(action)
                        when (action) {
                            is CoinListAction.ClickCoin -> navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Detail)
                        }
                    },
                    events = viewModel.event,
                    modifier = Modifier
                )
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(state = state)
            }
        },
    )
}