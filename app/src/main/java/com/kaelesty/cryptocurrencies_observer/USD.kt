package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class USD: Serializable {

    @SerializedName("PRICE") var price: String? = null
    @SerializedName("LASTUPDATE") var lastUpdate: String? = null
    @SerializedName("HIGHDAY") var highestDayPrice: String? = null
    @SerializedName("LOWDAY") var lowestDayPrice: String? = null
    @SerializedName("LASTMARKET") var lastMarket: String? = null

}