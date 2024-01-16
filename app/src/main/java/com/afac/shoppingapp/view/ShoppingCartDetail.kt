package com.afac.shoppingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.afac.shoppingapp.DataBindingTriggerClass
import com.afac.shoppingapp.R
import com.afac.shoppingapp.databinding.FragmentShoppinCartDetailBinding
import com.afac.shoppingapp.viewModel.ShoppingCartDetailViewModel
import com.afac.shoppingapp.viewModel.ShoppingCartViewModel

class ShoppingCartDetail : Fragment() {
 private lateinit  var viewModel:ShoppingCartDetailViewModel
 private lateinit  var dataBinding:FragmentShoppinCartDetailBinding
    private var clothesId=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_shoppin_cart_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(ShoppingCartDetailViewModel::class.java)
        arguments?.let {
            clothesId=ShoppingCartDetailArgs.fromBundle(it).cartId

        }
        viewModel.getDataFromRoom(clothesId)
        observeLiveData()

    }

private  fun  observeLiveData(){
    viewModel.clothesLiveData.observe(viewLifecycleOwner) { clothes ->
        clothes.let {
            dataBinding.selectedClothes=clothes
        }


    }
}
}