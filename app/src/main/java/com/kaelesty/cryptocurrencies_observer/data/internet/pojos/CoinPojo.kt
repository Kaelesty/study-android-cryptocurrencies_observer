package com.kaelesty.cryptocurrencies_observer.data.internet.pojos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CoinPojo: Serializable {
    @SerializedName("CoinInfo") var coinInfo: CoinInfo? = null
    @SerializedName("DISPLAY") var display: CurrencyDisplay? = null
}