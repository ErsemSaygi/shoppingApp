package com.afac.shoppingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afac.shoppingapp.R
import com.afac.shoppingapp.viewModel.ShoppingCartViewModel
import com.afac.shoppingapp.adapter.ClothesAdapter
import kotlinx.android.synthetic.main.fragment_shopping_cart.*

class ShoppingCart : Fragment() {
    private lateinit var viewModel: ShoppingCartViewModel
    private val shoppingCartAdapter=ClothesAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProviders.of(this).get(ShoppingCartViewModel::class.java)
        viewModel.refreshFromAPI()

        idGRV.adapter=shoppingCartAdapter
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.clothes.observe(this,Observer{clothes->
            clothes?.let {
                idGRV.visibility = View.VISIBLE
                shoppingCartAdapter.updateCountryList(clothes)
            }

        })
        viewModel.clothesError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {
                    clothesError.visibility = View.VISIBLE
                } else {
                    clothesError.visibility = View.GONE
                }
            }
        })

        viewModel.clothesLoading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    clothesLoading.visibility = View.VISIBLE
                    idGRV.visibility = View.GONE
                    clothesError.visibility = View.GONE
                } else {
                    clothesLoading.visibility = View.GONE
                }
            }
        })
    }
}