package com.test.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.airbuzz.model.City

class MainViewModel {

    var mainRepo: MainRepo? = null
    var mutableLiveData: MutableLiveData<List<City>>? = null

    init {
        mainRepo = MainRepo()
    }

    fun get_weather(): LiveData<List<City>> {
        if (mutableLiveData == null) {
            mutableLiveData = mainRepo!!.fetchWeather()
        }
        return mutableLiveData!!
    }

}