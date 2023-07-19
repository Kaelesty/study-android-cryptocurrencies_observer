package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName

class CoinInfo {
    @SerializedName("Name") var name: String? = null
    @SerializedName("ImageUrl") var imageUrl: String? = null
}