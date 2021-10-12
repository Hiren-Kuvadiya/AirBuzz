package com.test.airbuzz.model

import com.google.gson.annotations.SerializedName

class City {

    @SerializedName("city")
    var city : String = ""

    @SerializedName("aqi")
    var aqi : Double = -1.0

    var lastUpdated : Long = 0

}