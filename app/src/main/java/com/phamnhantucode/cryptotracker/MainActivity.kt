package com.phamnhantucode.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                    val viewModel = koinViewModel<CoinsViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    CoinListScreen(state = state,  modifier = Modifier.padding(it))
                }
            }
        }
    }
}
