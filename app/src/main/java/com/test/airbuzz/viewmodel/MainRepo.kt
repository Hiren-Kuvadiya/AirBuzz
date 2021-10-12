package com.test.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.test.myapplication.utils.Constants
import com.test.airbuzz.model.City
import com.test.airbuzz.model.WeatherResponse
import okhttp3.*
import okio.ByteString


class MainRepo {

    val TAG = javaClass.simpleName
    lateinit var okHttpClient: OkHttpClient



    fun rx_fetch_weather() {

        okHttpClient = OkHttpClient()

        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {

            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "on_message_received:" + text)


                var gson : Gson =  GsonBuilder().setPrettyPrinting().create();
                var response = gson.fromJson<WeatherResponse>(text, WeatherResponse::class.java)

            }

            override fun onMessage(
                webSocket: WebSocket,
                bytes: ByteString
            ) {

            }

            override fun onClosing(
                webSocket: WebSocket,
                code: Int, reason: String
            ) {

            }

            override fun onClosed(
                webSocket: WebSocket,
                code: Int, reason: String
            ) {

            }

            override fun onFailure(
                webSocket: WebSocket,
                throwable: Throwable,
                response: Response?
            ) {


            }
        }

    }

    fun fetchWeather(): MutableLiveData<List<City>> {
        var mutableList: MutableLiveData<List<City>> = MutableLiveData()


        okHttpClient = OkHttpClient()

        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {

            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "on_message_received:" + text)

                var gson : Gson =  Gson();
                val itemType = object : TypeToken<List<City>>() {}.type
                var response = gson.fromJson<List<City>>(text, itemType)
                Log.d(TAG, "on_message_received:" + response)

                 mutableList.value =  response
            }

            override fun onMessage(
                webSocket: WebSocket,
                bytes: ByteString
            ) {

            }

            override fun onClosing(
                webSocket: WebSocket,
                code: Int, reason: String
            ) {

            }

            override fun onClosed(
                webSocket: WebSocket,
                code: Int, reason: String
            ) {

            }

            override fun onFailure(
                webSocket: WebSocket,
                throwable: Throwable,
                response: Response?
            ) {


            }
        }


        val request = Request.Builder().url(Constants.BASE_URL).build()
        okHttpClient.newWebSocket(request, webSocketListener)

        return mutableList
    }


}