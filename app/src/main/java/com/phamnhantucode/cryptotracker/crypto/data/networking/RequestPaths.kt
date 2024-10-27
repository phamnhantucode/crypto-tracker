package com.phamnhantucode.cryptotracker.crypto.data.networking

object RequestPaths {
    const val listCoin = "assets";
    val coinHistory: (coinId: String) -> String
        get() = { "assets/${it}/history" }


}