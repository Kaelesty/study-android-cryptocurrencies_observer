package com.kaelesty.cryptocurrencies_observer.data.internet.pojos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CurrencyDisplay: Serializable {

    @SerializedName("USD") var usd: USD? = null

}