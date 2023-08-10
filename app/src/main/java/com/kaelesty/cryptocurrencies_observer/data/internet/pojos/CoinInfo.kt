package com.kaelesty.cryptocurrencies_observer.data.internet.pojos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CoinInfo: Serializable {
    @SerializedName("Name") var name: String? = null
    @SerializedName("ImageUrl") var imageUrl: String? = null
}