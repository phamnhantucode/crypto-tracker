package com.phamnhantucode.cryptotracker.di

import com.phamnhantucode.cryptotracker.core.data.networking.HttpClientFactory
import com.phamnhantucode.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.phamnhantucode.cryptotracker.crypto.domain.CoinDataSource
import com.phamnhantucode.cryptotracker.crypto.presentation.coin_list.CoinsViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinsViewModel)
}