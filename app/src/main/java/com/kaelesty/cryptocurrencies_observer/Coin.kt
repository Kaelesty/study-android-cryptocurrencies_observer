package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Coin: Serializable {
    @SerializedName("CoinInfo") var coinInfo: CoinInfo? = null
    @SerializedName("DISPLAY") var display: CurrencyDisplay? = null
}