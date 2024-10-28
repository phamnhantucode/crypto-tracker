package com.phamnhantucode.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.phamnhantucode.cryptotracker.core.presentation.navigation.AdaptiveListDetailPanel
import com.phamnhantucode.cryptotracker.core.presentation.util.ObserveEvents
import com.phamnhantucode.cryptotracker.core.presentation.util.toString
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinsViewModel
import com.phamnhantucode.cryptotracker.ui.theme.CryptoTrackerTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTrackerTheme {
                Scaffold {
                    AdaptiveListDetailPanel(
                        modifier = Modifier.padding(it)
                    )
                }
            }
        }
    }
}
