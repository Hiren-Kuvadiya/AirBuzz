package com.test.airbuzz.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.myapplication.view.adapters.CityListAdapter
import com.test.airbuzz.R
import com.test.airbuzz.model.City
import kotlinx.android.synthetic.main.fragment_city_list.view.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CityListFragment : Fragment() {

    private var mList: ArrayList<City>

    private lateinit var cityAdapter: CityListAdapter
    private var root: View? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    init {
        mList = ArrayList<City>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_city_list, container, false)

        setData(mList);

        return root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CityListFragment()
    }

    public fun setData(mUpdatedList: ArrayList<City>) {
        mList = mUpdatedList

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        root!!.cityRv.setLayoutManager(layoutManager)
        root!!.cityRv.setItemAnimator(DefaultItemAnimator())

        cityAdapter = CityListAdapter(context, mList)
        root!!.cityRv.adapter = cityAdapter

    }

    fun updateAdapter(mUpdatedList: ArrayList<City>){
        mList.clear()
        mList.addAll(mUpdatedList)
        cityAdapter.notifyDataSetChanged()
    }


}