package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName

class Coin {
    @SerializedName("CoinInfo") var coinInfo: CoinInfo? = null
    @SerializedName("DISPLAY") var display: CurrencyDisplay? = null
}