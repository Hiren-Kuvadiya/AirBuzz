package com.test.airbuzz.utils

import android.content.Context
import com.test.myapplication.utils.Constants
import com.test.airbuzz.R
import com.test.airbuzz.model.City
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AppUtils {

    companion object {
        fun getColor(aqi: Double, mContext: Context?): Int {

            if (aqi <= 50)
                return mContext!!.resources.getColor(R.color.good)
            else if (aqi <= 100)
                return mContext!!.resources.getColor(R.color.satisfactory)
            else if (aqi <= 200)
                return mContext!!.resources.getColor(R.color.moderate)
            else if (aqi <= 300)
                return mContext!!.resources.getColor(R.color.poor)
            else if (aqi <= 400)
                return mContext!!.resources.getColor(R.color.veryPoor)
            else (aqi <= 500)
            return mContext!!.resources.getColor(R.color.severe)

        }

        var mCityNameList = ArrayList<City>()

        fun getLastUpdated(timeDifference: Long, timeStamp : Long, mContext: Context?): String {

            if (timeDifference < 60)
                return mContext!!.resources.getString(R.string.aFewSeconsAgo)
            else if (timeDifference <= 2 * 60)
                return mContext!!.resources.getString(R.string.aMinuteAgo)
            else if(timeDifference>60)
                return getTimeFromStamp(timeStamp)
            else
                return ""

        }

        fun getTimeFromStamp(timeStamp : Long) : String{
            val sdf = SimpleDateFormat(Constants.TIME_FORMATE)
            val netDate = Date(timeStamp)
            val date =sdf.format(netDate)
            return date
        }

    }


}