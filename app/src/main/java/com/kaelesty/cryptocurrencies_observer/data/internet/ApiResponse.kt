package com.kaelesty.cryptocurrencies_observer.data.internet

import com.google.gson.annotations.SerializedName
import com.kaelesty.cryptocurrencies_observer.data.internet.pojos.CoinPojo


class ApiResponse {
    @SerializedName("Data") var coins: ArrayList<CoinPojo>? = null
}