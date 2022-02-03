package com.example.countiresulkeler.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countiresulkeler.adapter.CounrtyAdapter
import com.example.countiresulkeler.R
import com.example.countiresulkeler.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

// TODO: Rename parameter arguments, choose names that match

class FeedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel:FeedViewModel
    private   var counrtyAdapter= CounrtyAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryListRecyclerView.layoutManager=LinearLayoutManager(context)
        countryListRecyclerView.adapter=counrtyAdapter

        observeLiveData()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.countryLooding.value=true;
            viewModel.refreshFromAPi()
            swipeRefreshLayout.isRefreshing=false

        }
    }


    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {countryies->
            countryies?.let {
                countryListRecyclerView.visibility=View.VISIBLE
                counrtyAdapter.updateCounryList(countryies)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {err->
            err?.let {
                if(it){
                    countryErrorTex.visibility=View.VISIBLE
                    countryListRecyclerView.visibility=View.GONE

                }else{
                    countryErrorTex.visibility=View.GONE

                }
            }
        })
        viewModel.countryLooding.observe(viewLifecycleOwner, Observer {isLooding->
            isLooding?.let {
                if(it){
                    countyLooding.visibility=View.VISIBLE
                    countryErrorTex.visibility=View.GONE
                    countryListRecyclerView.visibility=View.GONE
                }else{
                    countyLooding.visibility=View.GONE
                }
            }
        })
    }
}
