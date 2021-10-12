package com.test.airbuzz.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.test.myapplication.utils.Constants
import com.test.myapplication.viewmodel.MainRepo
import com.test.myapplication.viewmodel.MainViewModel
import com.test.airbuzz.R
import okhttp3.*
import okio.ByteString
import org.json.JSONArray
import com.test.airbuzz.model.City
import com.test.airbuzz.utils.AppUtils
import com.test.airbuzz.view.fragments.CityListFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mCityNames : ArrayList<City> = ArrayList()
    private lateinit var fragment: CityListFragment

    private var gson: Gson = Gson();
    private val TAG: String = javaClass.simpleName
    private var okHttpClient = OkHttpClient()

    val mainViewModel = MainViewModel()
    val mainRepo = MainRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?);
        getSupportActionBar()?.setTitle(resources.getString(R.string.app_name));

        createWebSocketClient()
        fragment = CityListFragment.newInstance()
        mCityNames = AppUtils.mCityNameList

        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.rootFrameLayout, fragment)
            transaction.commit()
        }

    }

    fun createWebSocketClient() {

        /* main_view_model.get_weather()
        .observe(this, object : Observer<List<City>> {

            override fun onChanged(t: List<City>) {
                Log.d("weather_response:", "observe onChanged()=" + t)
            }
        })*/


        val webSocketListener = object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {

            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                Log.d(TAG, "on_message_received:" + text)


                var socketData : JSONArray= JSONArray(text)
                var mCityList = ArrayList<City>()



                for(i in 0..socketData.length()-1){

                    var mCity: City = City()

                    var cityName : String = socketData.getJSONObject(i).getString("city")
                    var aqi : Double = socketData.getJSONObject(i).getDouble("aqi")

                    mCity.city = cityName
                    mCity.aqi = aqi

                    mCityList.add(mCity)

                    var isAdded : Boolean = false

                    for(i in 0..AppUtils.mCityNameList.size-1){

                        var mAddedCity = AppUtils.mCityNameList.get(i)

                        if(mAddedCity.city.equals(mCity.city,ignoreCase = true)){
                            isAdded = true
                        }

                    }

                    if(!isAdded){
                        AppUtils.mCityNameList.add(mCity)
                    }


                }

                Log.d(TAG, "city_response:" + mCityList)
                Log.d(TAG, "city_response_size:" +  AppUtils.mCityNameList.size)


                runOnUiThread {
                    fragment.updateAdapter(mCityList)
                    pBar.visibility = View.GONE
                }



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

    }

}