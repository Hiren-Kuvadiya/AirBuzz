package com.test.myapplication.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.test.airbuzz.R
import com.test.airbuzz.model.City
import com.test.airbuzz.utils.AppUtils
import java.math.RoundingMode
import java.text.DecimalFormat

class CityListAdapter(var context: Context?, var mCityList: List<City>) :

    RecyclerView.Adapter<CityListAdapter.MyViewHolder>() {

    private var currentTimeStamp: Long
    private var dec_format: DecimalFormat
    private var TAG = javaClass.simpleName

    init {
        dec_format = DecimalFormat("#.##")
        dec_format.roundingMode = RoundingMode.CEILING
        currentTimeStamp = System.currentTimeMillis()
    }

    fun addData(arrList: List<City>) {
        this.mCityList = arrList
    }


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var city_tv: TextView = view.findViewById(R.id.city_tv)
        var aqi_tv: TextView = view.findViewById(R.id.aqi_tv)
        var lastupdate_tv: TextView = view.findViewById(R.id.lastupdate_tv)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var mCity = mCityList.get(position)


        holder.city_tv.setText(mCity.city)

        var aqi = dec_format.format(mCity.aqi)


        holder.aqi_tv.setTextColor(AppUtils.getColor(mCity.aqi, context))
        holder.aqi_tv.setText("" + aqi)


        for (i in 0..AppUtils.mCityNameList.size - 1) {
            var mAddedCity = AppUtils.mCityNameList.get(i)

            if (mAddedCity.city.equals(mCity.city, ignoreCase = true)) {

                var timeDifference = currentTimeStamp - AppUtils.mCityNameList.get(i).lastUpdated
                var timeDifferenceInSeconds = timeDifference / 1000


                if (AppUtils.mCityNameList.get(i).lastUpdated != 0L) {
                    holder.lastupdate_tv.setText(
                        AppUtils.getLastUpdated(
                            timeDifferenceInSeconds,
                            currentTimeStamp,
                            context
                        )
                    );
                }

                AppUtils.mCityNameList.get(i).lastUpdated = currentTimeStamp
                break
            }
        }

    }

    override fun getItemCount(): Int {
        return mCityList.size
    }

}