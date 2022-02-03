package com.example.countiresulkeler.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.countiresulkeler.R
import com.example.countiresulkeler.databinding.FragmentCountryBinding
import com.example.countiresulkeler.util.downloadFromUrl
import com.example.countiresulkeler.util.placeHolderProgressBar
import com.example.countiresulkeler.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_country.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class CountryFragment : Fragment() {
    private lateinit var viewModel: CountryViewModel
    private var myId=0
    private lateinit var  dataBinding:FragmentCountryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_country, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(CountryViewModel::class.java)

        arguments?.let{
            myId=CountryFragmentArgs.fromBundle(it).countryId
        }
        viewModel.getDataFromRoom(myId)
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.country.observe(viewLifecycleOwner, Observer {country->
            country?.let {
                dataBinding.myModel=country
                Log.e("TAG",  country.countryImgUrl )
                /*
                countyName.text=country.countryName
                countyRegion.text=country.countryRegion
                countrCapital.text=country.countryCapital
                countyCurrency.text=country.countryCurrency
                countyLanguage.text=country.countryLanguage
                context?.let {
                    countryImageView.downloadFromUrl(country.countryImgUrl, placeHolderProgressBar(it))
                }*/
            }
        })
    }
}