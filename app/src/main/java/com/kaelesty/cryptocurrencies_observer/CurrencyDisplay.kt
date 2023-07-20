package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CurrencyDisplay: Serializable {

    @SerializedName("USD") var usd: USD? = null

}