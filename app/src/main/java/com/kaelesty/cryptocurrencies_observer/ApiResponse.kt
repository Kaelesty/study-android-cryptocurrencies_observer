package com.kaelesty.cryptocurrencies_observer

import com.google.gson.annotations.SerializedName


class ApiResponse {
    @SerializedName("Data") var coins: ArrayList<Coin>? = null
}